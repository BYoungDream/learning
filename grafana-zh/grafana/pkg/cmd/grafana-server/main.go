package main

import (
	"flag"
	"fmt"
	"os"
	"os/signal"
	"runtime"
	"runtime/trace"
	"strconv"
	"syscall"
	"time"

	"net/http"
	_ "net/http/pprof"

	"github.com/grafana/grafana/pkg/log"
	"github.com/grafana/grafana/pkg/metrics"
	"github.com/grafana/grafana/pkg/setting"

	extensions "github.com/grafana/grafana/pkg/extensions"
	_ "github.com/grafana/grafana/pkg/services/alerting/conditions"
	_ "github.com/grafana/grafana/pkg/services/alerting/notifiers"
	_ "github.com/grafana/grafana/pkg/tsdb/cloudwatch"
	_ "github.com/grafana/grafana/pkg/tsdb/elasticsearch"
	_ "github.com/grafana/grafana/pkg/tsdb/graphite"
	_ "github.com/grafana/grafana/pkg/tsdb/influxdb"
	_ "github.com/grafana/grafana/pkg/tsdb/mysql"
	_ "github.com/grafana/grafana/pkg/tsdb/opentsdb"
	_ "github.com/grafana/grafana/pkg/tsdb/postgres"
	_ "github.com/grafana/grafana/pkg/tsdb/prometheus"
	_ "github.com/grafana/grafana/pkg/tsdb/stackdriver"
	_ "github.com/grafana/grafana/pkg/tsdb/testdata"
)

var version = "5.0.0"
var commit = "NA"
var buildstamp string

var configFile = flag.String("config", "", "path to config file")
var homePath = flag.String("homepath", "", "path to grafana install/home path, defaults to working directory")
var pidFile = flag.String("pidfile", "", "path to pid file")

func main() {
	v := flag.Bool("v", false, "prints current version and exits")
	profile := flag.Bool("profile", false, "Turn on pprof profiling")
	profilePort := flag.Int("profile-port", 6060, "Define custom port for profiling")
	flag.Parse()
	if *v {
		fmt.Printf("Version %s (commit: %s)\n", version, commit)
		os.Exit(0)
	}

	if *profile {
		runtime.SetBlockProfileRate(1)
		go func() {
			http.ListenAndServe(fmt.Sprintf("localhost:%d", *profilePort), nil)
		}()

		f, err := os.Create("trace.out")
		if err != nil {
			panic(err)
		}
		defer f.Close()

		err = trace.Start(f)
		if err != nil {
			panic(err)
		}
		defer trace.Stop()
	}

	buildstampInt64, _ := strconv.ParseInt(buildstamp, 10, 64)
	if buildstampInt64 == 0 {
		buildstampInt64 = time.Now().Unix()
	}

	setting.BuildVersion = version
	setting.BuildCommit = commit
	setting.BuildStamp = buildstampInt64
	setting.IsEnterprise = extensions.IsEnterprise

	metrics.M_Grafana_Version.WithLabelValues(version).Set(1)

	server := NewGrafanaServer()

	go listenToSystemSignals(server)

	err := server.Run()

	code := server.Exit(err)
	trace.Stop()
	log.Close()

	os.Exit(code)
}

func listenToSystemSignals(server *GrafanaServerImpl) {
	signalChan := make(chan os.Signal, 1)
	sighupChan := make(chan os.Signal, 1)

	signal.Notify(sighupChan, syscall.SIGHUP)
	signal.Notify(signalChan, os.Interrupt, os.Kill, syscall.SIGTERM)

	for {
		select {
		case <-sighupChan:
			log.Reload()
		case sig := <-signalChan:
			server.Shutdown(fmt.Sprintf("System signal: %s", sig))
		}
	}
}

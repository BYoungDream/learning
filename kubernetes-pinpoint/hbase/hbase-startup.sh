#/bin/bash

/opt/hbase/hbase-1.2.6/bin/start-hbase.sh
sleep 10
/opt/hbase/hbase-1.2.6/bin/hbase shell /opt/hbase/hbase-create.hbase
/opt/hbase/hbase-1.2.6/bin/stop-hbase.sh

sleep 10

/opt/hbase/hbase-1.2.6/bin/hbase master start

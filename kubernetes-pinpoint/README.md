# pinpoint在kubernetes中部署
1、服务端把pinpoint中collector、web、hbase打包到一个docker镜像中，详细文件在server文件夹中
2、收集端(客户端),把pinpoint-agent打包成docker镜像，在kubernetes中和应用程序打包成一个pod，详细文件在client文件夹中
3、参考文件：https://github.com/dawidmalina/docker-pinpoint
# pinpoint在kubernetes中部署
1、服务端把pinpoint中collector、web、hbase打包到一个docker镜像中，详细文件在server文件夹中   <br />
2、收集端(客户端),把pinpoint-agent打包成docker镜像，在kubernetes中和应用程序打包成一个pod，详细文件在client文件夹中  <br />
3、参考文件：https://github.com/dawidmalina/docker-pinpoint   <br />
4、把pinpoint中的collector、web、hbase分开打包成镜像，部署到kubernetes中，总是收集不到数据，提示request time out ...暂时没有解决，如果谁分开部署过，欢迎留言...<br />
5、后续尝试，可以把collector、web、hbase各自的镜像打包到一个pod，共享IP，这样就可以上述出现的问题,yaml文件实例，在yaml文件夹。
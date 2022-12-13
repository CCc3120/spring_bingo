#添加Java启动的必要镜像
FROM williamyeh/java8
#将本地文件挂载到当前容器
VOLUME /tmp
#复制jar文件和配置文件所在的目录到容器里
ADD spring_bingo-0.0.1-SNAPSHOT.jar /app/springbingo.jar
#创建一个目录存放配置文件,启动容器时挂载配置文件
RUN mkdir -p /conf
#声明需要暴露的端口
#EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/springbingo.jar","--spring.config.location=/conf/application.yml"]

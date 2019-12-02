FROM hseeberger/scala-sbt:8u222_1.3.4_2.13.1

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project project/
COPY src src/
COPY build.sbt ./

RUN sbt assembly && ln -s target/*/crawler.jar .

ENTRYPOINT ["java", "-jar", "crawler.jar"]

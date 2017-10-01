FROM openjdk:8-jdk
LABEL maintainer="Datadog Inc. <tyler@datadoghq.com>"

COPY . /usr/src/dd-trace-java-demo
WORKDIR /usr/src/dd-trace-java-demo

RUN ./gradlew installDist

CMD /usr/src/dd-trace-java-demo/build/install/dd-trace-java-demo/bin/dd-trace-java-demo

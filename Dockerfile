FROM openjdk:8-jdk
LABEL maintainer="Datadog Inc. <tyler@datadoghq.com>"

COPY . /usr/src/demo-app
WORKDIR /usr/src/demo-app

RUN ./gradlew installDist

CMD /usr/src/demo-app/build/install/demo-app/bin/demo-app

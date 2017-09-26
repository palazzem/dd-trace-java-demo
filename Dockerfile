FROM openjdk:8-jdk

COPY . /usr/src/demo-app
WORKDIR /usr/src/demo-app

RUN ./gradlew installDist

CMD /usr/src/demo-app/build/install/dd-trace-java-demo/bin/dd-trace-java-demo

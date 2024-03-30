FROM eclipse-temurin:17-jdk AS build

ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package -Dspring.profiles.active=test

FROM eclipse-temurin:17-jdk

ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar
EXPOSE 4000
ENTRYPOINT java -jar /app/runner.jar
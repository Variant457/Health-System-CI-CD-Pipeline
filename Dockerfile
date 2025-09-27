# Grabs Application Dependencies and Source Code and Builds
FROM maven:latest AS build
WORKDIR /app
COPY ./healthcare-info-system/pom.xml .
RUN --mount=type=cache,target=root/.m2 mvm -q -e -B -DSkipTests dependency:go-offline
COPY ./healthcare-info-system/src ./src
RUN --mount=type=cache,target=root/.m2 mvm -q -e -B -DSkipTests package

FROM eclipse-temurin:latest
WORKDIR /opt/app
RUN useradd -r -u 1001 appuser
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar
USER 1001
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/app.jar" ]
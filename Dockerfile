# Grabs Application Dependencies and Source Code and Builds
FROM maven:latest AS build
WORKDIR /app
COPY ./healthcare-info-system/pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -B -DskipTests dependency:go-offline
COPY ./healthcare-info-system/src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -B -DskipTests package

# Run the Docker Container as Read-Only User
FROM eclipse-temurin:latest
WORKDIR /opt/app
RUN useradd -r -u 1001 appuser
COPY --from=build /app/target/*-SNAPSHOT.jar /opt/app/app.jar
USER 1001
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/app.jar" ]
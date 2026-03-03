FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN addgroup -S notes && adduser -S notes -G notes

RUN mkdir -p /app/data && chown -R notes:notes /app

COPY --from=build --chown=notes:notes /build/target/*.jar app.jar

USER notes:notes

EXPOSE 8081

ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/notes-reminders.db \
    JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
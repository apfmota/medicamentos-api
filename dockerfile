# Multi-stage build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy project files
COPY . .

# Give execution permission to mvnw and build
RUN chmod +x mvnw && ./mvnw -DskipTests=true package

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy jar from build stage (name is fixed via <finalName>)
COPY --from=build /app/target/medicamentos-api.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=15s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/medicamentos || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
# Build da aplicação usando Maven
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Runner
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copia apenas o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Define o fuso horário para Brasil/Brasília
ENV TZ=America/Sao_Paulo

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
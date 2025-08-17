# Використовуємо офіційний образ JDK 17
FROM eclipse-temurin:17-jdk-alpine as build

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо pom.xml та завантажуємо залежності
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Копіюємо весь проєкт та збираємо його
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Створюємо фінальний образ з мінімальним розміром
FROM eclipse-temurin:17-jre-alpine

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо зібраний JAR файл з попереднього етапу
COPY --from=build /app/target/WeddingDay-0.0.1-SNAPSHOT.jar /app/WeddingDay.jar

# Вказуємо команду для запуску додатку
ENTRYPOINT ["java", "-jar", "/app/WeddingDay.jar"]

# Відкриваємо порт 8080
EXPOSE 8080

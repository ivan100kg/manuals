# 📌 Полное руководство по сборке и деплою Java 21 (Spring Boot) в Docker с использованием Nexus и PostgreSQL

## 🛠️ 1. Подготовка проекта

### 1.1. **Файл `settings.xml` для Nexus**
Файл `settings.xml` хранится в `src/main/resources/settings.xml`. Он нужен для скачивания зависимостей с Nexus.

Пример `src/main/resources/settings.xml`:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <mirrors>
        <mirror>
            <id>nexus</id>
            <mirrorOf>*</mirrorOf>
            <url>https://nexus.example.com/repository/maven-public/</url>
            <layout>default</layout>
        </mirror>
    </mirrors>
</settings>
```

---

### 1.2. **Настройка `pom.xml`**

Добавляем копирование `settings.xml` в `~/.m2/` перед сборкой:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.3.1</version>
            <executions>
                <execution>
                    <id>copy-settings</id>
                    <phase>initialize</phase>
                    <goals>
                        <goal>copy-resources</goal>
                    </goals>
                    <configuration>
                        <outputDirectory>${user.home}/.m2</outputDirectory>
                        <resources>
                            <resource>
                                <directory>src/main/resources</directory>
                                <includes>
                                    <include>settings.xml</include>
                                </includes>
                            </resource>
                        </resources>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

---

### 1.3. **Настройки `application.properties`**
Файл `src/main/resources/application.properties` содержит параметры подключения к базе:

```properties
spring.datasource.url=jdbc:postgresql://tfoms_db:5432/charly
spring.datasource.username=tfoms
spring.datasource.password=402e72ec-1616-4090-8234-caa39bd5bcda
spring.jpa.hibernate.ddl-auto=update
spring.profiles.active=docker
```

---

## 🐳 2. Настройка контейнеров

### 2.1. **Dockerfile**
Используем **готовый `jar` файл**, без сборки в контейнере:

```dockerfile
FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

---

### 2.2. **Файл `docker-compose.yml`**
Определяем два сервиса: приложение и базу данных.

```yaml
services:
  tfoms_app:
    image: myrepo/tfoms_app:latest
    ports:
      - "8021:8080"
    depends_on:
      - tfoms_db
    volumes:
      - tfoms_app_vol:/data
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://tfoms_db:5432/charly
      SPRING_DATASOURCE_USERNAME: tfoms
      SPRING_DATASOURCE_PASSWORD: 402e72ec-1616-4090-8234-caa39bd5bcda
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
      SPRING_PROFILES_ACTIVE: docker

  tfoms_db:
    image: postgres
    environment:
      POSTGRES_DB: charly
      POSTGRES_USER: tfoms
      POSTGRES_PASSWORD: 402e72ec-1616-4090-8234-caa39bd5bcda
    ports:
      - "5421:5432"
    volumes:
      - tfoms_db_vol:/var/lib/postgresql/data

volumes:
  tfoms_app_vol:
  tfoms_db_vol:
```

---

## 🚀 3. Сборка и деплой

### 3.1. **Локальная сборка (`.jar`)**

```sh
mvn clean package -DskipTests
```

После успешной сборки появится файл `target/app.jar`.

### 3.2. **Сборка Docker-образа**

```sh
docker build -t myrepo/tfoms_app:latest .
```

### 3.3. **Запуск локально через Docker Compose**

```sh
docker-compose up -d
```

Приложение будет доступно на **`http://localhost:8021`**.

### 3.4. **Просмотр логов**

```sh
docker logs -f tfoms_app
```

### 3.5. **Остановка контейнеров**

```sh
docker-compose down
```

---

## 🌍 4. Деплой на удалённый сервер

### 4.1. **Загрузка образа в Docker Hub**

```sh
docker tag myrepo/tfoms_app:latest myrepo/tfoms_app:v1.0

docker push myrepo/tfoms_app:v1.0
```

### 4.2. **На сервере: загрузка и запуск**

```sh
docker pull myrepo/tfoms_app:v1.0
docker-compose up -d
```

### 4.3. **Остановка на сервере**

```sh
docker-compose down
```

---

## 📦 5. Деплой через архив Docker-образа

Если нет доступа к Docker Hub, можно передать образ вручную:

```sh
# Сохранить образ в файл
docker save -o myapp.tar myrepo/tfoms_app:latest

# Передать на другую машину
scp myapp.tar user@IP:/path

# Загрузить образ на сервере
ssh user@IP "docker load -i /path/myapp.tar"

# Запустить контейнер
ssh user@IP "docker-compose up -d"
```

---

## ✅ **Вывод**
Теперь у тебя есть полный процесс:
- **Настройка проекта** (`settings.xml`, `pom.xml`, `application.properties`)
- **Создание `jar`-файла**
- **Сборка Docker-образа**
- **Запуск локально и на сервере**
- **Деплой через архив Docker-образа**

Готово! 🚀🔥


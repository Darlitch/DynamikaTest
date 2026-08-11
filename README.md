# Library Application

Веб-приложение для управления библиотекой.

Приложение позволяет:
- управлять книгами;
- управлять клиентами;
- оформлять выдачу книг клиентам;
- получать информацию о выданных книгах через REST API.

---

## Используемые технологии

- Java 8
- Spring Boot 2.7.18
- Spring Web MVC
- Spring Data JPA (Hibernate)
- Thymeleaf
- PostgreSQL 14
- Maven
- Lombok
- Docker Compose

---

# Запуск приложения

## Требования

Необходимо установить:

- JDK 8
- Maven
- Docker и Docker Compose

---

## Запуск базы данных

В корне проекта выполнить:

```bash
docker compose up -d
```

После запуска будет доступен PostgreSQL:

```
Host: localhost
Port: 5433
Database: library
Username: postgres
Password: postgres
```

---

## Настройка приложения

Настройки подключения к базе данных находятся в файле:

```
src/main/resources/application.properties
```

Основные параметры:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/library
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
```

При первом запуске Hibernate автоматически создаёт необходимую схему базы данных.

Используется режим:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Данные в базе сохраняются между перезапусками приложения.

---

## Сборка проекта

Выполнить:

```bash
mvn package
```

После успешной сборки будет создан файл:

```
target/library-1.0-SNAPSHOT.jar
```

---

## Запуск приложения

Запуск выполняется командой:

```bash
java -jar target/library-1.0-SNAPSHOT.jar
```

После запуска приложение доступно по адресу:

```
http://localhost:8080
```

---

## Веб-интерфейс

Основные страницы:

| URL | Описание |
| --- | --- |
| `/books` | Управление книгами |
| `/clients` | Управление клиентами |
| `/loans/create` | Выдача книг |

---

## REST API

Получение списка выданных книг:

```
GET /api/loans
```

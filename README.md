# Library Management System – Spring Boot & MySQL

A learning‑purpose REST API demonstrating clean architecture with Spring Boot 3, Hibernate (JPA), and MySQL. It supports basic CRUD for books and simple book‑assignment to users.

---

## 📂 Project structure

```
└── src/main/java/com/spring/learning/library_management
    ├── books
    │   ├── controller      # REST endpoints
    │   ├── dto             # Request / Response DTOs
    │   ├── entity          # JPA entities
    │   ├── repository      # Spring‑Data JPA repos
    │   └── service         # Business logic
    ├── users               # User module (similar layout)
    └── common              # Shared classes (RestApiResponse, exceptions, etc.)
```

---

## 🚀 Getting started

### Prerequisites

| Tool                     | Version                         |
| ------------------------ | ------------------------------- |
| JDK 17+                  | ✔                               |
| Maven 3.9+               | ✔                               |
| MySQL 8+/ 9.2 (Homebrew) | running on **`localhost:3306`** |

### Clone & run

```bash
git clone https://github.com/<your-handle>/library-management.git
cd library-management

# Start MySQL & create schema
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS library_management;"

# Build & run
./mvnw spring-boot:run
```

Default profile starts on **[http://localhost:8080/library-management](http://localhost:8080/library-management)**.

> Tip: `docker-compose up` spins MySQL + the app (see `docker-compose.yml`).

---

## 🔑 Configuration

`src/main/resources/application.properties`

```properties
server.servlet.context-path=/library-management

spring.datasource.url=jdbc:mysql://localhost:3306/library_management?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root@1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🛠️ API Endpoints (Books)

| Method | Path                      | Description          |
| ------ | ------------------------- | -------------------- |
| GET    | `/books`                  | List all books       |
| GET    | `/books/{id}`             | Get book by ID       |
| GET    | `/books/search?title=...` | Find by title (LIKE) |
| GET    | `/books/search?genre=...` | Find by genre        |
| POST   | `/books`                  | Add new book         |
| PUT    | `/books/{id}`             | Update existing book |
| DELETE | `/books/{id}`             | Delete by ID         |
| DELETE | `/books?title=...`        | Delete by title      |
| POST   | `/books/assign`           | Assign book to user  |

> Full path includes prefix `/library-management` e.g. `GET /library-management/books`.

### API Endpoints (Users)

| Method | Path          | Description                                              |
| ------ | ------------- | -------------------------------------------------------- |
| GET    | `/user`       | List all users                                           |
| GET    | `/user/{id}`  | Get user by ID                                           |
| GET    | `/user/email` | Find user by email (pass JSON body `{ "email": "..." }`) |
| POST   | `/user`       | Create new user                                          |
| DELETE | `/user/{id}`  | Deactivate (soft‑delete) user                            |

\--------|------|-------------|
\| GET    | `/books` | List all books |
\| GET    | `/books/{id}` | Get book by ID |
\| GET    | `/books/search?title=...` | Find by title (LIKE) |
\| GET    | `/books/search?genre=...` | Find by genre |
\| POST   | `/books` | Add new book |
\| PUT    | `/books/{id}` | Update existing book |
\| DELETE | `/books/{id}` | Delete by ID |
\| DELETE | `/books?title=...` | Delete by title |
\| POST   | `/books/assign` | Assign book to user |

> Full path includes prefix `/library-management` e.g. `GET /library-management/books`.

### Sample request – Add Book

```http
POST /library-management/books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "genre": "Programming",
  "stock": 5
}
```

---

## 🧪 Testing

```bash
./mvnw test        # unit tests
./mvnw verify -Pintegration  # integration tests with Testcontainers
```

---

## 📜 License

MIT – free for learning and experimentation.

---

### 🙋‍♂️ Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

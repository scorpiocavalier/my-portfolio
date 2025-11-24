# Coffee Shop Backend

Spring Boot REST API for the Coffee Shop application.

## 🛠️ Tech Stack

- **Java 24**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **H2 Database** (in-memory, for development)

## 🚀 Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/coffees`

- `GET /api/coffees` - Get all coffees
- `GET /api/coffees/{id}` - Get coffee by ID
- `POST /api/coffees` - Create a new coffee
- `PUT /api/coffees/{id}` - Update a coffee
- `DELETE /api/coffees/{id}` - Delete a coffee

## 🗄️ Database

H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:coffeeshopdb`
- Username: `sa`
- Password: (empty)

## 📦 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/store/
│   │   │   ├── controller/    # REST Controllers
│   │   │   ├── dto/           # Data Transfer Objects (Records)
│   │   │   ├── entity/        # JPA Entities
│   │   │   ├── repository/    # Data Access Layer
│   │   │   ├── service/       # Business Logic
│   │   │   └── StoreApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                  # Unit tests
└── pom.xml
```


# Implementation Guide - Coffee Shop Application

## 📁 Project Structure

The project is organized into two main folders:

- **`backend/`** - Spring Boot REST API
- **`frontend/`** - Angular micro frontends (Nx workspace)

## 📚 Progress Tracker

- ✅ **Lesson 1**: Understanding Spring Boot Architecture
- ✅ **Lesson 2**: Entity Layer - Coffee Entity (Completed!)
- ✅ **Lesson 3**: Repository Layer - CoffeeRepository (Completed!)
- ✅ **Lesson 4**: Service Layer - CoffeeService (Completed!)
- ✅ **Lesson 5**: DTOs - Using Java Records (Completed!)
- ✅ **Lesson 6**: REST Controller - CoffeeController (Completed!)
- 🔄 **Lesson 7**: Angular Frontend Setup (Current Task)

---

## ✅ Completed: Task 1 - Coffee Entity

**Status**: ✅ Completed!

You've successfully created the `Coffee` entity class with:

- ✅ All required annotations (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`)
- ✅ All fields (id, name, price, description, size)
- ✅ Default constructor (required by JPA)
- ✅ Parameterized constructor
- ✅ All getters and setters

**Location**: `backend/src/main/java/com/example/store/entity/Coffee.java`

**What you learned**:

- How to create JPA entities
- Why entities use classes (not records)
- Annotations and their purposes
- Java bean conventions (getters/setters)

---

## ✅ Completed: Task 2 - CoffeeRepository

**Status**: ✅ Completed!

You've successfully created the `CoffeeRepository` interface with:

- ✅ Correct imports (JpaRepository, Repository, Coffee entity)
- ✅ `@Repository` annotation
- ✅ Extends `JpaRepository<Coffee, Long>`
- ✅ Proper interface declaration

**Location**: `backend/src/main/java/com/example/store/repository/CoffeeRepository.java`

**What you learned**:

- How to create Repository interfaces
- Spring Data JPA magic (automatic implementation)
- Dependency injection concepts
- Interface vs Class distinction

---

## ✅ Completed: Task 3 - CoffeeService

**Status**: ✅ Completed!

You've successfully created the `CoffeeService` class with:

- ✅ `@Service` annotation
- ✅ `@Autowired` repository injection
- ✅ All required methods (`getAllCoffees`, `getCoffeeById`, `createCoffee`, `updateCoffee`, `deleteCoffee`)
- ✅ Proper use of `Optional` with `orElseThrow()`
- ✅ Error handling for missing coffees
- ✅ Update logic correctly updates all fields

**Location**: `backend/src/main/java/com/example/store/service/CoffeeService.java`

**What you learned**:

- How to create Service classes
- Dependency injection with `@Autowired`
- Business logic and error handling
- Working with `Optional` and exception handling
- CRUD operations at the service layer

---

## ✅ Completed: Task 4 - DTOs (Java Records)

**Status**: ✅ Completed!

You've successfully created both DTO records:

- ✅ `CoffeeRequest` - No ID field (correct for creating coffees)
- ✅ `CoffeeResponse` - Includes ID field (correct for returning coffees)
- ✅ Both are records (immutable, concise)
- ✅ Proper package structure (`dto` package)
- ✅ All fields correctly defined

**Locations**:

- `backend/src/main/java/com/example/store/dto/CoffeeRequest.java`
- `backend/src/main/java/com/example/store/dto/CoffeeResponse.java`

**What you learned**:

- Java Records syntax and benefits
- DTO pattern (separating API contract from database)
- Immutable data structures
- Modern Java best practices

---

## ✅ Completed: Task 5 - REST Controller

**Status**: ✅ Completed!

You've implemented the `CoffeeController` with:

- ✅ Consistent use of `ResponseEntity` and proper status codes (200/201/204)
- ✅ Optional Location header for POST
- ✅ Clean DTO ↔ Entity conversion helpers
- ✅ All CRUD endpoints mapped

**Location**: `backend/src/main/java/com/example/store/controller/CoffeeController.java`

**What you learned**:

- REST endpoint design and HTTP semantics
- Controller-to-Service interaction
- DTO conversion and response shaping

---

## 🔄 Current Task: Angular Frontend Setup

Now let's create a standard Angular application in the `frontend/` folder.

### 📋 Task Instructions

1. **Create Angular application in the frontend folder**:

   ```bash
   cd frontend
   npx ng new coffee-shop-ui --routing --style=scss
   ```

   - Choose your preferred options (standalone components, etc.)
   - This will create the Angular app in `frontend/coffee-shop-ui/`

2. **Add proxy configuration** for Spring Boot API:

   - Create `frontend/coffee-shop-ui/proxy.conf.json`:
     ```json
     {
       "/api": {
         "target": "http://localhost:8080",
         "secure": false,
         "changeOrigin": true
       }
     }
     ```
   - Update `frontend/coffee-shop-ui/angular.json`:
     - In `projects.coffee-shop-ui.architect.serve.options`, add:
       ```json
       "proxyConfig": "proxy.conf.json"
       ```

3. **Generate Coffees feature**:

   ```bash
   cd coffee-shop-ui
   ng g c features/coffees/coffees-list
   ng g s features/coffees/coffees
   ```

4. **Start the application**:
   ```bash
   ng serve
   ```
   - App runs at http://localhost:4200

### 🎓 Learning Points

- **Angular CLI**: Standard tool for creating and managing Angular applications
- **Proxy Configuration**: Avoids CORS issues during development
- **Component & Service Generators**: Angular CLI commands to scaffold code
- **Feature Organization**: Organizing code by feature (coffees, orders, etc.)

### ✅ Check Your Work

- Angular app created in `frontend/coffee-shop-ui/` folder
- App runs on port 4200
- Proxy configured to forward `/api` requests to Spring Boot (port 8080)
- Can make API calls from Angular without CORS errors

**Controllers** are like **cashier counters**:

- Customer (Angular) asks: "Show me menu" → GET /api/coffees
- Customer orders: "I want a Latte" → POST /api/coffees
- Customer updates: "Change Latte price" → PUT /api/coffees/1
- Customer cancels: "Remove this item" → DELETE /api/coffees/1

### 📋 Task Instructions

Create a `CoffeeController` **class** that handles HTTP requests and converts between DTOs and Entities.

### ✅ Requirements

1. **Location**: Create the file at: `backend/src/main/java/com/example/store/controller/CoffeeController.java`

2. **Key Points**:

   - It's a **class** (not an interface!)
   - Use `@RestController` annotation
   - Use `@RequestMapping("/api/coffees")` for base URL
   - Inject `CoffeeService` using `@Autowired`
   - Convert between DTOs and Entities

3. **Methods to Create**:

   - `getAllCoffees()` - GET /api/coffees → Returns `List<CoffeeResponse>`
   - `getCoffeeById(Long id)` - GET /api/coffees/{id} → Returns `CoffeeResponse`
   - `createCoffee(CoffeeRequest request)` - POST /api/coffees → Returns `CoffeeResponse`
   - `updateCoffee(Long id, CoffeeRequest request)` - PUT /api/coffees/{id} → Returns `CoffeeResponse`
   - `deleteCoffee(Long id)` - DELETE /api/coffees/{id} → Returns `void` or `ResponseEntity`

4. **Conversion Logic**:
   - **CoffeeRequest → Coffee (Entity)**: Create new Coffee entity from request
   - **Coffee (Entity) → CoffeeResponse**: Create response from entity

### 💡 Hints

- Use `@RestController` from `org.springframework.web.bind.annotation.RestController`
- Use `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- Use `@PathVariable` for path parameters (like `{id}`)
- Use `@RequestBody` for request body (JSON data)
- Create helper methods for conversion: `toEntity()` and `toResponse()`

### 📝 Example Structure (Don't copy-paste, write it yourself!)

```java
package com.example.store.controller;

import com.example.store.dto.CoffeeRequest;
import com.example.store.dto.CoffeeResponse;
import com.example.store.entity.Coffee;
import com.example.store.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public List<CoffeeResponse> getAllCoffees() {
        return coffeeService.getAllCoffees().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        return toResponse(coffee);
    }

    @PostMapping
    public ResponseEntity<CoffeeResponse> createCoffee(@RequestBody CoffeeRequest request) {
        Coffee coffee = toEntity(request);
        Coffee savedCoffee = coffeeService.createCoffee(coffee);
        return new ResponseEntity<>(toResponse(savedCoffee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public CoffeeResponse updateCoffee(@PathVariable Long id, @RequestBody CoffeeRequest request) {
        Coffee coffee = toEntity(request);
        Coffee updatedCoffee = coffeeService.updateCoffee(id, coffee);
        return toResponse(updatedCoffee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        coffeeService.deleteCoffee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Helper method: Entity → DTO
    private CoffeeResponse toResponse(Coffee coffee) {
        return new CoffeeResponse(
            coffee.getId(),
            coffee.getName(),
            coffee.getPrice(),
            coffee.getDescription(),
            coffee.getSize()
        );
    }

    // Helper method: DTO → Entity
    private Coffee toEntity(CoffeeRequest request) {
        return new Coffee(
            request.name(),
            request.price(),
            request.description(),
            request.size()
        );
    }
}
```

### 🎓 Learning Points

- **@RestController**: Marks this as a REST API controller (auto-converts to JSON)
- **@RequestMapping**: Sets base URL path
- **@GetMapping/@PostMapping/etc.**: HTTP method mappings
- **@PathVariable**: Extracts path parameters (like `/api/coffees/{id}`)
- **@RequestBody**: Converts JSON request body to Java object
- **DTO Conversion**: Controller converts between Entity (internal) and DTO (API contract)
- **ResponseEntity**: Allows custom HTTP status codes

### ✅ Check Your Work

After you write the code:

1. Make sure it compiles (no red squiggles)
2. It's a class with `@RestController`
3. All HTTP methods are mapped correctly
4. Conversion methods (toEntity, toResponse) are implemented
5. Service is injected with `@Autowired`

---

## 📚 Need Help?

### Coffee Shop Analogy Recap:

- **Entity** = Menu item card (stored in database, mutable)
- **Repository** = Inventory system (finds, saves, deletes items)
- **Service** = Barista (business logic, knows HOW to process)
- **Controller** = Cashier (receives orders, returns results)
- **DTO** = Order slip/receipt (what customers see/send)

### Key Concepts:

- **Entity** = Class (mutable, for database)
- **DTO** = Record (immutable, for API)
- **Repository** = Interface (Spring provides implementation)
- **Service** = Class (contains business logic)
- **Controller** = Class (handles HTTP requests)

---

**Current Status**: Working on REST Controller. Keep going! ☕


# 🚀 Spring Boot Backend Development – Learning Journey

This repository contains my hands-on work, mini-projects, experiments, and assignments from the **Spring Boot 0–100 Course (Anuj Bhaiya)**.

I’m building a solid backend foundation in Java and documenting my progress weekly.

---

## 📍 Weeks Completed: **Week 1–2**

### 🔧 Spring Boot Fundamentals
- Installed and configured Java, Maven, Spring Boot
- Explored project structure and auto-configuration
- Learned how Spring Boot starters work
- Understanding of application.properties / application.yml

---

### 🧠 Core Spring Concepts
| Concept | Status |
|--------|--------|
| Dependency Injection | ✔️ |
| Inversion of Control | ✔️ |
| Bean Lifecycle | ✔️ |
| `@Component`, `@Service`, `@Repository` | ✔️ |
| `@Autowired` vs Constructor Injection | ✔️ |

---

### 🌐 Building REST APIs
- Created multiple API endpoints using:
  - `@RestController`
  - `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`
- Learned:
  - `@RequestBody`
  - `@PathVariable`
  - `@RequestParam`
- Proper HTTP status usage using `ResponseEntity`

---

### 📦 DTO (Data Transfer Object) + Validation
- Converted Request → DTO → Entity → Response DTO
- Used:
  - `@Valid`
  - `@NotNull`, `@NotBlank`
  - `@Email`
  - `@Min`, `@Max`
- Added custom validation annotation (`@EmployeeRoleValidation`)

---

### 🗄️ Database (Spring Data JPA + H2)
- Configured in-memory H2 database
- Used:
  - `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
- Executed full CRUD operations
- Learned derived queries such as:
  - `findByName()`
  - `findByEmail()`

Example Entity:

```java
@Entity
@Table(name="employees")
@Data
public class EmployeeEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private boolean isActive;
}
````

---

### 🧩 Global Response Handling

Implemented a global response wrapper using:

```java
@RestControllerAdvice
public class GlobalResponseAPIHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<?> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType contentType, Class<?> converterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ApiResponse<?>) return body;
        return new ApiResponse<>(body);
    }
}
```

Purpose:

* Ensures every API returns consistent JSON response.

---

### 🧪 API Testing

* Tested all endpoints in **Postman** / **Thunder Client**
* Verified:

    * Status codes
    * Request validation errors
    * JSON response format

---

## 🛠 Skills Built So Far

| Skill                                | Level |
| ------------------------------------ | ----- |
| Spring Boot Basics                   | ⭐⭐⭐⭐  |
| RESTful API Design                   | ⭐⭐⭐⭐  |
| Data Validation                      | ⭐⭐⭐   |
| Repository Layer                     | ⭐⭐⭐   |
| Global Exception & Response Handling | ⭐⭐⭐⭐  |
| Clean Architecture Thinking          | ⭐⭐⭐⭐  |

---

## 🔜 Coming Up Next

* Pagination & Sorting
* Spring Security + JWT Authentication
* MySQL Integration
* Layered Architecture Best Practices
* Logging & AOP
* Deployment to Cloud (AWS / Render / Railway)

---

## 📂 Project Structure

```
src/
 ├── main/java
 │    └── com/.../springbootJava
 │         ├── controllers
 │         ├── services
 │         ├── dto
 │         ├── entities
 │         ├── repositories
 │         └── advices
 └── resources
      └── application.properties
```

---

## 🏷 Tech Stack

| Tool                     | Status |
| ------------------------ | ------ |
| Java                     | ✔️     |
| Spring Boot              | ✔️     |
| Spring Validator         | ✔️     |
| JPA / Hibernate          | ✔️     |
| H2 Database              | ✔️     |
| Postman / Thunder Client | ✔️     |

---

## 👨‍💻 Author

**Adamya**
Aspiring Backend Engineer | Java | Spring Boot | System Design Learner

If you find this project useful, feel free to ⭐ star the repo.

---

```

---

If you want, I can also:

- Add **GitHub Shields badges**
- Create a **project banner**
- Add a **CHANGELOG**
- Format it like a **portfolio case study**

Want one of those upgrades?
```

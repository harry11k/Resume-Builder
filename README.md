# 📝 Secure Resume Builder – Spring Boot Full-Stack Application

A secure, full-stack Resume Builder application developed using **Java** and **Spring Boot**. This project allows users to create, manage, and export professional resumes with secure authentication and role-based authorization.

---

## 🚀 Features

* 🔐 **JWT-Based Authentication & Authorization**

  * Secure login & registration
  * Token-based API protection using Spring Security
  * Role-based access control

* 📄 **Resume Management**

  * Manage resume sections:

    * Education
    * Experience
    * Skills
    * Certifications
  * RESTful APIs for CRUD operations

* 🏗 **Layered Architecture**

  * Controller → Service → Repository
  * Clean separation of concerns
  * Global exception handling
  * Request validation

* 🗄 **Database Integration**

  * MySQL database
  * JPA/Hibernate ORM
  * Entity relationship mapping

* 🎨 **Dynamic UI**

  * Server-side rendering using Thymeleaf
  * HTML & CSS-based responsive design

* 📥 **Resume Export**

  * Export resume to PDF
  * Integrated OpenHTMLToPDF for PDF generation

* 🧪 **API Testing**

  * Verified API endpoints using Postman

---

## 🛠 Tech Stack

| Layer          | Technology Used      |
| -------------- | -------------------- |
| Backend        | Java, Spring Boot    |
| Security       | Spring Security, JWT |
| ORM            | JPA / Hibernate      |
| Database       | MySQL                |
| Frontend       | Thymeleaf, HTML, CSS |
| PDF Generation | OpenHTMLToPDF        |
| API Testing    | Postman              |

---

## 🏛 Architecture

The application follows a **Layered Architecture Pattern**:

```
Controller  →  Service  →  Repository  →  Database
```

### Key Design Principles:

* Separation of concerns
* Dependency Injection
* Global exception handling
* DTO validation
* Secure API endpoints with JWT filters

---

## 🔐 Authentication Flow

1. User registers or logs in.
2. Server validates credentials.
3. JWT token is generated.
4. Token must be included in Authorization header:

   ```
   Authorization: Bearer <your_token>
   ```
5. Protected endpoints validate token before processing request.

---

## 📦 API Endpoints (Sample)

### Auth APIs

* `POST /login`

### Resume APIs

*  `GET /login`
* `GET /edit`
* `POST /edit`
* `GET /delete`
* `GET /view/{userId}`
* `GET /download-resume`

---

## 🗃 Database Configuration

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_builder
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ How to Run the Project

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/resume-builder.git
cd resume-builder
```

### 2️⃣ Configure MySQL Database

* Create a database named `resume_builder`
* Update credentials in `application.properties`

### 3️⃣ Run the Application

run the main class from your IDE.

### 4️⃣ Access the Application

```
http://localhost:8080/login
```

## 🧪 Testing APIs with Postman

1. Register a new user.
2. Login to receive JWT token.
3. Add token in Authorization header.
4. Test protected endpoints.

---

## 📄 PDF Export Implementation

* Resume data is rendered into HTML using Thymeleaf.
* OpenHTMLToPDF converts rendered HTML into downloadable PDF.
* Supports styled and formatted resume output.

## 🎯 Future Enhancements

* Front End migration to React or Angular
* Cloud storage integration
* Profile image upload
* Admin dashboard
* Docker deployment

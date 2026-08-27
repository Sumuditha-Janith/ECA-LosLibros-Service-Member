# 👥 LosLibros - Member Service

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Document%20Store-47A248.svg)](https://www.mongodb.com/)
[![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-red.svg)](https://mapstruct.org/)

The **Member Service** handles library patron records, membership statuses, and contact details for the LosLibros Library Management System.

---

## 🌟 Features

- **Patron & Member Directory**: Full lifecycle management for library members (Registration, profile updates, deletions).
- **Document-Oriented Persistence**: Uses **MongoDB** (or MongoDB API on Google Cloud Firestore) via Spring Data MongoDB for scalable patron profiles.
- **Input Validation**: Strict request validation using Jakarta Bean Validation (`@Email`, `@NotBlank`).
- **Cloud Native**: Dynamically configures itself via **Spring Cloud Config** and registers with **Eureka Service Registry**.

---

## ⚙️ Configuration & Environment

- **Service Name**: `member-service`
- **Port**: Dynamic (`0` - registered with Eureka) or defined via Config Server.
- **Database**: MongoDB
  - Default URI: Configured in `configurations/services/member-service.yaml` (Supports local MongoDB `mongodb://localhost:27017/db-loslibros` or Cloud MongoDB cluster).

---

## 📡 API Endpoints (`/api/v1/members`)

| Method | Endpoint | Request Body | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/members` | `application/json` | Registers a new library member |
| `PUT` | `/api/v1/members/{memberId}` | `application/json` | Updates member details (name, email, phone, etc.) |
| `GET` | `/api/v1/members/{memberId}` | None | Retrieves a specific member's profile by ID |
| `GET` | `/api/v1/members` | None | Retrieves a list of all registered members |
| `DELETE` | `/api/v1/members/{memberId}` | None | Removes a member record from the directory |

---

## 📝 Request & Response Models

### 1. Create / Update Member Request (`POST` / `PUT` - `application/json`)
```json
{
  "memberId": "M001",
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "phone": "+1-555-0199",
  "membershipType": "Premium",
  "joinedDate": "2025-01-01"
}
```

### 2. Sample Member Response (`JSON`)
```json
{
  "memberId": "M001",
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "phone": "+1-555-0199",
  "membershipType": "Premium",
  "joinedDate": "2025-01-01"
}
```

---

## 🚀 Running the Service

### Prerequisites
1. **Config Server** (Port `9000`) and **Service Registry** (Port `9001`) must be active.
2. Active MongoDB instance or connection URI.

### Launch via Maven Wrapper

```bash
cd services/member-service
./mvnw spring-boot:run
```

### Launch via Built JAR

```bash
./mvnw clean package -DskipTests
java -jar target/Member-Service-1.0.0.jar
```

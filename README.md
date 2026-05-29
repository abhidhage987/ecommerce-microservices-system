# 🛒 E-Commerce Microservices System

A scalable E-Commerce Microservices application built using Java, Spring Boot, Spring Security, JWT Authentication, PostgreSQL, Eureka Service Discovery, and API Gateway.

## 🚀 Current Services

### 1. Eureka Server

* Service Discovery
* Registers all microservices
* Maintains service registry

### 2. API Gateway

* Single Entry Point
* Request Routing
* Load Balancing Support
* Security Integration

### 3. Auth Service

* User Registration
* User Login
* Password Encryption using BCrypt
* JWT Token Generation
* Spring Security Integration
* PostgreSQL Database Integration

---

## 🏗️ Architecture

```text
                 Client
                    |
              API Gateway
                    |
     -----------------------------
     |                           |
 Auth Service              Future Services
     |
 PostgreSQL

 Eureka Server
```

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Spring Cloud
* Eureka Server
* API Gateway
* JWT Authentication

### Database

* PostgreSQL

### Build Tool

* Maven

### Version Control

* Git
* GitHub

---

## 📂 Project Structure

```text
ecommerce-microservices-system
│
├── EurekaServer
│
├── api-gateway
│
├── auth-service
│
└── README.md
```

---

## 🔐 Auth Service APIs

### Register User

```http
POST /auth/register
```

Request Body

```json
{
  "username": "abhi",
  "email": "abhi@gmail.com",
  "password": "123456"
}
```

---

### Login User

```http
POST /auth/login
```

Request Body

```json
{
  "email": "abhi@gmail.com",
  "password": "123456"
}
```

Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

## 🔒 Security Features

* BCrypt Password Hashing
* JWT Token Generation
* Spring Security
* Stateless Authentication

---

## 🎯 Concepts Implemented

* Microservices Architecture
* Service Discovery
* API Gateway Pattern
* Layered Architecture
* Dependency Injection
* DTO Pattern
* Repository Pattern
* JWT Authentication
* Password Encryption

---

## 🚧 Upcoming Services

### Product Service

* Product Management
* Categories
* Search Products

### Cart Service

* Add to Cart
* Remove from Cart
* Update Quantity

### Order Service

* Place Orders
* Order History

### Payment Service

* Payment Processing

### Notification Service

* Email Notifications
* Event Driven Architecture

---

## 🔥 Future Enhancements

* JWT Validation Filter
* Role Based Access Control (RBAC)
* Refresh Token
* Redis Caching
* Kafka Integration
* Docker
* Kubernetes
* ELK Stack
* Zipkin Tracing
* Prometheus & Grafana Monitoring

---

## 👨‍💻 Developer

Abhishek Dhage

Java Backend Developer | Spring Boot Developer

---

⭐ If you like this project, consider giving it a star.

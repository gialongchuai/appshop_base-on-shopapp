# ShopApp Backend

## Description

**ShopApp Backend** is a modular, RESTful backend API developed for an **e-commerce application** as part of a self-learning project using **Spring Boot** and **Java 17**. The system simulates core e-commerce functionalities such as **user authentication**, **product management**, **order processing**, and **role-based access control**, with a strong emphasis on clean architecture and maintainability.

The backend system follows a layered architecture with clearly separated modules:
- **Controller**
- **Service**
- **Repository**
- **DTO**
- **Mapper**
- **Entity**

### Key Features
- **Secure JWT Authentication**: Implemented using **JWT** tokens with custom decoding and integrated with **OAuth2 Resource Server** for secure user access.
- **Modular Architecture**: Clear separation of concerns across controller, service, repository, and data models.
- **Request-Response Models**: Designed structured **DTO-based** models to enable secure and clear data exchange.
- **Efficient Object Mapping**: Automated DTO-to-entity mapping using **MapStruct**, reducing boilerplate and enhancing clarity.
- **Robust CRUD Operations**: Developed endpoints for categories, products, users, roles, orders, and product images following **RESTful principles**.
- **Global Exception Handling**: Implemented a **centralized global error handler** with custom error codes per module.
- **Simplified Data Access**: Leveraged **Spring Data JPA** and **Lombok** to streamline data persistence and reduce verbosity.
- **Role-Based Access Control**: Secured API endpoints using **Spring Security** with proper **CORS configuration** and **role-based permissions**.

## Technologies Used

### Backend & Framework
- **Spring Boot**, **Java 17**
- **Spring Data JPA**, **MapStruct**, **Lombok**

### Security & Authentication
- **JWT**, **OAuth2 Resource Server**
- **Spring Security**

### Architecture & Design
- **DTO**, **Mapper**, **Entity** layered design
- **RESTful API principles**

### Error Handling
- **Centralized Global Exception Handler**
- **Custom error codes**

# ShopApp Backend

Một hệ thống backend e-commerce RESTful API hoàn chỉnh được phát triển bằng **Spring Boot** và **Java 17**. Hệ thống mô phỏng các tính năng e-commerce cốt lõi như **xác thực người dùng**, **quản lý sản phẩm**, **xử lý đơn hàng**, và **kiểm soát truy cập dựa trên vai trò**, với kiến trúc clean architecture và khả năng bảo trì cao.

## 🏗️ Kiến trúc Layered

```
  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
  │     Client      │────│   Controller    │────│     Service     │
  │   (Frontend)    │    │     Layer       │    │     Layer       │
  └─────────────────┘    └─────────────────┘    └─────────────────┘
                                  │                       │
                                  │               ┌─────────────────┐
                                  │───────────────│   Repository    │
                                                  │     Layer       │
                                                  └─────────────────┘
                                                          │
                                                 ┌─────────────────┐
                                                 │     MySQL       │
                                                 │   Database      │
                                                 └─────────────────┘
  
  ┌───────────────────────────────────────────────────────────────────────┐
  │                     Cross-Cutting Concerns                            │
  ├─────────────────┬─────────────────┬─────────────────┬─────────────────┤
  │   Security      │   Global Error  │   DTO Mapping   │   Validation    │
  │  (JWT/OAuth2)   │    Handling     │   (MapStruct)   │   & Logging     │
  └─────────────────┴─────────────────┴─────────────────┴─────────────────┘
```

## 🚀 Tính năng chính

-  #### **🔐 Xác thực & Bảo mật**
-  #### **📦 Quản lý sản phẩm**
-  #### **👥 Quản lý người dùng**
-  #### **🛒 Xử lý đơn hàng**

## 🛠️ Công nghệ sử dụng

### **Backend Framework**
- **Spring Boot** - Framework ứng dụng chính
- **Java 17** - Ngôn ngữ lập trình
- **Spring Data JPA** - Data persistence và ORM
- **Spring Security** - Authentication và authorization

### **Database & Persistence**
- **MySQL** - Cơ sở dữ liệu quan hệ chính
- **HikariCP** - Connection pooling hiệu suất cao

### **Security & Authentication**
- **JWT** - Stateless authentication
- **OAuth2 Resource Server** - Fine-grained access control
- **BCrypt** - Secure password hashing

### **Development Tools**
- **Maven** - Build automation
- **MapStruct** - DTO-Entity mapping tự động
- **Lombok** - Giảm boilerplate code
- **Jakarta Validation** - Request data validation

## 📁 Cấu trúc dự án

```
shopapp-backend/
├── src/main/java/com/shopapp/
│   ├── controllers/          # REST API endpoints
│   ├── services/            # Business logic layer
│   ├── repositories/        # Data access layer
│   ├── entities/           # JPA entity models
│   ├── dtos/              # Data Transfer Objects
│   ├── mappers/           # MapStruct mappers
│   ├── config/            # Application configuration
│   ├── exceptions/        # Global exception handling
│   └── utils/             # Utility classes
├── src/main/resources/
├── src/test/             # Test classes
└── README.md
```

## 🏗️ Kiến trúc Clean Architecture

### **Layer Separation**
- **Controller Layer**: Xử lý HTTP requests/responses
- **Service Layer**: Business logic và validation
- **Repository Layer**: Data access và database operations
- **DTO Layer**: Data transfer objects cho API communication

### **Cross-cutting Concerns**
- **Global Exception Handling**: Centralized error management với custom error codes
- **Security**: JWT authentication và role-based authorization
- **Mapping**: Automated DTO-Entity mapping với MapStruct
- **Validation**: Input validation với Jakarta Validation

## 🔒 Tính năng bảo mật

- **JWT Authentication** với custom decoding
- **OAuth2 Resource Server** cho fine-grained permissions
- **BCrypt password hashing** cho secure credential storage
- **CORS configuration** cho cross-origin request handling
- **Role-based access control** với user/admin roles

## 📚 Best Practices được áp dụng

- **Layered Architecture** với clear separation of concerns
- **DTO Pattern** cho secure data transfer
- **Repository Pattern** cho data access abstraction
- **Global Exception Handling** cho consistent error responses
- **MapStruct** cho efficient object mapping
- **Lombok** để giảm boilerplate code

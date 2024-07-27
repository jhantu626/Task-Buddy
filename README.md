# TaskBuddy

TaskBuddy is a task management application built using Spring Boot, providing features for user authentication, task creation, task management, and user profile management. The project leverages various Spring Boot modules and integrates JWT for security.

## Features

- User Registration and Authentication
- Task Creation and Management
- User Profile Management
- API Documentation with OpenAPI/Swagger
- Secure endpoints with JWT authentication

## Technologies Used

- Java 17
- Spring Boot
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Security
  - Spring Boot Starter Web
  - Spring Boot Starter Test
- MySQL
- JWT (JSON Web Tokens)
- Lombok
- ModelMapper
- SpringDoc OpenAPI
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven
- MySQL

### Installation

1. Clone the repository:

```bash
git clone https://github.com/jhantubala626/TaskBuddy.git
```

2. Navigate to the project directory:

```bash
cd TaskBuddy
```
3. Configure the MySQL database:
- Create a database named taskbuddy and update the database configurations in src/main/resources/application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/taskbuddy
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```
4. Build and run the application:
```bash
mvn clean install
mvn spring-boot:run
```
## API Endpoints
### Authentication
- `POST /api/v1/auth/register`: Register a new user
- `POST /api/v1/auth/login`: Login and get a JWT token

### Task Management
- `POST /api/v1/task`: Create a new task
- `GET /api/v1/task/{id}`: Get task details by ID
- `GET /api/v1/task`: Get all tasks for the user
- `PATCH /api/v1/task/do-task-complete/{taskId}`: Mark task as completed
- `PUT /api/v1/task/update-task/{id}`: Update task details
- `DELETE /api/v1/task/delete-task/{id}`: Delete a task
### User Management
- `GET /api/v1/user/profile`: Get user profile
- `GET /api/v1/user/getAll`: Get all users (Admin only)
- `GET /api/v1/user/email/{email}`: Get user by email
- `GET /api/v1/user/{id}`: Get user by ID
- `GET /api/v1/user/role/{role}`: Get users by role
- `PUT /api/v1/user/update`: Update user details
- `PATCH /api/v1/user/update/password`: Update user password
- `DELETE /api/v1/user/deleteAccount`: Delete user account


## Security
The application uses JWT for securing endpoints. Ensure you include the token in the `Authorization` header for protected routes. Example:
```bash
Authorization: Bearer <your-token>
```

## API Documentation

API documentation is available at `/swagger-ui.html` and `/v3/api-docs`

## Swagger Snapshoots
### Auth-Controller
![auth-controller](https://github.com/user-attachments/assets/32e70fed-acea-458b-92f3-9b2c119f5530)

### User-Controller
![user-controller](https://github.com/user-attachments/assets/d7c9cb46-bb14-4d4a-b540-34abe6f8b9e8)

### Task-Controller
![task controller](https://github.com/user-attachments/assets/6d29e638-99e7-4c45-9f3e-44e5a1447125)


## Authors
[Jhantu Bala - GitHub](https://github.com/jhantu626)












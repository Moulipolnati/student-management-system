# Student Management System

## Project Overview

Student Management System is a full-stack web application developed using Spring Boot and React.

The application allows administrators to manage students, courses, and enrollments through a secure JWT-based authentication system.

---

## Features

### Authentication

* User Registration
* User Login
* JWT Authentication
* Protected APIs

### Student Management

* View Students
* Add Student
* Update Student
* Delete Student
* Search Student

### Course Management

* View Courses
* Add Course
* Update Course
* Delete Course

### Enrollment Management

* View Enrollments
* Create Enrollment
* Delete Enrollment

---

## Technologies Used

### Backend

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* MySQL
* Swagger OpenAPI

### Frontend

* React
* Vite
* Axios
* Bootstrap
* React Router

### Tools

* Git
* GitHub
* VS Code
* Postman
* Swagger UI

---

## Project Structure

StudentManagementSystem

├── backend

├── frontend

├── database

└── docs

---

## Backend Setup

1. Configure MySQL database.

2. Update application.properties.

3. Run Spring Boot application.

Backend URL:

http://localhost:8081

Swagger URL:

http://localhost:8081/swagger-ui/index.html

---

## Frontend Setup

Navigate to frontend folder.

Install dependencies:

npm install

Run application:

npm run dev

Frontend URL:

http://localhost:5173

---

## API Endpoints

### Authentication

POST /auth/register

POST /auth/login

### Students

GET /students

POST /students

PUT /students/{id}

DELETE /students/{id}

GET /students/search

### Courses

GET /courses

POST /courses

PUT /courses/{id}

DELETE /courses/{id}

### Enrollments

GET /enrollments

POST /enrollments

DELETE /enrollments/{id}

---

## Future Enhancements

* Role-Based Authorization
* Dashboard Analytics
* Pagination
* Course Search
* Enrollment Reports
* Docker Deployment

---

## Author

Mouli Polnati

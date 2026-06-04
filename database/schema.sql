CREATE DATABASE student_management_db;
SHOW DATABASES;
USE student_management_db;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    address VARCHAR(255)
);
CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description TEXT,
    duration VARCHAR(50)
);
CREATE TABLE enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enrollment_date DATE,

    CONSTRAINT fk_student
        FOREIGN KEY(student_id)
        REFERENCES students(id),

    CONSTRAINT fk_course
        FOREIGN KEY(course_id)
        REFERENCES courses(id)
);
DESC enrollments;
SHOW TABLES;
INSERT INTO users(username,email,password,role)
VALUES
('admin','admin@gmail.com','admin123','ADMIN');
INSERT INTO students(
first_name,
last_name,
email,
phone,
address
)
VALUES
(
'Mouli',
'Polnati',
'mouli@gmail.com',
'9876543210',
'Bangalore'
);
INSERT INTO courses(
course_name,
description,
duration
)
VALUES
(
'Java Full Stack',
'Spring Boot and React',
'6 Months'
);
INSERT INTO enrollments(
student_id,
course_id,
enrollment_date
)
VALUES
(
1,
1,
CURDATE()
);
SELECT * FROM users;
SELECT * FROM students;
SELECT * FROM courses;
SELECT * FROM enrollments;

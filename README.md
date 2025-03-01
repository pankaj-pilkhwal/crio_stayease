# crio_stayease

## Overview
StayEase, a simplified hotel booking API, is built using Spring Boot. It enables room bookings for hotels using the Stay-Ease application. Users can register, log in, manage hotel details, and book rooms through this API.

## Key Features
- MySQL database for data persistence
- User Registration and Login with Spring Security & JWT authentication
- Role-based access (CUSTOMER, MANAGER, ADMIN)
- Hotel management (get, create, update, delete)
- Room booking and cancellation

## Technologies Used
- Java 17
- Spring Boot
- Spring Security (JWT)
- MySQL

### Authentication
- **POST** `/auth/register`: Register a new user
- **POST** `/auth/login`: Authenticate and get a JWT token
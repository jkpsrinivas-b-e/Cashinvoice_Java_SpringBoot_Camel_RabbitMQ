**CashInvoice – Mini Order Processing System**

CashInvoice is a mini backend order processing system developed using Java and Spring Boot, following clean architecture and enterprise standards.
The project demonstrates REST APIs, role-based authorization, Apache Camel integration, and RabbitMQ messaging.

This project was implemented as part of an assessment to showcase backend development skills, system design, and integration capabilities.

**Tech Stack**
Java 17
Spring Boot 3
Spring Security + JWT Authentication
Apache Camel
RabbitMQ
RESTful APIs
Maven

**Features Implemented**
Authentication & Authorization
JWT-based authentication

Two roles implemented:
ADMIN
USER
Authorization Rules
ADMIN
  Can create orders
  Can view all orders
USER
  Can create orders
  Can view only their own orders

**Order Management**
1. Create a new order
2. Get order by Order ID
3. List orders by Customer ID
4. Role-based access control enforced at API level

**Apache Camel Integration**
File → RabbitMQ → Consumer flow
Orders can be read from files using Camel routes
Orders are published to RabbitMQ
Consumer route listens and processes messages

This demonstrates enterprise messaging and integrati

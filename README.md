# Customer Management Service

The Customer Management Service is a robust REST API designed for efficiently managing customer objects and contract types within a PostgreSQL database. It is built on the Spring Boot framework.

## Project Structure

The project follows a typical Spring Boot project structure:
    
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── customer
    │   │   │           └── management
    │   │   │               ├── controller
    │   │   │               ├── entity
    │   │   │               ├── repository
    │   │   │               ├── services
    |   |   |               ├── validators
    │   │   │               └── Application.java
    │   │   ├── resources
    │   │   │   ├── application.properties
    │   │   │   ├── static
    │   │   │   └── templates
    │   ├── test
    │   │   └── java
    │   │       └── com
    │   │           └── customer
    │   │               └── management
    │   │                   ├── controller
    │   │                   ├── service
    │   │                   └── ApplicationTests.java
    ├── target
    ├── pom.xml
    

   ## Project Overview
The primary goal of the Customer Management Service is to provide a flexible and efficient solution for managing customer objects and contract types. It supports Create, Read, Update, and Delete (CRUD) operations for both entities, making it versatile for various business use cases.

Customer Management: The system allows the creation, retrieval, modification, and deletion of customer profiles. Each customer can have various attributes, including name, account type, and associated business requirements. Additionally, detailed personal information such as sex, date of birth, and native place is supported through the "Customer" object.

Contract Type Management: The API also facilitates the management of contract types. Contract types are defined by a unique identifier (UUID) and a name. This feature enables organizations to define and categorize various contract types according to their needs.

## Technologies Used

![Java](https://img.shields.io/badge/Java-2F2625?style=for-the-badge&logo=coffeescript&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33FF?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/apache%20maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![RESTful Web Services](https://img.shields.io/badge/Rest%20APis-FF4F8B?style=for-the-badge&logo=amazonapigateway&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JUnit5](https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
![Git](https://img.shields.io/badge/Git-f44d27?style=for-the-badge&logo=git&logoColor=white)
![Eclipse IDE](https://img.shields.io/badge/eclipse%20ide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white)

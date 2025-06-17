<p align="center"><a href="https://spring.io/" target="_blank"><img src="https://i.imgur.com/ctXVIWc.jpg" width="400"></a></p>

# F1 Driver API

F1 Driver API is a modern, fully dockerized RESTful service for managing Formula 1 driver data. Built with Spring Boot and MySQL, it offers endpoints for CRUD operations, seamless local or containerized deployment, and comprehensive API documentation via Swagger and Postman.

## Technology Stack

- [**Java**](https://www.java.com/en/) ☕: 21 LTS
- [**Maven**](https://maven.apache.org/) 🛠️: 3.9.9
- [**Spring Boot**](https://spring.io/projects/spring-boot) 🍃: 3.5.0
- [**MySQL**](https://www.mysql.com/) 🐬: 9.3.0
- [**Docker**](https://www.docker.com/) 🐳

## Setup Instructions

### Prerequisites
- [**Docker**](https://docs.docker.com/get-started/get-docker/) and [**Docker Compose**](https://docs.docker.com/compose/)

### Installation
1. Clone the repository:
```
git clone https://github.com/MatiasCarabella/formula1-driver-API.git
cd formula1-driver-API
```

2. Build and start the application using Docker Compose:
```bash
 docker compose up --build 
 ```
_This will automatically build the Docker containers and start the application._

3. Access the application on http://localhost:9096/api. You should get the following response:
```json
{
    "message": "Ready to go! 🚦🏁",
    "status": 200
}
 ```

## Project Structure
```
formula1-driver-API/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── motorsport
│   │   │           └── formula1
│   │   │               ├── Formula1Application.java
│   │   │               ├── controller
│   │   │               │   ├── IDriverController.java
│   │   │               │   ├── IPingController.java
│   │   │               │   └── impl
│   │   │               │       ├── DriverController.java
│   │   │               │       └── PingController.java
│   │   │               ├── entity
│   │   │               │   ├── Driver.java
│   │   │               │   └── Response.java
│   │   │               ├── repository
│   │   │               │   └── DriverRepository.java
│   │   │               ├── response
│   │   │               │   └── ResponseHandler.java
│   │   │               ├── usecase
│   │   │               │   ├── ICreateDrivers.java
│   │   │               │   ├── IDeleteDriver.java
│   │   │               │   ├── IGetAllDrivers.java
│   │   │               │   ├── IGetDriversFromJson.java
│   │   │               │   ├── IGetDriversWithFilters.java
│   │   │               │   ├── IGetDuplicateDrivers.java
│   │   │               │   ├── IInitializeDatabase.java
│   │   │               │   ├── IIsDatabasePopulated.java
│   │   │               │   ├── IUpdateDriver.java
│   │   │               │   └── impl
│   │   │               │       ├── CreateDrivers.java
│   │   │               │       ├── DeleteDriver.java
│   │   │               │       ├── GetAllDrivers.java
│   │   │               │       ├── GetDriversFromJson.java
│   │   │               │       ├── GetDriversWithFilters.java
│   │   │               │       ├── GetDuplicateDrivers.java
│   │   │               │       ├── InitializeDatabase.java
│   │   │               │       ├── IsDatabasePopulated.java
│   │   │               │       └── UpdateDriver.java
│   │   │               └── util
│   │   │                   └── DocumentationHelper.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── data
│   │           └── drivers.json
│   └── test
│       └── java
│           └── com
│               └── motorsport
│                   └── formula1
│                       └── usecase
│                           └── impl
│                               ├── CreateDriversTest.java
│                               ├── DeleteDriverTest.java
│                               ├── GetAllDriversTest.java
│                               ├── GetDriversFromJsonTest.java
│                               ├── GetDriversWithFiltersTest.java
│                               ├── GetDuplicateDriversTest.java
│                               ├── InitializeDatabaseTest.java
│                               ├── IsDatabasePopulatedTest.java
│                               └── UpdateDriverTest.java
├── Dockerfile
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── .gitignore
│── LICENSE
└── README.md
```

## Usage
### API Endpoints

| Endpoint                     | Method | Description                              |
|------------------------------|--------|------------------------------------------|
| `/api`                        | **GET** | Check the service status with a message |
| `/api/drivers`                | **GET** | Get all drivers, with optional filters   |
| `/api/drivers`                | **POST**| Add new drivers to the database          |
| `/api/drivers/{id}`           | **PUT** | Update driver information by ID         |
| `/api/drivers/{id}`           | **DELETE**| Delete a driver by ID                   |
| `/api/drivers/initialize`     | **POST**| Initialize the database with sample data |

## API Documentation

### Swagger

Once the application is running, you can access the generated OpenAPI docs at:
- [http://localhost:9096/v3/api-docs](http://localhost:9096/v3/api-docs)

### Postman

You can also view and test the API using the following Postman docs:
- [Postman Documentation](https://documenter.getpostman.com/view/10146128/2s93JoxRFG)

## Running Code Quality and Tests Locally

If you have Java and Maven installed locally, you can use the following commands:

### Format code with Spotless
  ```sh
  mvn spotless:apply
  ```
### Run all tests
  ```sh
  mvn test
  ```

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [**@DaianaArena**](https://github.com/DaianaArena) 💜 for creating the banner image.

<p align="center"><a href="https://spring.io/" target="_blank"><img src="https://i.imgur.com/ctXVIWc.jpg" width="400"></a></p>

# F1 Driver API
This RESTful API allows users to fetch, add, update, and delete Formula 1 driver data, such as name, nationality, team, and statistics. Built with Spring Boot and MySQL, the app is fully dockerized for easy setup and deployment via Docker Compose, requiring no additional software installation.

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
    "status": "Ready to go! 🚦🏁"
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

[API Documentation (Postman)](https://documenter.getpostman.com/view/10146128/2s93JoxRFG)

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [**Spring Boot**](https://spring.io/projects/spring-boot) 🍃 for the backend framework.
- [**Docker**](https://www.docker.com/) 🐳 for containerized deployment.
- [**@DaianaArena**](https://github.com/DaianaArena) 💜 for creating the banner image.

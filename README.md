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
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── springBootProject/
│   │   │           └── formula1/
│   │   │               ├── config/
│   │   │               │   └── DataInitializer.java
│   │   │               ├── controller/
│   │   │               │   ├── DriverController.java
│   │   │               │   └── StatusController.java
│   │   │               ├── domain/
│   │   │               │   └── Driver.java
│   │   │               ├── repository/
│   │   │               │   └── DriverRepository.java
│   │   │               ├── response/
│   │   │               │   └── ResponseHandler.java
│   │   │               ├── service/
│   │   │               │   └── DriverService.java
│   │   │               └── Formula1Application.java
│   │   └── resources/
│   │       ├── data/
│   │       │   └── drivers.json
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── springBootProject/
│                   └── formula1/
│                       ├── controller/
│                       │   └── DriverControllerIntegrationTests.java
│                       └── Formula1ApplicationTests.java
├── pom.xml
├── Dockerfile
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── LICENSE
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

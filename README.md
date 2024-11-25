<p align="center"><a href="https://spring.io/" target="_blank"><img src="https://i.imgur.com/ctXVIWc.jpg" width="400"></a></p>

# F1 Driver API
This RESTful API allows users to fetch, add, update, and delete Formula 1 driver data, such as name, nationality, team, and statistics. Built with Spring Boot and MySQL, the app is fully dockerized for easy setup and deployment via Docker Compose, requiring no additional software installation.

## Setup Instructions

### Prerequisites
- [Docker](https://docs.docker.com/get-started/get-docker/) and [Docker Compose](https://docs.docker.com/compose/)

### Installation
1. Clone the repository:
```
git clone https://github.com/MatiasCarabella/formula1-driver-API.git
cd formula1-driver-API
```

2. Build and start the application using Docker Compose:
```bash
 docker-compose up --build 
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
dealership-ai-chat/
└── app/
|   ├── __init__.py      # Package initialization
|   ├── main.py          # FastAPI application
|   ├── chatbot.py       # Chatbot implementation
|   ├── database.py      # Database connection and setup
|   └── models.py        # Database models and schemas
├── Dockerfile           # Dockerfile for the app
├── docker-compose.yml   # Docker Compose configuration
├── requirements.txt     # Python dependencies
├── init.sql             # Database initialization script
├── .env.example         # Environment variables
├── .gitignore           # Files to ignore in Git
├── LICENSE              # MIT license
└── README.md            # You're reading it now
```

## Usage
### API Endpoints

You should see something like this:
<p align="center"><img src="https://i.imgur.com/xO7OUPt.png"></p>

You're all set! 🏎️✨ Now you can refer to the <a href="https://documenter.getpostman.com/view/10146128/2s93JoxRFG" target="_blank">**API's Documentation**</a> for more details.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

- [**Spring Boot**](https://spring.io/projects/spring-boot) 🍃 for the backend framework.
- [**Docker**](https://www.docker.com/) 🐳 for containerized deployment.
- [@DaianaArena](https://github.com/DaianaArena) 💜 for creating the banner image.

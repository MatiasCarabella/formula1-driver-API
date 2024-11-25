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

This will automatically build the Docker containers, set up a MySQL database with the name `formula1`, and start the application. The database will be configured with default credentials:
- **MYSQL_USER**: mysqluser
- **MYSQL_PASSWORD**: secret
- **MYSQL_ROOT_PASSWORD**: rootpassword

**All done!**  
By default, the app will run on port **9096**.

## Using the API

To ensure everything is working fine, you can access the API endpoint at http://localhost:9096/api/v2/drivers

You should see something like this:
<p align="center"><img src="https://i.imgur.com/xO7OUPt.png"></p>

You're all set! 🏎️✨ Now you can refer to the <a href="https://documenter.getpostman.com/view/10146128/2s93JoxRFG" target="_blank">**API's Documentation**</a> for more details.

## Closing thoughts
I hope you enjoy this little passion project of mine, and thanks to [@DaianaArena](https://github.com/DaianaArena) for creating the banner image!

To whoever read this far, thank you very much and best regards!

_<p align="right">Matías Carabella - Back-end Developer</p>_

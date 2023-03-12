<p align="center"><a href="https://spring.io/" target="_blank"><img src="https://i.imgur.com/ctXVIWc.jpg" width="400"></a></p>

## Formula 1 CRUD API - Spring Boot & MySQL

## Index
  * [General description](#general-description)
  * [Technical specification](#technical-specification)
  * [Installation](#installation)
    + [Maven](#maven)
    + [Docker compose](#docker-compose-)
  * [Using the API](#using-the-api)
  * [Closing thoughts](#closing-thoughts)


## General description
This proyect is a REST API of F1 drivers that allows the user to fetch, add, update and delete them as you wish.

## Technical specification
- **Java** JDK version **11.x** _(Mandatory)_
- **MySQL** version **8.x** _(Optional)_*
- **Docker** _(Optional)_*

\* Between Docker & MySQL, pick one.


## Installation

1. Generate the folder where you'll download the project, access it from the terminal/console of preference and run the following commands:

```
git init
git pull https://github.com/MatiasCarabella/formula1CRUD.git
```

2. Spin up a MySQL server with the default credentials and a 'formula1' database. We can achieve this with a single **_docker_** command:
```
docker run --name mysql -p 13306:3306 -e MYSQL_DATABASE=formula1 -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=secret -e MYSQL_ROOT_PASSWORD=secret -d mysql
```
If you do not have docker installed, you can run a **_mysql_** command like this one:
```
mysql -u root -p[YOUR PASSWORD] -e "create database formula1; CREATE USER 'mysqluser'@'localhost' IDENTIFIED BY 'secret'; GRANT ALL PRIVILEGES ON formula1.* TO 'mysqluser'@'localhost';"
```
Just make sure to change the default **MYSQL_PORT** on your _**application.properties**_ file to whichever port your local MySQL server is running _(**3306** by default)_


3. Build the **.jar** file using Maven with the following command:
```
& ./mvnw.cmd install -f pom.xml
```

Now, in order to get the application up and running, you have two options:
### Maven

Run the following command to run the spring boot app:

```
& ./mvnw.cmd spring-boot:run
```

**All done!**
By default, the app will run on port **8086**

### Docker compose 🐋
Requirements:
- <a href="https://www.docker.com/">**Docker**</a> installed.

1. Run the following command in the project root folder to build & start the application:
```
docker compose up
```

**All done!**
By default, the app will run on port **9096**


## Using the API

To make sure everything is working fine, you can access http://localhost:9096/api/v2/drivers/get _(You might have to change the **PORT** depending on how you chose to run the project)_

You should see something like this:
<p align="center"><img src="https://i.imgur.com/xO7OUPt.png"></p>

You're all set! 🏎️✨ Now you can refer to the <a href="https://documenter.getpostman.com/view/10146128/2s93JoxRFG" target="_blank">**API's Documentation**</a>

## Closing thoughts
I hope you enjoy this little passion project of mine, and thanks to [@DaianaArena](https://github.com/DaianaArena) for creating the banner image!

To whoever read this far, thank you very much and best regards!

_<p align="right">Matías Carabella - Back-end Developer</p>_

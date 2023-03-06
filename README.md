<p align="center"><a href="https://spring.io/" target="_blank"><img src="https://i.imgur.com/ctXVIWc.jpg" width="400"></a></p>

## Formula 1 CRUD API - Spring Boot & MySQL

## Index
  * [General description](#general-description)
  * [Technical specification](#technical-specification)
  * [Installation](#installation)
    + [PHP | Composer | Artisan](#php--composer--artisan)
    + [Docker compose](#docker-compose-)
  * [Using the API](#using-the-api)
    + [Notes](#notes)
  * [Project structure](#project-structure)
  * [Tests](#tests)
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

2. Spin up a MySQL database with the default credentials and a 'formula1' database. We can achieve this with a single **docker** command:
```
docker run --name mysql -p 13306:3306 -e MYSQL_DATABASE=formula1 -e MYSQL_USER=mysqluser -e MYSQL_PASSWORD=secret -e MYSQL_ROOT_PASSWORD=secret -d mysql
```
If you do not have docker installed, 

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
By default, the app will run on port 8086**

### Docker compose 🐋
Requirements:
- <a href="https://www.docker.com/">**Docker**</a> installed.

1. Run the following command in the project root folder to build & start the application:
```
docker compose up
```

**All done!**


## Using the API

Onto the _fun_ bit, now we're able to use the API. The URL of the endpoint is the following:

_**<p align="center">http://localhost:8000/api/youtube-search</p>**_

If we access this URL from a web browser, we'll see something like this:

<p align="center"><img src="https://i.imgur.com/pbnIuWg.png"></p>

That's to be expected _(since we haven't specified the text to search for)_, but it gives us confirmation that the API is running successfully!

Now, to effectively test the service we can use a client like <a href="https://www.postman.com/">**Postman**</a>:

<p align="center"><img src="https://i.imgur.com/LcEnhgM.png"></p>
As you can see, it's simply a matter of sending a JSON body with the text to search for in the 'search' field:

```json
{
    "search": "Paradise"
}
```

The only other mandatory element is the **api_key**, which can be generated on the <a href="https://console.developers.google.com/apis/credentials">**Google Cloud Platform**</a>.

This can be configured in two ways:

- As a Request **header** _('api_key': 'XXXXXXXXXXXXX')_

- As the value of the **_API_KEY_DEFAULT_** variable from the projects' **env** file _(When not sent as a header, it is read from here)_

```
API_KEY_DEFAULT=XXXXXXXXXXXXX
```

In both cases, if an invalid **api_key** is set _(or if there's no api_key)_, an error will be displayed as returned by the Google API:
<p align="center"><img src="https://i.imgur.com/1HWHXzm.png"></p>

Finally, as we mentioned earlier, the optional fields **_results_per_page_** and **_page_token_** are also available.

<p align="center"><img src="https://i.imgur.com/j5ZgZKa.png"></p>

### Notes

- If an invalid value is entered in the **_results_per_page_** parameter, it defaults to **10**.
- If an invalid value is entered in the **_page_token_** or **_api_key_** parameters, an error message will be displayed as returned by the Google API.

## Project structure

The bulk of the application's logic is on the following files:

`routes->api.php`

`app->Http->Controllers->YoutubeController.php`

`app->Services->YoutubeServices.php`

`tests->Feature->YoutubeTest.php`

In order to facilitate the understanding of the code, everything is commented accordingly:

<p align="center"><img src="https://i.imgur.com/X4R7C6M.png"></p>

## Tests

There are some tests that can be run to make sure the application functions properly. These are:

1. Validate that an example query returns HTTP status 200 - OK.
2. Validate that the JSON response format matches to the stipulated one.

To execute them, simply run the following command from the project root folder:
```
php artisan test tests/Feature/YoutubeTest.php
```
<p align="center"><img src="https://i.imgur.com/cBc7Iox.png"></p>

## Closing thoughts
I am happy to say that the _‘Have fun!’_ bit from the Challenge's description was also achieved, I really enjoyed the project!

To whoever read this far, thank you very much and best regards!

_<p align="right">Matías Carabella - Back-end Developer</p>_

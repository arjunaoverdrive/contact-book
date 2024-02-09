# Contact book

Spring Boot Web MVC application to manage contacts.

## Technologies used:
Java 17, Spring Boot, Spring Web MVC, Thymeleaf, Gradle, Docker

### Prerequisites
Java 17 or Docker to run the app in a container.


### Installation steps
#### Local installation
1. Clone the project repository.
2. Open a terminal/CLI and navigate to the project folder.
3. By default, the app is configured to use a database instance that runs in Docker environment. Thus, if you want
   to use a local database instance, create a new schema and the table using the script form the `docker/init.sql` file.
4. Remember to create a user with access to that database. 
5. Specify the user credentials and database connection URL in the `src/main/resources/application.yml` file. 
Alternatively, you can set them as the DB_USER, DB_PSWD, and DB_URL parameters when running the application (Step 8).
6. Run `./gradlew build`.
7. As a result, it'll build the application, and place the resulting artefact (jar file) in the ./build/libs folder.
8. Run `java -jar ./build/libs/contact-book-0.0.1-SNAPSHOT.jar`.


#### Docker
1. Clone the project repository.
2. Open terminal/CLI and navigate to the project folder.
3. Run `./gradlew build`.
4. Run `docker build -t contact-book .` to build an image.
5. Navigate to the docker folder: `cd docker`
6. Run `docker-compose up` to run a container with the application.

#### Usage
The application listens to port 8080, the main endpoint is the root (/). 
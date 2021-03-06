# MySpringBoot
This repository is a playground for learning and trying new ideas with Spring Boot

### Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/MySpringBoot.svg?branch=master)](https://travis-ci.org/harishkannarao/MySpringBoot)

### Required Software and Tools
* Java Version: Oracle Java 1.8.0_31 (Execute **_java -version_** in command line after installation)
* Apache Maven Version: 3.2.5 (Execute **_mvn -version_** in command line after installation)
* Docker Version: Docker version 17.03.1-ce, build c6d412e (Execute **_docker --version_** in command line after installation)
* Docker Compose Version: docker-compose version 1.11.2, build dfed245 (Execute **_docker-compose --version_** in command line after installation)
* Chrome (Windows & Mac OS) Browser / Chromium (Linux OS) Browser: 62
* chromedriver: 2.32 [chromedriver installation steps](https://blogs.harishkannarao.com/2018/01/installing-chromedriver-for-selenium.html)
* Git Client: Any latest version (Execute **_git --version_** in command line after installation)
* Integrated Development Environment: Any version IntelliJ Idea or Eclipse

### Docker dependencies
Docker dependencies needs to be started using docker-compose before the build
##### Pull the latest images of docker services
    docker-compose -f docker_local/docker-compose.yml pull
##### Start docker services
    docker-compose -f docker_local/docker-compose.yml up --build -d
##### Stop docker services
    docker-compose -f docker_local/docker-compose.yml down -v

### Running full build
* Start Postgresql database through docker (steps given below)
* Execute ```mvn clean install```

### Run full build (headless mode)
To run the tests with chrome in headless mode

    mvn clean install -DchromeHeadless=true

### Generate artifacts only
Will skip unit tests, integration tests and docker commands
    
    mvn clean install -DskipTests=true

### Running multiple spring boot application in parallel
* Open terminal in root folder
* Execute the following to start rest third party stub and service ```mvn exec:exec@run-third-party antrun:run@wait-for-ping spring-boot:run -pl RestExample/RestServiceAndConsumer```
* Verify all applications are running ```curl --header "Content-Type: application/json" -X GET "http://localhost:8080/quote"```

### Docker Commands
#### Check PostgreSql Database Container Logs
    docker logs -t -f  springboot-jdbc-postgres
#### Connect to PostgreSql Database
    docker run --network=docker_local_main -it --rm postgres:10 psql --host springboot-jdbc-postgres --username myuser --dbname myuser --port 5432
    
Enter password `superpassword`
    
Type '\q' to quit the terminal and container
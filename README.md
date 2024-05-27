<!-- # Read Me First
The following was discovered as part of building this project:

* The original package name 'lan.home.dev.microservice-new' is invalid and this project uses 'lan.home.dev.microservicenew' instead. -->

# Project Overview

This is a microservice project that I worked on to re-familiarize myself with Java and SpringBoot

The primary function is a microservice backend consisting of:
- A product service which uses the document database MongoDB to keep a list of unique items that would be sold.
- An inventory service to track what products are in stock.
- An order service to fullfil or reject possible orders made to the store.

To support this primary function I have also Included:
- A discovery server to connect instances of ```Order Service``` to ```Inventory Service```
- An API gateway using ```spring-cloud-starter-gateway``` as a loadbalancer and to provide security to internal communications.
<!-- - To Finish Writing when I add more -->

## Technologies Used <!--Add as you go -->
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

# Getting Started <!--Change as you go -->
<!-- V1 -->
Compile and build the packages to with

```javac <FILENAME>```

followed by running the application files.

```java <FILENAME>```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)


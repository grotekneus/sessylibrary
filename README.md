# SESsy Library

#### Sessy Library?

> A Library Management Simulation WebApp for the 'SES' (Software Engineering Skills) course

Course Home: https://brainbaking.com/teaching/ses/

### Minimum Requirements

* Java 10.x or higher (see `build.gradle`)
* Gradle 5.6.x or higher (see `gradlew --version`output)

[Upgrading the Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) can be done using:

> `$ ./gradlew wrapper --gradle-version 6.0`

### Components

#### Backend

[DropWizard](http://www.dropwizard.io/en/stable/) Java RESTful API that integrates Jetty/Jackson/... functionality in a nice all-in-one package. 
This means executing the jar automatically bootstraps a webserver, and REST calls can be easily added using `javax.ws` annotations. See `be.kuleuven.sessylibrary.api` classes for examples.

#### Frontend

[VueJS](https://vuejs.org) Progressive single-page JS framework that calls the above API endpoints and is served from the very same webserver thanks to the `dropwizard-assets` module. 

The compiled vuejs webpage should be copied to `src/main/resources/assets` - `index.htm` is served at `/`.

Students **do not need to edit** the frontend part of this application and may consider this as a 'given'. 

### What should I do with this?

TODO
 
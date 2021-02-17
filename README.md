# SESsy Library

[![travis build](https://travis-ci.org/KULeuven-Diepenbeek/sessylibrary.svg?branch=master)](https://travis-ci.org/KULeuven-Diepenbeek/sessylibrary)

#### Sessy Library?

> A Library Management Simulation WebApp for the 'SES' (Software Engineering Skills) course

Part of the course [Software Engineering Skills](https://brainbaking.com/teaching/ses/) at Faculty of [Engineering Technology](https://iiw.kuleuven.be), KU Leuven, Belgium.

### Minimum Requirements

* Java 10.x or higher (see `build.gradle`)
* Gradle 5.6.x or higher (see `gradlew --version`output)

[Upgrading the Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) can be done using:

> `$ ./gradlew wrapper --gradle-version 6.3`

### Components

#### Backend

[DropWizard](http://www.dropwizard.io/en/stable/) Java RESTful API that integrates Jetty/Jackson/... functionality in a nice all-in-one package. 
This means executing the jar automatically bootstraps a webserver, and REST calls can be easily added using `javax.ws` annotations. See `be.kuleuven.sessylibrary.api` classes for examples.

**Building**:

1. One can create a big jar using the `./gradlew shadowjar` command. Run this big whopper using `java -jar build\libs\sessylibrary-1.0-SNAPSHOT-all.jar server app.yml`.
2. Simpler: just instruct gradle to boot locally using `./gradlew serve`.
3. Using an IDE: Run the class `be.kuleuven.sessylibrary.SessyLibApplication` with the arguments "server app.yml". 

After that, your project should be running at `http://localhost:8080` now.

#### Frontend

[VueJS](https://vuejs.org) Progressive single-page JS framework that calls the above API endpoints and is served from the very same webserver thanks to the `dropwizard-assets` module. 

The compiled vuejs webpage should be copied to `src/main/resources/assets` - `index.htm` is served at `/`.

Students **do not need to edit** the frontend part of this application and may consider this as a 'given'. 

Building and running the Frontend source:

1. `npm install` in the frontend subdir
2. `npm run serve` in the frontend subdir

Your project should be running at `http://localhost:8080` now.

### What should I do with this?

1. The application will be used during labs as part of multiple exercises. 
2. Fix the Open [Github Issues](https://github.com/KULeuven-Diepenbeek/sessylibrary/issues). You will need to create a Fork, as this repository is the example one where no merges will be accepted. 

### Resources

* [DropWizard: Getting Started](https://dropwizard.io/en/stable/getting-started.html) 


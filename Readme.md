#Test For .tribe - back End Test

Application is Build using Spring Boot And Mockito & Junit for testing


## Features

* Json api's: /api
    + /post : retrieve Posts
    + /ping : Ping server is up
* Exceptions :
    + Advice Exception handler => handle Most our Api calls Exception
    + MethodArgumentTypeMismatchException And InvalidParameterException for 
    response like {"error": "sortBy parameter is invalid"} return code 400
    + MissingServletRequestParameterException for reponse like 
    {"error": "Tags parameter is required"} return code 400
    + ConsumedApiNotFoundException return internal server error in case ---Api is down
* Caching
    + Caching calls to --- api Based on Tag
    + Caching calls to PostService based on tags sortBy and direction
* Logging
    + logging calls to Controllers
    + logging calls to services
    
## Configuration 

* application.yml : Defining Scope & Server Port & And url to HatchWays API
* configuration/ApiPath : our internal api's paths
* ---Api : --- Api path scope
* PropertyResolver : expose application.yml values to the app

## Testing
* Mockito
* Junit ( sadly i didn't have enough time to scale the testing more)
i ve done just a simple class to test some api calls
## Docker 
* Dockerfile describe : layers of image 
* build : docker build -t tribe/blog .
* run : docker run -p 9000:9000 tribe/blog

## Features to ADD
* DTO to be exposed in Controller
* Converter between Classes And our DTO's 
* more tests ^-^
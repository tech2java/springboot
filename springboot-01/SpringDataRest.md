Spring Data Rest:
-----------------
- Automatically generates the rest API(CRUD operations) for repository.
- implementation '_org.springframework.boot:spring-boot-starter-data-rest_'
- [http://localhost:9090/profile](http://localhost:9090/profile)- check for all REST APIS exposed.(derived methods also populated)
- REST APIs will be generated any kind of implementation like spring data jap, spring data mango etc.
- http://localhost:9090/profile/employees - provides metadata
- SAFE - if no updates , then that operation treated as SAFE operation(GET operations)
- UNSAFE(NON IDEMPOTENT) - Creation,Update usually treated as UNSAFE operations.
- IDEMPOTENT - if we perform operation n number of time, it will not affect the data(ex: delete a record 1 time or multiple time)
- [http://localhost:9090/employees/search](http://localhost:9090/employees/search) - Gives the all apis for that repository.
- Can use **Spring HATEOAS** to build the links similar to above
- http://localhost:9090/employees/search/findByEmpAge?empAge=37
- look at @Transient annotation

HAL:(Hypertext Application Language)
-------------------------------------
- We can check the HAL explorer by opening the URL [http://localhost:8080/](http://localhost:8080/) in the browser.
- The UI is an angular based web application that let you easily explore and HAL-FORMS based HTTP response.
- implementation '_org.springframework.data:spring-data-rest-hal-explorer_'
- Give HAL url to other developers to understand the all apis.
- spring.data.rest.basePath=**/data-api**
- [http://localhost:9090/data-api/](http://localhost:9090/data-api/)
- Spring Data Rest uses plural names for displaying api names in HAL explorer. (**Eg: employees**)
- We can override path names using _@RepositoryRestResource(**path = "employee-api"**)_
- http://localhost:9090/data-api/profile - check here, in HAL it's not reflecting.
- Use _@RepositoryRestResource(**exported = false**)_ for not exposing the endpoints for repository


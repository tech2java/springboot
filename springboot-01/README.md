SpringBoot-01
--------------
1. Changing the default server port & **context path** of SpringBoot Web application:(server.port=9090 , spring.servlet.context-path=/example1)
2. **Random server port** number inside SpringBoot: (server.port=0 , run multiple instances for testing)
3. Print the autoconfiguration report on the console :(debug=true, +ve matches,-ve matches,exclusions, unconditional classes)
4. Demo of Spring Boot **Autoconfiguration**
5. Creating simple web application using Spring Boot
6. Introduction to **Lombok** library
7. Demo of **@Slf4j** annotation from Lombok library
8. Accepting Query Params using **@RequestParam** annotation
9. Accepting Path Params using **@PathVariable** annotation
10. Introduction to **@ControllerAdvice** & **@ExceptionHandler** annotations


SpringBoot Starters:
-------------------
SpringBoot groups related dependencies used for a specific purpose as starter projects.
Example: spring-boot-starter-web

AutoConfiguration:
------------------
1. Based on the dependencies present in the class path , spring Boot guess and auto-wire the spring beans,property configurations etc.
2. To achieve autoconfiguration spring boot uses "convention-over-configuration"

Actuator & Dev Tools:
---------------------
1. spring boot provides predefined list of actuator endpoints.Using these production ready end points, we can monitor app health and metrics etc.
2. Devtools provides features such as automatic detection of application code changes.LiveReload server to automatically refresh any HTML changes to the browser.


@SpringBootApplication
//@SpringBootConfiguration
//@ComponentScan
//@EnableAutoConfiguration

- Multiple Paths
`@RequestMapping(value = {"/","/welcome","/demo"})`


- @RestController // @Controller+@ResponeBody

http://localhost:8080/welcome

Lombok:
---------
  compileOnly 'org.projectlombok:lombok'
  annotationProcessor 'org.projectlombok:lombok'
- Removes boilerplate code(getters & setters etc) from POJO.
- It will auto-generates the bytecode into .class files based on the annotations that we used. 
- @Data=  @Getter,@Setter,@NoArg.. @RequireArgCo..@ToString,@EqualsAndHashCode


1. @RequestBody : It retrieves only Request body
2. @RequestHeader : It retrieves only request headers
3. ResponseEntity : Response body + response headers
4. RequestEntity : Request headers & Request body


Validation with SpringBoot:
--------------------------
- implementation 'org.springframework.boot:spring-boot-starter-validation'
- https://beanvalidation.org/
- Jakarta (javax.validation.constraints.*) @Min,@Max,@Size,@Email,@NotBlank,@NotNull etc.
- hibernate(org.hibernate.validator.constraints.*) @Length,@Range ,@URL etc.
- Verify library for more validations
- note: check validations for spring mvc with modelAttribute etc.(Handling exceptions varies from mvc to rest)
- currently looking at rest-controllers 
- make sure the validations adding to int, string are appropriate.
- Add **@Validated** on top of rest controller and **@Valid** before @RequestBody.
- for methods which are taking single param then use **@Pattern** annotation

Exception Handling:
-------------------
- Write logic to handle global exceptions( trigger email or store in DB for tracking purpose)
- ResourceNotFoundException
- MethodArgumentNotValidException - extend **ResponseEntityExceptionHandler** and implement required method.
- It will look first for exact matching method in exception handler.

- Implement global error logic for Rest Services using @RestControllerAdvice
  @RestControllerAdvice(annotations=RestController.class), @exceptionHandler
  How to handle both Controller & RestController exceptions
  Create separate class for each - and use @Order(1) annotation to resolve the conflicts

`  @ControllerAdvice
  public class GlobalExceptionHandler {


    //this is for User Defined Exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        Response response=new Response();
        response.setStatusCode("400");
        response.setStatusMessage("Resource NotFound");

        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    //this is for global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handledGlobalExceptions(Exception exception, WebRequest webRequest){

        Response response=new Response();
        response.setStatusCode("500");
        response.setStatusMessage(exception.getMessage());

        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
`

XML Response:
-------------
- By default json format
- implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml'
- @RequestMapping(value = "/employee/api",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) - top of restcontroller
- Send the request with header Accept - application/xml 

content filter:
---------------
- Content filter inside Rest Services using @JsonIgnore annotation
- Response content filtering , @JsonProperty(..) , @JsonIgnore , @JsonIgnoreProperties(value={}) - top of the class


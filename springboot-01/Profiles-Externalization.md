Externalizing Properties
------------------------
- Spring Boot allows us to work with same application code in different environment.
- Variety of external configuration sources include **java property files**, **YML files**, **environment variables** and **command-line arguments**.
- Spring Boot default reads the properties from application.properties/yml file.But,it is possible to read it from other property files also.

Config/Properties Preferences:
-----------------------------
- With values from lower items overriding earlier ones

1. Properties present in application.properties
2. **OS Environment** variables
3. Java System properties(**System.getProperty()**)
4. JNDI attributes from **java:comp/env**
5. ServletContext init params
6. ServletConfig init params
7. **Command line** arguments

Reading Properties with @Value:
-------------------------------
- @Value annotation leverages **SpEL expression** to read the configurations from properties file.

`

    @Controller
    public class WelcomeController {

    @Value("${ems.address}")
    private String emsAddress;
`

Reading properties from Environment:
-----------------------------------
- Environment can read the both configurations from **application.properties** and **environment** variables.

`

    @Autowired
    private Environment environment;

    System.out.println("ems.address::"+environment.getProperty("ems.address"));
    System.out.println("JAVA_HOME::"+environment.getProperty("JAVA_HOME"));
`

Read properties with @ConfigurationProperties:
---------------------------------------------
- spring boot allow to load the **properties which are logically together** into a java bean.
- Make sure to use the **names inside bean and properties file**.

- **@PropertySource("classpath:test.properties")** - reading configurations other than application.properties file.
- **@ConfigurationProperties(prefix="ems"**) - prefix value needs to be considered while reading the properties into a given bean.
- **@Validated** - perform validations on fields.


`

    @Component("emsProperties")
    @Data
    @ConfigurationProperties(prefix = "ems")
    @Validated
    public class EMSProperties {

    @Size(min = 5,max = 10,message = "Address should be between 5 chars and 10 characters")
    private String address;

    private Map<String,String> contact;

    private List<String> branches;



}
`
- We can configure multiple configuration files


`
    @Configuration
    @PropertySources(
        {
        @PropertySource(value = "classpath:test1.properties" ,ignoreResourceNotFound = true),
        @PropertySource("classpath:test2.properties")
        }
    )
    public class AppConfig {
    }
`

Profiles:
---------
- Allowing a bunch of configurations based on the active profile.
- Profiles are used to set up the application based on environments.
- Also , can create a bean based on profile.
- The **default profile** is always **active**. 
- Spring Boot loads all properties from **application.properties** into **default profile**
- prod profile - **application_prod.properties**
- uat profile - **application_uat.properties**

- Setting profile as active : **spring.profiles.active=uat**  - The following 1 profile is active: "uat" in logs
- **spring.config.activate.on-profile=uat**  - Telling spring boot framework consider the properties present in uat property files  - add this in uat properties file
- In UAT file, we can override properties from application.properties and also can add new properties in uat properties file.
- **spring.config.import=application_uat.properties** -  add this in application.properties file

`
    application.properties 
    spring.config.import=application_uat.properties,application_prod.properties 
    spring.profiles.active=uat    <use this approach when you are in local>
`

`
    application_uat.properties 
    spring.config.activate.on-profile=uat
`

Activating a profile:
------------------------
1.  By adding spring.profiles.active=uat in application.properties
2.  Using environment variables
    export **SPRING_PROFILES_ACTIVE=uat**
    java -jar testapp.jar
3.  Using java system property
    _java_ "-**Dspring-boot.run.profiles=uat**" -jar test.jar - Running application using java command
    _mvn_ spring-boot:run "-Dspring-boot.run.profiles=prod"   - Running application using mvn command
4. Activate by programmatically - **setAdditionalProfiles("prod")** in SpringApplication class.
5. @ActivateProfiles while doing testing
   @SpringBootTest
   @ActiveProfiles({"uat"})


- Applicable above also: Config/Properties Preferences
1. Properties present in application.properties
2. **OS Environment** variables
3. Java System properties(**System.getProperty()**)
4. JNDI attributes from **java:comp/env**
5. ServletContext init params
6. ServletConfig init params
7. **Command line** arguments


- Can activate multiple profiles at the same time - but not recommended approach. Use single profile
- ![](C:/Users/HP/Desktop/spring-profiles.png)

IntelliJ:
---------
1. System property(program arguments)   --spring.profiles.active=prod  
2. Environment variables - SPRING_PROFILES_ACTIVE=uat
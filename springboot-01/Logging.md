Logging:
--------
1. Most of the configurations and logging is done by spring boot default.
2. Types of logging
  `FATAL(logback won't support)
  ERROR
  WARN
  INFO
  DEBUG
  TRACE`
3. In java, java util logging, log4j2, slf4j , logback are there, spring boot uses Logback as default logging if we add starters.
4. if other dependencies having different logging libraries,in that case also spring boot will support that.
5. Spring Boot , by default ERROR, WARN and INFO will be logged to the console.But, we can change them based on our requirement and environment.

Enable debug and trace logging:
------------------------------
- Add properties **debug=true** for debug and **trace=true** for tracing.Alternatively, we can add flags while starting the application(**java -jar test.jar --debug**).
- Add --debug in _program argument_ section of _Run Configuration_ while starting the application from _Intellij_
- if we enable WARN, then all above severities(WARN,ERROR) will be printed on the console.
- We can control logging level at package level also (**logging.level.root=ERROR logging.level.com.tech2java.controller=DEBUG**).Add them in application.properties file.
- With spring boot, can enable logging for group level.(add packages to group and then apply log level to that group)

`

  #Telling spring boot to enable info for all libraries
  logging.level.root=INFO

  #Initialize log group
  logging.group.ems_error=com.tech2java.ems.controller, com.tech2java.ems.exception

  #Set the log level to the group
  logging.level.ems_error=ERROR

`
- To print logs in color (**spring.output.ansi.enabled=ALWAYS**)
- 024-06-02T14:47:49.057+05:30  INFO 15136 --- [           main] com.tech2java.ems.EMSApplication         : Starting EMSApplication using Java 17.0.5 with PID 15136
- 024-06-02T14:47:49.057+05:30(**Date AND Time**)  
- INFO(**Log Level**) 
- 15136(**process Id**) 
- ---(**separator to distinguish the start of the log messages**) 
- [           main] (**Thread name**) 
- com.tech2java.ems.EMSApplication(**logger name**)         : 
- Starting EMSApplication using Java 17.0.5 with PID 15136(**The log message**)
- spring boot default writes the logs to **console** only, to write logs into any file then create **logback.xml** and add the logging requirements in it. 

-Lombok has annotations to help developers with logging.
- @Slf4j- generates log variable directly and use it in the class.

`
  
    @Slf4j
    public class EmployeeRestController {
    }
  
    Will generate below 
     public class EmployeeRestController {
       private static final Logger log=LoggerFactory.getLogger(EmployeeRestController.class);
     }

`

- Similarly, @CommonsLog , @Log4j2 will generate the log variable form the respective libraries. 
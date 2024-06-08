Consuming REST Services:
-----------------------
1. **OpenFeign**: Tool offered by spring cloud.Just write the interfaces not implementation code.
2. **RestTemplate**: has been using since spring 3, and it's deprecated in favour fo WebClient.
3. **WebClient**: Created as part of spring reactive module, supports all modes of invocations like sync,async(non-blocking)

Open Feign:
----------

`

    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-webflux'  - webclient
    implementation 'org.springframework.cloud:spring-cloud-starter-loadbalancer' -openfeign
    implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'  -openfeign

`


`
    
    @FeignClient(name="employee" ,url = "http://localhost:9090/emp/api",configuration = ProjectConfig.class)
    public interface EmployeeProxy {

    @RequestMapping(method = RequestMethod.GET,value = "/retrieve")
    @Headers(value = "Content-Type:application/json")//optional
    public Employee retrieveEmployeeByName(@RequestParam("name") String name);
}
`

`

    @EnableFeignClients(basePackages = "com.tech2java.rest_consumer.proxy")

`

Rest Template:
--------------
`
        

    @Bean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
        return restTemplateBuilder/*.basicAuthentication("","")*/.build();
    }

        

    @PostMapping("/saveEmp")
    public ResponseEntity<Employee> saveEmp(@RequestBody Employee employee){

    String uri="http://localhost:9090/emp/api/save";

    HttpHeaders headers=new HttpHeaders();
    headers.add("invocationForm","restTemplate");
    HttpEntity<Employee> httpEntity=new HttpEntity<>(employee,headers);
    
    ResponseEntity<Employee> responseEntity=restTemplate.exchange(uri, HttpMethod.POST,httpEntity,Employee.class);
    //last parameter - Response after calling exchange() method.
    return responseEntity;

    }
`

WebClient:
---------

- Due to non-blocking nature, we need to wrap the obejcect.
- With WebClient, can't send & receive tje object directly.
- It communicates asynchronously without blocking threads.

`
        


    @Bean
    public WebClient webClient(){
        return WebClient.builder().
                /*filter(ExchangeFilterFunctions.basicAuthentication("","")).*/
                build();
    }

    @PostMapping("/saveEmployee")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee){

    System.out.println("Inside saveEmployee method: using WebClient:::");
    String uri="http://localhost:9090/emp/api/save";
    return webClient.post().uri(uri)
            .body(Mono.just(employee),Employee.class)
            .header("invocationForm","webClinet")
            .retrieve()
            .bodyToMono(Employee.class);
    }
`
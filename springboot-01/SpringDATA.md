Spring Data:
-------------
1. Spring ecosystem project that simplifies the development of persistence layer.
2. it provides the implementation of persistence technology that we use in the application.
3. JDBC(jpa/hibernate),MongoDB,Cassandra,etc-->Spring Data -> spring Application Logic
4. Repository(I)- it's marker interface - ability to using springData- we can't perform any operations
5. CrudRepository(I) - Can perform CRUD operations.
6. PagingAndSortingRepository(I) - extends CrudRepository and adds operations like sorting and paging.
7. It provides multiple interfaces the extends one another by following the principle called "interface segregation"
8. Repository->CrudRepository->PagingAndSortingRepository-.(JpaRepository(I),MongoRepository(I))

H2 database:
------------
- runtimeOnly 'com.h2database:h2'
- default console: /h2-console
- username : sa and password ""
- spring.h2.console.path - to give a new path for h2 console.
- schema.sql and data.sql

1. spring.datasource.url=jdbc:h2:mem:testdb
2. spring.datasource.driverClassName=org.h2.Driver
3. spring.datasource.username=sa
4. spring.datasource.password=
5. spring.jpa.show-sql=true
6. spring.jpa.hibernate.ddl-auto=update
7. spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
8. spring.h2.console.enabled=true
9. spring.jpa.properties.hibernate.format_sql=true

MYSQL:
--------

1. spring.datasource.url=jdbc:mysql://localhost:3306/microservicesdb
2. spring.datasource.username=root
3. spring.datasource.password=root

- java property names are in camelCase , but while generating insert query , it's using _ .(eg: empName -> emp_name))

Steps:
------
1. Create an entity with (@Entity,@Table,@Column etc)
2. Create interface that extends CrudRepository  - @Repository on tp of class
3. @EnableJpaRepositories @EnableEntityScan on main class
`
@Id
@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
@GenericGenerator(name = "native",strategy = "native")`


Derived Query methods:
----------------------
- it has two parts (introducer, criteria)
`public Employee findByEmpAge(int empAge);
  public Employee queryByEmpName(String empName);
  public Employee getByEmpNameAndEmpAge(String empName,int empAge);`


Audit Support:
--------------
1. Spring Data JPA has EntityListener(**AuditingEntityListener**.class) , that can be used to trigger the capturing of auditing information.
2. @CreatedDate @CreatedBy @LastModifiedDate , @LastModifiedBy
3. **MappedSuperClass** , creation:- column(updatable=false), modification:-column(insertable=false)
4. Date related info will be fetched by JPA
5. What about createdBy and updatedBy ???  - Get it from **SecurityContextHolder** - implement **AuditAware** interface
6. @EnableJpaAuditing(..)

`
CREATE TABLE `employee` (
`emp_no` int NOT NULL AUTO_INCREMENT,
`emp_name` varchar(20) NOT NULL,
`emp_age` int NOT NULL,
`created_at` datetime DEFAULT NULL,
`created_by` varchar(45) DEFAULT NULL,
`updated_at` datetime DEFAULT NULL,
`updated_by` varchar(45) DEFAULT NULL,
PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

`


`
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
`

Custom Queries with JPA:
-----------------------
- Derived queries are good as long as they are not complex.
- @Query, @NamedQuery and @NamedNativeQuery- can write own queries
- @query- can write queries in the form of **JPQL** and **Native** SQL queries.
- @NamedQuery - used to define a **JPQL** named query
- @NamedNativeQuery - used to define a **native SQL** named query.

JPQL:
-----
- Java Persistence query Language
- It is a Platform-independent object-oriented query language defined in the Java Persistence API(JPA) specification.
- JPQL uses entity and field names instead of table and column names.


`

    //Positional parameters
    @Query("SELECT e from Employee e where empName=?1")
    public Employee retrieveEmployeeByName(String name);


    //named parameters
    @Query("SELECT e from Employee e where empName=:name")
    public Employee retrieveEmployeeByName2(@Param("name") String name);


    //Native query
    @Query(value = "SELECT *FROM employee where emp_name=:name",nativeQuery = true)
    public Employee retrieveEmployeeByNameNative(@Param("name") String name);
`

- Using **@Query** , we can also run UPDATE,DELETE , INSERT as well.
- For updates, use **@Modifying** and **@Transaction** for transaction management
- @NamedQuery- should match => method name in repository & query name
- @NamedNativeQuery-  should match => method name in repository & query name and add **@Query(nativeQuery=true)** on top of method.
- @NamedQueries , @NamedNativeQueries => for **multiple queries**
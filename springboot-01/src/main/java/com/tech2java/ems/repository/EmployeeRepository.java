package com.tech2java.ems.repository;

import com.tech2java.ems.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RepositoryRestResource(path = "employee-api")

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {



    public Employee findByEmpAge(int empAge);

    public Employee queryByEmpName(String empName);

    public Employee getByEmpNameAndEmpAge(String empName,int empAge);

    //Positional parameters
    //@Query("SELECT e from Employee e where empName=?1")
    public Employee retrieveEmployeeByName(String name);


    //named parameters
    @Query("SELECT e from Employee e where empName=:name")
    public Employee retrieveEmployeeByName2(@Param("name") String name);


    //@Query(value = "SELECT *FROM employee where emp_name=:name",nativeQuery = true)
    @Query(nativeQuery = true) // this is for @NamedNativeQuery
    public Employee retrieveEmployeeByNameNative(@Param("name") String name);



    @Transactional
    @Modifying
    @Query("UPDATE Employee set empAge=?1 where empNo=?2")
    int updateEmployeeAge(int empAge, int empNo);



}

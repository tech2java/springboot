package com.tech2java.ems.repository;

import com.tech2java.ems.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {



    public Employee findByEmpAge(int empAge);

    public Employee queryByEmpName(String empName);

    public Employee getByEmpNameAndEmpAge(String empName,int empAge);




}

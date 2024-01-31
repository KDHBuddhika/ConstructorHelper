package com.ConstructorHelper.ConstructorHeper.repo;


import com.ConstructorHelper.ConstructorHeper.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Employee findByEmployeeEmail(String employeeEmail);

    Optional<Employee> findByEmployeeEmailAndEmployeePassword(String employeeEmail, String password);

    List<Employee> findByemployeeActiveStateEquals(boolean activeState);

    Employee findByEmployeeEmailEqualsAndEmployeePasswordEquals(String employeeEmail, String password);
}

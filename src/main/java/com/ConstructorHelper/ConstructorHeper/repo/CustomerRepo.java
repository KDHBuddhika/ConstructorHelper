package com.ConstructorHelper.ConstructorHeper.repo;


import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String customerEmail);

    Optional<Customer> findByEmailAndPassword(String customerEmail, String password);

    List<Customer> findByCustomerActiveStateEquals(boolean activeState);

    Optional<Customer> getReferenceByEmailAndPassword(String customerEmail, String password);

    Customer findByEmailEqualsAndPasswordEquals(String customerEmail, String password);


//    List<Customer> findByActiveStateEquals(boolean activeState);


}

package com.ConstructorHelper.ConstructorHeper.repo;

import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface CompanyRepo extends JpaRepository<Companies,Long> {

    Companies findByCompanyEmail(String companyEmail);


    Optional<Companies> findByCompanyEmailAndCompanyPassword(String companyEmail, String encodedPassword);
}

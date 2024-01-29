package com.ConstructorHelper.ConstructorHeper.repo;

import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface CompanyRepo extends JpaRepository<Companies,Long> {


//    Optional<Companies> findByCompanyEmailEqualsAndCompanyPasswordEquals(String companyEmail, String password);
//
//    Companies findByCompanyEmailEquals(String companyEmail);


    Optional<Companies> findByCompanyEmailAndCompanyPassword(String companyEmail, String password);

    Companies findByCompanyEmail(String companyEmail);

    List<Companies> findByActiveStateEquals(boolean b);
}

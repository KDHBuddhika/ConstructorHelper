package com.ConstructorHelper.ConstructorHeper.repo;

import com.ConstructorHelper.ConstructorHeper.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AdvertisementRepo extends JpaRepository<Advertisement,Integer> {
}

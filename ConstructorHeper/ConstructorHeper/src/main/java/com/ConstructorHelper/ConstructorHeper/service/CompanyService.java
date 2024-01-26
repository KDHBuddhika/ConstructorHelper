package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;

public interface CompanyService {
    String registerCompany(CompanyRegisterDTO companyRegisterDTO);


    String LoginCompany(CompanyLoginDTO companyLoginDTO);
}

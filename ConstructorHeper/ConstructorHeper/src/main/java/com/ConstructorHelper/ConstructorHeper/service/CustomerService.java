package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;

public interface CustomerService {
    String registerCustomer(CustomerRegisterDTO customerRegisterDTO);

    String loginCompany(CustomerLoginDTO customerLoginDTO);

    CustomerResponseAllDTO getCustomerById(int customerId);
}

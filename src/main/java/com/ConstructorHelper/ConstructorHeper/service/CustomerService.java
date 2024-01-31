package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO.GetAllCustomerDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.update.CustomerUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    String registerCustomer(CustomerRegisterDTO customerRegisterDTO);

    int loginCompany(CustomerLoginDTO customerLoginDTO);

    CustomerResponseAllDTO getCustomerById(int customerId);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    String uploadImage(MultipartFile file, int id) throws IOException;

    byte[] getImage(int id);

    String uploadDocument(MultipartFile file, int id) throws IOException;

    byte[] getDocument(int id);


    List<GetAllCustomerDTO> getAllCustomerByActiveState(boolean activeState);
}

package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.repo.CustomerRepo;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIMPL implements CustomerService {

     @Autowired
     private CustomerRepo customerRepo;


    /* ******************* Customer Register ******************** */

    @Override
    public String registerCustomer(CustomerRegisterDTO customerRegisterDTO) {
        if(!(customerRepo.existsById(customerRegisterDTO.getCoustomerNIC())))
        {
            Customer customer = new Customer();
            customer.setCoustomerNIC(customerRegisterDTO.getCoustomerNIC());
            customer.setCustomerFname(customerRegisterDTO.getCustomerFname());
            customer.setCustomerSname(customerRegisterDTO.getCustomerSname());
            customer.setEmail(customerRegisterDTO.getEmail());
            customer.setPhoneNumber(customerRegisterDTO.getPhoneNumber());
            customer.setPassword(customerRegisterDTO.getPassword());
            customer.setCustomerActiveState(false);

            customerRepo.save(customer);
            return "saved";
        }else{
            throw new AlreadyReportedException("Already have account");
        }
    }
}

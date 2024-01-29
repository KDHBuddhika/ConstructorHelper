package com.ConstructorHelper.ConstructorHeper.service.impl;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.entity.Companies;
import com.ConstructorHelper.ConstructorHeper.entity.Customer;
import com.ConstructorHelper.ConstructorHeper.exception.AlreadyReportedException;
import com.ConstructorHelper.ConstructorHeper.exception.NotFoundedException;
import com.ConstructorHelper.ConstructorHeper.repo.CustomerRepo;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    //----------------------------customer login---------------------------
    @Override
    public String loginCompany(CustomerLoginDTO customerLoginDTO) {
        Customer customer = customerRepo.findByEmail(customerLoginDTO.getCustomerEmail());
        if(customer != null){
            String password = customerLoginDTO.getCustomerPassword();
            String encodedPassword = customer.getPassword();
            //           boolean isPwsRight = passwordEncoder.match(password,encodedPassword);
            boolean isPwsRight= password.equals(encodedPassword);
            if(isPwsRight)
            {
                Optional<Customer> customer1 = customerRepo.findByEmailAndPassword(customerLoginDTO.getCustomerEmail(),password);
                if(customer1.isPresent())
                {
                    return"Login Successfully";
                }
                else
                {
                    return"Login Fail";
                }
            }else
            {
                return "Password is Not match";
            }

        }else
        {
            return "Email not Exist";
        }
    }

    //------------------get customer details--------------------------

    @Override
    public CustomerResponseAllDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
           Customer customer = customerRepo.getReferenceById(customerId);

            CustomerResponseAllDTO customerResponseAllDTO = new CustomerResponseAllDTO(
                    customer.getCoustomerNIC(),
                    customer.getCustomerFname(),
                    customer.getCustomerSname(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getDescription(),
                    customer.getCustomerAddress(),
                    customer.getCustomerPic(),
                    customer.getCustomerCV(),
                    customer.isCustomerActiveState()
            );

            return customerResponseAllDTO;
        }
        else {
            throw new NotFoundedException("Not found Details");
        }
    }


    //----------------------------customer Update---------------------------
}

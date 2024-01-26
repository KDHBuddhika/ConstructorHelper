package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    //-------customer Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterCustomer(@RequestBody CustomerRegisterDTO customerRegisterDTO)
    {
        String message = customerService.registerCustomer(customerRegisterDTO);
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
        );
    }




}

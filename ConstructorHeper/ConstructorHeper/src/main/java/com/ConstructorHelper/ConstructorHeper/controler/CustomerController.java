package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CompanyResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.service.CustomerService;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;
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

    //-----------------customer login------------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginCustomer(@RequestBody CustomerLoginDTO customerLoginDTO)
    {

        String message =  customerService.loginCompany(customerLoginDTO);

        if(message.equals("Login Successfully"))
        {
            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(message,true),HttpStatus.ACCEPTED);
            return response;
        }
        else
        {
            ResponseEntity<LoginResponse> response = new ResponseEntity<LoginResponse>(
                    new LoginResponse(message,false),HttpStatus.ACCEPTED);
            return response;
        }


    }

    //----------Customer get all by id -------------------//

    @GetMapping(
            path = "/get-customer-by-id",params = "id"
    )
    public ResponseEntity<StandedResponse> getCustomerById(@RequestParam(value = "id") int customerId){
//      System.out.println("print "+ customerId);
        CustomerResponseAllDTO customerResponseAllDTO= customerService.getCustomerById(customerId);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",customerResponseAllDTO),HttpStatus.OK);

        return response;
    }




}

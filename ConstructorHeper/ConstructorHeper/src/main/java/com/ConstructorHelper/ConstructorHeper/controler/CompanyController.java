package com.ConstructorHelper.ConstructorHeper.controler;


import com.ConstructorHelper.ConstructorHeper.dto.Register.CompanyRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CompanyLoginDTO;
import com.ConstructorHelper.ConstructorHeper.service.CompanyService;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;




    //-------Company Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterCompany(@RequestBody CompanyRegisterDTO companyRegisterDTO)
    {
       String message = companyService.registerCompany(companyRegisterDTO);
       return new ResponseEntity<StandedResponse>(
               new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
       );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginCompany(@RequestBody CompanyLoginDTO companyLoginDTO)
    {
       String message =  companyService.LoginCompany(companyLoginDTO);

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





}

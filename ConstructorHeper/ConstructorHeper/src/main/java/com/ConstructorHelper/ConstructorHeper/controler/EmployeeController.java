package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.CustomerLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.CustomerResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
import com.ConstructorHelper.ConstructorHeper.util.LoginResponse;
import com.ConstructorHelper.ConstructorHeper.util.StandedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {


    @Autowired
    private EmployeeSrevice employeeSrevice;


    //-------employee Register --------------------
    @PostMapping("/register")
    public ResponseEntity<StandedResponse> RegisterEmployee(@RequestBody EmployeeRegisterDTO employeeRegisterDTO)
    {
        String message = employeeSrevice.registerEmployee(employeeRegisterDTO);
        return new ResponseEntity<StandedResponse>(
                new StandedResponse(201,"Sucess",message), HttpStatus.CREATED
        );
    }


    //----------------Employee  Login-----------------------------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> LoginCustomer(@RequestBody EmployeeLoginDTO employeeLoginDTO)
    {

        String message =  employeeSrevice.loginEmployee(employeeLoginDTO);

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


    //----------Employee get all by id -------------------//

    @GetMapping(
            path = "/get-employee-by-id",params = "id"
    )
    public ResponseEntity<StandedResponse> getEmployeeById(@RequestParam(value = "id") long employeeId){
//      System.out.println("print "+ customerId);
        EmployeeResponseAllDTO employeeResponseAllDTO= employeeSrevice.getEmployeeById(employeeId);
        ResponseEntity<StandedResponse> response = new ResponseEntity<StandedResponse>(
                new StandedResponse(200,"sucess",employeeResponseAllDTO),HttpStatus.OK);

        return response;
    }

}

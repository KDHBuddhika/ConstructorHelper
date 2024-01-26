package com.ConstructorHelper.ConstructorHeper.controler;

import com.ConstructorHelper.ConstructorHeper.dto.Register.CustomerRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.service.EmployeeSrevice;
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

}

package com.ConstructorHelper.ConstructorHeper.service;

import com.ConstructorHelper.ConstructorHeper.dto.Register.EmployeeRegisterDTO;
import com.ConstructorHelper.ConstructorHeper.dto.login.EmployeeLoginDTO;
import com.ConstructorHelper.ConstructorHeper.dto.responseALL.EmployeeResponseAllDTO;

public interface EmployeeSrevice {
    String registerEmployee(EmployeeRegisterDTO employeeRegisterDTO);

    String loginEmployee(EmployeeLoginDTO employeeLoginDTO);

    EmployeeResponseAllDTO getEmployeeById(long employeeId);
}

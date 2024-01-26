package com.ConstructorHelper.ConstructorHeper.dto.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRegisterDTO {
    private long EmployeeNIC;
    private String EmployeeFName;
    private String EmployeeSname;
    private String EmployeeEmail;
    private String EmployeePhoneNumber;
    private String EmployeePassword;

}

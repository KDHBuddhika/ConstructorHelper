package com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllEmployeeDTO {
    private long employeeNIC;
    private String employeeFname;
    private String employeeSname;
    private String employeeEmail;
    private String employeePhoneNumber;
    private String employeeAddress;
    private String employeeWorkExperiance;
    private String employeeGender;
    private String employeeAge;
    private String employeeDescription;
    private  byte[] employeePic;
    private byte[] EmployeeCV;
    private boolean EmployeeActiveState;
}

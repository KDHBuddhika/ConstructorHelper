package com.ConstructorHelper.ConstructorHeper.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeUpdateDTO {

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

}

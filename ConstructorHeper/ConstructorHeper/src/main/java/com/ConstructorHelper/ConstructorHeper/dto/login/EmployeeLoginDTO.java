package com.ConstructorHelper.ConstructorHeper.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeLoginDTO {

    private String EmployeeEmail;
    private String EmployeePassword;
}

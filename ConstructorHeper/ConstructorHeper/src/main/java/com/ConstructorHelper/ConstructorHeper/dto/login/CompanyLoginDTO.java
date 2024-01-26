package com.ConstructorHelper.ConstructorHeper.dto.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyLoginDTO {

    private String CompanyEmail;
    private String CompanyPassword;
}

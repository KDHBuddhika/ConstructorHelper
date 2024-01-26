package com.ConstructorHelper.ConstructorHeper.dto.Register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRegisterDTO {
    private long CompanyId;
    private String CompanyName;
    private String CompanyEmail;
    private String CompanyPhoneNumber;
    private String CompanyPassword;

}

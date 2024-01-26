package com.ConstructorHelper.ConstructorHeper.dto.Register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRegisterDTO {

    private int CoustomerNIC;
    private String CustomerFname;
    private String CustomerSname;
    private int PhoneNumber;
    private String Email;
    private String Password;
}

package com.ConstructorHelper.ConstructorHeper.dto.responseALL;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponseAllDTO {
    private int coustomerNIC;
    private String customerFname;
    private String customerSname;
    private int phoneNumber;
    private String email;
    private String description;
    private String customerAddress;
    private byte[] customerPic;
    private byte[] customerCV;
    private boolean customerActiveState;
}

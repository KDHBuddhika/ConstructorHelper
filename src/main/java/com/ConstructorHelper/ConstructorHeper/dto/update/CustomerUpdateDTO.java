package com.ConstructorHelper.ConstructorHeper.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {

    private int coustomerNIC;
    private String customerFname;
    private String customerSname;
    private int phoneNumber;
    private String email;
    private String description;
    private String customerAddress;
}

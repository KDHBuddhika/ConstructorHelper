package com.ConstructorHelper.ConstructorHeper.dto.getAllCompanyDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCompanyDTO {

    private long companyId;
    private String companyName;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyDescription;
    private byte[] companyLogo;
    private byte[] companyCV;
    private boolean activeState;
}

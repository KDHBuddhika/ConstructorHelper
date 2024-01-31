package com.ConstructorHelper.ConstructorHeper.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyUpdateDTO {

    private long companyId;
    private String companyName;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyAddress;
    private String companyDescription;
    private String constructiontype;
    private String distrct;


}

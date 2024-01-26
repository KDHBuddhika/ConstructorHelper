package com.ConstructorHelper.ConstructorHeper.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Companies {

    @Id
    @Column(name = "Company_Id",length = 16,nullable = false)
    private long CompanyId;

    @Column(name = "Company_Name",nullable = false)
    private String CompanyName;

    @Column(name = "Company_Email",length = 32,nullable = false)
    private String CompanyEmail;

    @Column(name = "Company_PhoneNumber", length = 10,nullable = false)
    private String CompanyPhoneNumber;

    @Column(name = "Password",length = 255)
    private String CompanyPassword;

    @Column(name = "Company_Description")
    private String CompanyDescription;

    @Column(name = "Company_Logo")
    private byte CompanyLogo;

    @Column(name = "Company_CV")
    private byte CompanyCV;

    @Column(name = "Active_State", columnDefinition = "TINYINT default 0")
    private boolean ActiveState;



}

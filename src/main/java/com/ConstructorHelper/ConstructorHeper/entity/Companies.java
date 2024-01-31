package com.ConstructorHelper.ConstructorHeper.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Companies {

    @Id
    @Column(name = "company_id",length = 16,nullable = false)
    private long companyId;

    @Column(name = "company_name",nullable = false)
    private String companyName;

    @Column(name = "company_email",length = 32,nullable = false)
    private String companyEmail;

    @Column(name = "company_phone_number", length = 10,nullable = false)
    private String companyPhoneNumber;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "password",length = 255,nullable = false)
    private String companyPassword;

    @Column(name = "company_description",columnDefinition = "TEXT")
    private String companyDescription;

    @Lob
    @Column(name = "company_logo",length = 20000)
    private byte[] companyLogo;

    @Lob
    @Column(name = "company_cV",length = 20000)
    private byte[] companyCV;

    @Column(name = "active_state") //columnDefinition = "TINYINT default 0"
    private boolean activeState;

    @Column(name = "constructiontype")
    private String constructiontype;

    @Column(name = "distrct")
    private String distrct;


}

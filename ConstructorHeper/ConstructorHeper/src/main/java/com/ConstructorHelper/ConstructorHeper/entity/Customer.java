package com.ConstructorHelper.ConstructorHeper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @Column(name = "customer_NIC" , length = 16,nullable = false)
    private int CoustomerNIC;

    @Column(name = "customer_Fname", length = 16,nullable = false)
    private String CustomerFname;

    @Column(name = "customer_Sname", length = 16,nullable = false)
    private String CustomerSname;

    @Column(name = "customer_PhoneNumber", length = 10,nullable = false)
    private int PhoneNumber;

    @Column(name = "customer_Email", length = 64,nullable = false)
    private String Email;

    @Column(name = "customer_Description", length = 512)
    private String Description;

    @Column(name = "customer_Password",nullable = false)
    private String Password;

    @Column(name = "customer_Pic")
    private byte CustomerPic;

    @Column(name = "customer_CV")
    private byte CustomerCV;

    @Column(name= "active_state",columnDefinition = "TINYINT default 0")
    private boolean CustomerActiveState;




}

package com.ConstructorHelper.ConstructorHeper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    @Column(name = "customer_nic" , length = 16,nullable = false)
    private int coustomerNIC;
    @Column(name = "customer_fname", length = 16,nullable = false)
    private String customerFname;
    @Column(name = "customer_sname", length = 16,nullable = false)
    private String customerSname;
    @Column(name = "customer_phone_number", length = 10,nullable = false)
    private int phoneNumber;
    @Column(name = "customer_email", length = 64,nullable = false)
    private String email;
    @Column(name = "customer_description", length = 512)
    private String description;
    @Column(name = "customer_address")
    private String customerAddress;
    @Column(name = "customer_password",nullable = false)
    private String password;
    @Lob
    @Column(name = "customer_pic",length = 10000)
    private byte[] customerPic;

    @Lob
    @Column(name = "customer_cv",length = 10000)
    private byte[] customerCV;

    @Column(name= "active_state",columnDefinition = "TINYINT default 0")
    private boolean customerActiveState;




}

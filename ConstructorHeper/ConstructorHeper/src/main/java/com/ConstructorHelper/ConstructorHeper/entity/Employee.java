package com.ConstructorHelper.ConstructorHeper.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @Column(name = "Employee_NIC",length = 16,nullable = false)
    private long EmployeeNIC;

    @Column(name = "Employee_Fname",length = 64,nullable = false)
    private String EmployeeFname;

    @Column(name = "Employee_Sname",length = 64,nullable = false)
    private String EmployeeSname;

    @Column(name = "Employee_Email",length = 64,nullable = false)
    private String EmployeeEmail;

    @Column(name = "Employee_PhoneNumber",length = 10,nullable = false)
    private String EmployeePhoneNumber;

    @Column(name = "Password",nullable = false)
    private String EmployeePassword;

    @Column(name = "Employee_Description")
    private String EmployeeDescription;

    @Column(name = "Employee_Pic")
    private  byte EmployeePic;

    @Column(name = "Employee_CV")
    private byte EmployeeCV;

    @Column(name = "Active_State",columnDefinition = "TINYINT default 0")
    private boolean EmployeeActiveState;




}

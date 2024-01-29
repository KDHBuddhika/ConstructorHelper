package com.ConstructorHelper.ConstructorHeper.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employee {
    @Id
    @Column(name = "employee_nic",length = 16,nullable = false)
    private long employeeNIC;

    @Column(name = "employee_fname",length = 64,nullable = false)
    private String employeeFname;

    @Column(name = "employee_sname",length = 64,nullable = false)
    private String employeeSname;

    @Column(name = "employee_email",length = 64,nullable = false)
    private String employeeEmail;

    @Column(name = "employee_phone_number",length = 10,nullable = false)
    private String employeePhoneNumber;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_work_experiance")
    private String employeeWorkExperiance;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_age")
    private String employeeAge;

    @Column(name = "password",nullable = false)
    private String employeePassword;

    @Column(name = "employee_description")
    private String employeeDescription;

    @Lob
    @Column(name = "employee_pic",length = 1000)
    private  byte[] employeePic;

    @Lob
    @Column(name = "Employee_CV",length = 1000)
    private byte[] EmployeeCV;

    @Column(name = "Active_State",columnDefinition = "TINYINT default 0")
    private boolean EmployeeActiveState;




}

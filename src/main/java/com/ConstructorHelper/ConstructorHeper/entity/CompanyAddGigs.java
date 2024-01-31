package com.ConstructorHelper.ConstructorHeper.entity;

import javax.persistence.*;

@Entity
@Table
public class CompanyAddGigs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gigId;

    @ManyToOne
    @JoinColumn(name = "add_id")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}

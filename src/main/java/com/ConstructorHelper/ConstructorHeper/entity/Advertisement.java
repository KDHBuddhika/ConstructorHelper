package com.ConstructorHelper.ConstructorHeper.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "advertisement")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Advertisement {

    @Id
    @Column(name = "add_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addId;

    @Column(name = "add_title")
    private String title;

    @Column(name = "categories")
    private String categories;

    @Column(name = "add_content")
    private String content;

    @Column(name = "add_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies companies;

    @OneToMany(mappedBy = "advertisement")
    private Set<CompanyAddGigs> companyAddGigs;



}

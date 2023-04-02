package com.example.spring_es.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String POLICYID;
    private String POLICYTITLE;
    private String POLICYGRADE;
    private String PUBAGENCYID;
    private String PUBAGENCY;
    private String PUBAGENCYFULLNAME;
    private String PUBNUMBER;
    private Date PUBTIME;
    private String POLICYTYPE;
    private String POLICYBODY;
    private String PROVINCE;
    private String CITY;
    private String POLICYSOURCE;
    private Date UPDATEDATE;
}

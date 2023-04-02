package com.example.spring_es.model;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(indexName = "policy")
public class Policy {
    @Id
    @Field(type = FieldType.Text)
    private Integer id;
    @Field(analyzer = "ik_max_word")
    private String POLICYID;
    @Field(analyzer = "ik_max_word")
    private String POLICYTITLE;
    @Field(analyzer = "ik_max_word")
    private String POLICYGRADE;
    @Field(analyzer = "ik_max_word")
    private String PUBAGENCYID;
    @Field(analyzer = "ik_max_word")
    private String PUBAGENCY;
    @Field(analyzer = "ik_max_word")
    private String PUBAGENCYFULLNAME;
    @Field(analyzer = "ik_max_word")
    private String PUBNUMBER;
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @Field(type = FieldType.Date, format=DateFormat.basic_date)
    private String PUBTIME;
    @Field(analyzer = "ik_max_word")
    private String POLICYTYPE;
    @Field(analyzer = "ik_max_word")
    private String POLICYBODY;
    @Field(analyzer = "ik_max_word")
    private String PROVINCE;
    @Field(analyzer = "ik_max_word")
    private String CITY;
    @Field(analyzer = "ik_max_word")
    private String POLICYSOURCE;
    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private String UPDATEDATE;

}

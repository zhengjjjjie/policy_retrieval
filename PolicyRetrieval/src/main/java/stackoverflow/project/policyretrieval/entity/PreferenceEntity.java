package stackoverflow.project.policyretrieval.entity;


import javax.persistence.*;

import lombok.Data;

import java.util.Scanner;

@Entity
@Data
@Table(name = "preference")
public class PreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "province")
    private String province;

    @Basic
    @Column(name = "provincweight")
    private Double provinceWeight;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "typeweight")
    private Double typeWeight;

    @Basic
    @Column(name = "source")
    private String source;
    @Basic
    @Column(name = "sourceweight")
    private Double sourceWeight;

}

package stackoverflow.project.policyretrieval.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;


@Data
@Document(indexName = "art")
public class ESPolicyEntity {
    @Id
    @Field(type = FieldType.Text)
    private Integer id;

    @Field(name ="POLICYID")
    private String policyId;
    @Field(name = "POLICYTITLE", analyzer = "ik_max_word")
    private String policyTitle;
    @Field(name = "POLICYGRADE",analyzer = "ik_max_word")
    private String policyGrade;
    @Field(name = "PUBAGENCYID",analyzer = "ik_max_word")
    private String pubAgencyId;
    @Field(name = "PUBAGENCY",analyzer = "ik_max_word")
    private String pubAgency;
    @Field(name = "PUBAGENCYFULLNAME",analyzer = "ik_max_word")
    private String pubAgencyFullName;
    @Field(name = "PUBNUMBER", analyzer = "ik_max_word")
    private String pubNumber;
    @Field(name = "PUBTIME")
    private String pubTime;
    @Field(name = "POLICYTYPE", analyzer = "ik_max_word")
    private String policyType;
    @Field(name = "POLICYBODY",analyzer = "ik_max_word")
    private String policyBody;
    @Field(name = "PROVINCE", analyzer = "ik_max_word")
    private String province;
    @Field(name = "CITY", analyzer = "ik_max_word")
    private String city;
    @Field(name = "POLICYSOURCE", analyzer = "ik_max_word")
    private String policySource;
    @Field(name = "UPDATEDATE")
    private String updateDate;

}

package stackoverflow.project.policyretrieval.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;


@Data
@Document(indexName = "art")
public class ESPolicyEntity {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(name ="POLICYID",type = FieldType.Text)
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
    @Field(name = "PUBTIME",type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    private Date pubTime;
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
    @Field(name = "UPDATEDATE",type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    private Date updateDate;

}

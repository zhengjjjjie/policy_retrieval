package stackoverflow.project.policyretrieval.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(indexName = "datehistogram")
public class CounterEntity {
    @Id
    @Field(type = FieldType.Auto)
    private Integer id;

    @Field(name = "policyid", type = FieldType.Text)
    private String policyId;

    @Field(name = "userid", type = FieldType.Text)
    private String userId;

    @Field(name = "clicktime", type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    private Date clickTime;
}

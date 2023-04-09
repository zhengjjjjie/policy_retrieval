package stackoverflow.project.policyretrieval.entity.combine;

/*
    description: HistoryEntity的联合主键
    是为了建立一个多对多的History关系表
    一般情况下能够使用@ManyToMany在实体类中直接生成对应的关系表
    但是, 此处History需要添加一个时间戳作为主键, 因此需要构建新的实体类
 */

import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryId implements Serializable {
    private int user_id;
    private String policyid;
    private Timestamp click_time;
}
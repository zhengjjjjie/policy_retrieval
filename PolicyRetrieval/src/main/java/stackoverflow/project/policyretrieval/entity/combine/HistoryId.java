package stackoverflow.project.policyretrieval.entity.combine;

/*
    更新: 经过测试, 该方法的实际使用场景非常坑.
    description: HistoryEntity的联合主键
    是为了建立一个多对多的History关系表
    一般情况下能够使用@ManyToMany在实体类中直接生成对应的关系表
    但是, 此处History需要添加一个时间戳作为主键, 因此需要构建新的实体类
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class HistoryId implements Serializable {
    private String userName;
    private String policyId;
    private Timestamp clickTime;
    public HistoryId() {
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public void setClickTime(Timestamp clickTime) {
        this.clickTime = clickTime;
    }

}

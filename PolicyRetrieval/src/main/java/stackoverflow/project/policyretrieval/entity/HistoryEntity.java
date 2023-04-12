package stackoverflow.project.policyretrieval.entity;


import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import stackoverflow.project.policyretrieval.entity.combine.HistoryId;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@IdClass(HistoryId.class)
@Table(name = "history")
public class HistoryEntity {
    /*
    主码为全部字段, 包含user_id, policyid, last_click_date
    设计思路:
    当用户点击某一个政策时, 记录其点击时间
    由于时间不同, 故user_id 和 policyid是可以重复存在的
    同时设计定时任务, 删除过期的数据, 就可以使得history相对简洁

    如果使用多对一关系, 外键不能指定为其他表的非关键词
    因此如果加上@ManyToOne, 生成的表和预期是不一致的, 其字段会变成targetEntity指定的类型
    索性取消外键依赖
    但是该数据库结构我认为仍然是符合规范的
    外键不一定是其他表的主键, 但必须是其唯一索引.
    代码中外键为
    history_username -> username
    history_policyId -> POLICYID
    均可以唯一表示一条记录.
    */

    @Id
//    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "history_username", referencedColumnName="username",nullable = false)
    private String userName;

    @Id
    @JoinColumn(name = "history_policyId",referencedColumnName="POLICYID", nullable = false)
//    @ManyToOne(targetEntity = PolicyEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private String policyId;
    @Id
    @CreatedDate
    @Column(name = "click_time",updatable = false,nullable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp clickTime;

}


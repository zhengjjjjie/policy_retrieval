package stackoverflow.project.policyretrieval.entity;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import stackoverflow.project.policyretrieval.entity.combine.HistoryId;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@IdClass(HistoryId.class)
@Table(name = "history")
public class HistoryEntity {
    /*
    主码为全部字段, 包含user_id, policyid, last_click_date
    设计思路:
    当用户点击某一个政策时, 记录其点击时间
    由于时间不同, 故user_id 和 policyid是可以重复存在的
    同时设计定时任务, 删除过期的数据, 就可以使得history相对简洁
    */
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private int user_id;

    @Id
//    @ManyToOne(targetEntity = PolicyEntity.class)
    @JoinColumn(name = "policyid", nullable = false)
    @ManyToOne(targetEntity = PolicyEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private String policyid;

    @Id
    @CreatedDate
    @Column(name = "click_time",updatable = false,nullable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp click_time;
}


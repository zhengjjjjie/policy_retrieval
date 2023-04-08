//package stackoverflow.project.policyretrieval.entity;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "history")
//public class HistoryEntity {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    @Column(name = "id", nullable = false)
//    private int id;
//    @ManyToOne(targetEntity = PolicyEntity.class)
//    @JoinColumn(name = "policy_id", referencedColumnName = "id")
//    private PolicyEntity policy;
//    @ManyToOne(targetEntity = EnquirerEntity.class)
//    @JoinColumn(name = "enquirer_id", referencedColumnName = "id")
//    private EnquirerEntity enquirer;
//    @Basic
//    @Column(name = "date")
//    private Date date;
//}

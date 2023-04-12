package stackoverflow.project.policyretrieval.view;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Timestamp;

@Data
public class HistoryView {
    // copy to Policy result view
    // add History time
    private String policyId;
    private String policyTitle;
    @Field(name = "UPDATEDATE",type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    private Date pubTime;
    private Timestamp historyTime;

    public HistoryView() {}
    public HistoryView(String policyId, String policyTitle, Date pubTime,Timestamp historyTime) {
        this.policyTitle = policyTitle;
        this.policyId = policyId;
        this.pubTime = pubTime;
        this.historyTime = historyTime;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Timestamp getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(Timestamp historyTime) {
        this.historyTime = historyTime;
    }



}

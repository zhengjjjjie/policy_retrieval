package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;

@Data
public class HistoryView {
    // copy to Policy result view
    // add History time
    private String policyId;
    private String policyTitle;
    @Field(name = "UPDATEDATE",type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date pubTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date historyTime;

    public HistoryView() {}
    public HistoryView(String policyId, String policyTitle, Date pubTime,Date historyTime) {
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

    public Date getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(Date historyTime) {
        this.historyTime = historyTime;
    }



}

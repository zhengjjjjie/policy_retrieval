package stackoverflow.project.policyretrieval.view;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class HistoryView {
    // copy to Policy result view
    // add History time
    private String policyId;
    private String policyTitle;
    private String pubTime;
    private Timestamp historyTime;

    public HistoryView() {}
    public HistoryView(String policyId, String policyTitle, String pubTime,Timestamp historyTime) {
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

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public Timestamp getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(Timestamp historyTime) {
        this.historyTime = historyTime;
    }



}

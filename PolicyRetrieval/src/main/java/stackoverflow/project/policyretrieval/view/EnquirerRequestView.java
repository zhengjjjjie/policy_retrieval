package stackoverflow.project.policyretrieval.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnquirerRequestView extends LoginRequestView {
    private String nickname;
    private int age;
    private String gender;
    private String politicsStatus;
}

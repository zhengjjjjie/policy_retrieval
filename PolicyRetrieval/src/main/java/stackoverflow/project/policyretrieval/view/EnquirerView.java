package stackoverflow.project.policyretrieval.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnquirerView extends LoginView{
    private String nickname;
    private int age;
    private String gender;
    private String politicsStatus;
}

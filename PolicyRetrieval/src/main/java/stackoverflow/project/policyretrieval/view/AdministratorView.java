package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class AdministratorView {
    private String username;
    private String nickname;
}

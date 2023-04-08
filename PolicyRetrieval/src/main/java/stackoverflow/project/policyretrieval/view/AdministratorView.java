package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonView;

public class AdministratorView {
    @JsonView(AdministratorView.class)
    private String username;
    @JsonView(AdministratorView.class)
    private String nickname;
    @JsonView(AdministratorView.class)
    private String password;


}

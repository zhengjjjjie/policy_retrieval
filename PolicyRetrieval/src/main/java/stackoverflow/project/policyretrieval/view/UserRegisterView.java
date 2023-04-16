package stackoverflow.project.policyretrieval.view;


import lombok.Data;

@Data
public class UserRegisterView {

    private String username;
    private String password;
    private String nickname;
    private String role;
    private String gender;
    private Integer age;
    private String politicsStatus;


}

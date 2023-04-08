package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonView;

public class EnquirerView {
    @JsonView(EnquirerView.Login.class)
    public String username;

    @JsonView(EnquirerView.Login.class)
    private String password;

    @JsonView(EnquirerView.UserInfo.class)
    public String nickname;

    @JsonView(EnquirerView.UserInfo.class)
    public int age;
    @JsonView(EnquirerView.UserInfo.class)
    public String gender;

    @JsonView(EnquirerView.UserInfo.class)
    private String politicsStatus;

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class Login {}
    public static class UserInfo extends Login {}
}

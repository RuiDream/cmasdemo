package Temporary;


import javax.validation.constraints.NotNull;

//用来存储登录时的账号密码，分开实体UserModel的验证和登陆验证
public class UserLogin {
    @NotNull
    public String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

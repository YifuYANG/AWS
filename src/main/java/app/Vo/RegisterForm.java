package app.Vo;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String password;
    private Integer type;
    private String name;
    private Integer sex;
    private String Email;
    private String phone;

}

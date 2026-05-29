package sample.thymeleaf.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterForm {
	
	@NotBlank(message = "ユーザー名を入力してください")
    @Size(min = 3, max = 30, message = "ユーザー名は3文字以上30文字以内で入力してください")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "半角英数字とアンダースコアのみ使用できます")
    private String username;
	
	@NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 72, message = "パスワードは8文字以上72文字以内で入力してください")
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
package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class InsertAdministratorForm {
    @NotBlank(message="名前は必須です")
    private String name;
    @NotBlank(message="メールアドレスは必須です")
    @Email(message="正しい書式のメールアドレスを入力してください")
    private String mailAddress;
    @NotBlank(message="パスワードは必須です")
    private String password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }
    
}

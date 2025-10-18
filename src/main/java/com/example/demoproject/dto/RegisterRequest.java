package com.example.demoproject.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2,max = 50,message = "用户名长度必须在2-50之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码至少6位")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Min(value = 0,message = "年龄不能为负数")
    @Max(value = 150,message = "年龄不能超过150")
    private Integer age;

    public RegisterRequest(){}

    public RegisterRequest(String username,String password,String email,Integer age){
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }
}

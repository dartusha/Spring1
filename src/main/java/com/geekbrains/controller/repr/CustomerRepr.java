package com.geekbrains.controller.repr;

import javax.validation.constraints.NotEmpty;

public class CustomerRepr {

    private Long id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    @NotEmpty
    private String fio;

    private String role;


    public CustomerRepr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio=fio;
    }

    @Override
    public String toString() {
        return "CustomerRepr{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
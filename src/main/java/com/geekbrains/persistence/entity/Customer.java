package com.geekbrains.persistence.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fio;

    @Column
    private String login;

    @Column
    private String password;

    private Long role;

    public Customer() {
    }

    public Customer(String fio) {
        this.fio = fio;
    }

    public Customer(String fio, String login, String password) {
        this.fio = fio;
        this.login=login;
        this.password=password;
    }

    public Customer(String login, String password) {
        this.fio=login;
        this.login=login;
        this.password=password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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
}

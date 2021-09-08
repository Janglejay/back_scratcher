package com.janglejay.entity;

import lombok.Data;


@Data
public class User {
    private String name;
    private String password;
    private Integer authCode;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }
}

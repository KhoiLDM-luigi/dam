package com.example.khoildm;

@Table(name = "users")
public class User {
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    public User() {

    }


}

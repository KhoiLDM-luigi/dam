package com.example.khoildm;

import com.example.khoildm.ORM.annotations.Entity;
import com.example.khoildm.ORM.annotations.Id;
import com.example.khoildm.ORM.annotations.Column;

@Entity(schema = "test")
public class Test {
    @Id
    private int id;
    @Column
    private String name;

    public Test() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

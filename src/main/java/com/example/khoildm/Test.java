package com.example.khoildm;

import com.example.khoildm.ORM.annotations.Entity;
import com.example.khoildm.ORM.annotations.Id;
import com.example.khoildm.ORM.annotations.Column;

@Entity(schema = "test")
public class Test {
    @Id
    public int id;
    @Column
    public String name;

    public Test() {

    }
}

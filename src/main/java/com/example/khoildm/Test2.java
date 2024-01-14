package com.example.khoildm;

import com.example.khoildm.ORM.annotations.Column;
import com.example.khoildm.ORM.annotations.Entity;
import com.example.khoildm.ORM.annotations.Id;

@Entity(schema = "test2")
public class Test2 {

    @Id
    public int id;
    @Column
    public String name;
    @Column
    public double value;
    @Column
    public Boolean active;

    public Test2() {

    }
}

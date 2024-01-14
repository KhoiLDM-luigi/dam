package com.example.khoildm;

import com.example.khoildm.ORM.annotations.Column;
import com.example.khoildm.ORM.annotations.Entity;
import com.example.khoildm.ORM.annotations.Id;

@Entity(schema = "test2")
public class Test2 {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private double value;
    @Column
    private Boolean active;

    public Test2() {

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.query.DMLQuery;

public class Exist implements Condition {
    DMLQuery query;

    public Exist(DMLQuery query) {
        this.query = query;
    }

    @Override
    public String raw() {
        return "EXISTS (" + query.raw() + ")";
    }
}

package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public class Not extends BooleanWhere {
    public Not(Condition condition) {
        super(condition);
    }

    @Override
    String command() {
        return "NOT";
    }
}

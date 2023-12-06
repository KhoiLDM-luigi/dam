package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public class And extends BooleanWhere {
    public And(Condition condition) {
        super(condition);
    }

    @Override
    String command() {
        return "AND";
    }
}

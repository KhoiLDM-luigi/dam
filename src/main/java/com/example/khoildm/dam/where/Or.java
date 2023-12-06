package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public class Or extends BooleanWhere {
    public Or(Condition condition) {
        super(condition);
    }

    @Override
    String command() {
        return "OR";
    }
}

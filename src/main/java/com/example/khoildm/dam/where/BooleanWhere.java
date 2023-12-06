package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public abstract class BooleanWhere implements Condition {
    Condition condition;

    public BooleanWhere(Condition condition) {
        this.condition = condition;
    }

    abstract String command();

    @Override
    public String raw() {
        String command = this.command();
        String where = condition.raw();
        return command + " (" + where + ")";
    }
}

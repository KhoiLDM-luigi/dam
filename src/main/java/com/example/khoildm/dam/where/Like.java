package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public class Like implements Condition {
    String column;
    String pattern;

    public Like(String column, String pattern) {
        this.column = column;
        this.pattern = pattern;
    }

    @Override
    public String raw() {
        return column + " LIKE '" + pattern + "'";
    }
}

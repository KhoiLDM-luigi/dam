package com.example.khoildm.dam.where;

import com.example.khoildm.dam.Condition;

public class Where implements Condition {
    String source;
    String operator;
    Object value;

    public Where(String source, String operator, Object value) {
        this.source = source;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String raw() {
        StringBuilder sb = new StringBuilder(source.strip());
        sb.append(" " + operator.strip());
        if (value instanceof String)
            sb.append(" '" + ((String) value) + "'");
        else
            sb.append(" " + value.toString());

        return sb.toString();
    }
}

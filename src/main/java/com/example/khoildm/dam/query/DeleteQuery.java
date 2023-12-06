package com.example.khoildm.dam.query;

import com.example.khoildm.dam.Condition;

public class DeleteQuery extends DMLQuery {
    String command = "DELETE";

    protected DeleteQuery(String table, Condition condition) {
        super(table, condition);
    }

    @Override
    public String raw() {
        StringBuilder sb = new StringBuilder(this.command);

        sb.append(" FROM ");
        sb.append(table.strip());

        // where query
        if (this.condition != null) {
            sb.append(" WHERE ");
            sb.append(this.condition.raw());
        }
        return sb.toString();
    }
}

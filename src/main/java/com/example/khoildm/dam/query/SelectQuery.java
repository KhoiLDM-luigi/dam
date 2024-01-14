package com.example.khoildm.dam.query;

import com.example.khoildm.dam.Condition;

public class SelectQuery extends DMLQuery {
    String command = "SELECT";
    String[] columns;

    String[] orderColumns;
    boolean asc;

    protected SelectQuery(String table, Condition condition, String... columns) {
        super(table, condition);
        this.columns = columns;
    }

    @Override
    public DMLQuery orderBy(boolean asc, String... columns) throws Exception {
        this.asc = asc;
        this.orderColumns = columns;
        return this;
    }

    @Override
    public String raw() {
        StringBuilder sb = new StringBuilder(this.command + " ");

        if (this.columns != null && this.columns.length != 0) {
            sb.append(String.join(", ", this.columns));
        } else
            sb.append("*");
        sb.append(" FROM ");
        sb.append(table.strip());
        if (this.condition != null) {
            sb.append(" WHERE ");
            sb.append(condition.raw());
        }

        if (this.orderColumns != null) {
            sb.append(" ORDER BY ");
            sb.append(String.join(", ", this.orderColumns));
            String order = this.asc ? " ASC" : " DESC";
            sb.append(order);
        }
        return sb.toString();
    }
}

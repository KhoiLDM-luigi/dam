package com.example.khoildm.dam.query;

import java.util.Map;

import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.Query;

public class DMLQuery implements Query {
    String table;
    Condition condition;

    public DMLQuery() {

    }

    protected DMLQuery(String table, Condition condition) {
        this.table = table;
        this.condition = condition;
    }

    // base query
    public DMLQuery from(String source) {
        this.table = source;
        return this;
    }

    public DMLQuery select(String... columns) {
        return new SelectQuery(table, condition, columns);
    }

    public DMLQuery insert(Map<String, Object> columnsMap) {
        return new InsertQuery(table, condition, columnsMap);
    }

    public DMLQuery update(Map<String, Object> columnsMap) {
        return new UpdateQuery(table, condition, columnsMap);
    }

    public DMLQuery delete() {
        return new DeleteQuery(table, condition);
    }

    // extend query
    public DMLQuery where(Condition condition) throws Exception {
        this.condition = condition;
        return this;
    }

    public DMLQuery orderBy(boolean asc, String... columns) throws Exception {
        throw new Exception("ORDER BY Is not allowed");
    }

    public DMLQuery orderBy(String... columns) throws Exception {
        return orderBy(true, columns);
    }


    public DMLQuery groupBy () throws Exception{
        throw new Exception("Syntax error in GROUP BY clause.");
    }

    public DMLQuery groupBy (String... comlumns) throws Exception{
        return groupBy(comlumns);
    }

    // query config

    // query reset and executor
    public DMLQuery reset() {
        return new DMLQuery(table, condition);
    }

    @Override
    public String raw() {
        return "";
    }
}

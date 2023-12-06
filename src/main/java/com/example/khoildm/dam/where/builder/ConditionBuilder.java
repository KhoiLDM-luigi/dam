package com.example.khoildm.dam.where.builder;

import java.util.ArrayList;
import java.util.List;

import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.where.And;
import com.example.khoildm.dam.where.Exist;
import com.example.khoildm.dam.where.Like;
import com.example.khoildm.dam.where.Not;
import com.example.khoildm.dam.where.Or;
import com.example.khoildm.dam.where.Where;

public class ConditionBuilder implements Condition {
    List<Condition> conditions = new ArrayList<>();

    // where
    public ConditionBuilder where(String source, String operator, Object value) {
        if (conditions.isEmpty()) {
            conditions.add(new Where(source, operator, value));
            return this;
        }
        conditions.add(new And(new Where(source, operator, value)));
        return this;
    }

    public ConditionBuilder where(Condition condition) {
        if (conditions.isEmpty()) {
            conditions.add(condition);
            return this;
        }
        conditions.add(new And(condition));
        return this;
    }

    public ConditionBuilder orWhere(String source, String operator, Object value) {
        if (conditions.isEmpty()) {
            return where(source, operator, value);
        }
        conditions.add(new Or(new Where(source, operator, value)));
        return this;
    }

    public ConditionBuilder orWhere(Condition condition) {
        if (conditions.isEmpty()) {
            return where(condition);
        }
        conditions.add(new Or(condition));
        return this;
    }

    public ConditionBuilder notWhere(String source, String operator, Object value) {
        if (conditions.isEmpty()) {
            conditions.add(new Not(new Where(source, operator, value)));
            return this;
        }
        conditions.add(new Not(new And(new Where(source, operator, value))));
        return this;
    }

    public ConditionBuilder notWhere(Condition condition) {
        if (conditions.isEmpty()) {
            conditions.add(new Not(condition));
            return this;
        }
        conditions.add(new And(new Not(condition)));
        return this;
    }

    public ConditionBuilder orNotWhere(String source, String operator, Object value) {
        if (conditions.isEmpty()) {
            return notWhere(source, operator, value);
        }
        conditions.add(new Or(new Not(new Where(source, operator, value))));
        return this;
    }

    public ConditionBuilder orNotWhere(Condition condition) {
        if (conditions.isEmpty()) {
            return notWhere(condition);
        }
        conditions.add(new Or(new Not(condition)));
        return this;
    }

    // exist
    public ConditionBuilder exist(DMLQuery repository) {
        return where(new Exist(repository));
    }

    public ConditionBuilder orExist(DMLQuery repository) {
        return orWhere(new Exist(repository));
    }

    public ConditionBuilder notExist(DMLQuery repository) {
        return notWhere(new Exist(repository));
    }

    public ConditionBuilder orNotExist(DMLQuery repository) {
        return orNotWhere(new Exist(repository));
    }

    // like
    public ConditionBuilder like(String column, String pattern) {
        return where(new Like(column, pattern));
    }

    public ConditionBuilder orLike(String column, String pattern) {
        return orWhere(new Like(column, pattern));
    }

    public ConditionBuilder notLike(String column, String pattern) {
        return notWhere(new Like(column, pattern));
    }

    public ConditionBuilder orNotLike(String column, String pattern) {
        return orNotWhere(new Like(column, pattern));
    }

    @Override
    public String raw() {
        List<String> queries = new ArrayList<>();
        for (Condition c : conditions) {
            String query = c.raw();
            queries.add(query);
        }
        return String.join(" ", queries);
    }
}

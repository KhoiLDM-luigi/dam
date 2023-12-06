package com.example.khoildm.dam.repository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.khoildm.dam.Query;
import com.example.khoildm.dam.query.SelectQuery;

public class ResultSetQueryResult extends AbstractQueryResult {

    ResultSet result;
    List<Field> fields;

    protected void execute(Statement statement, Query query) throws SQLException {
        if (query instanceof SelectQuery) {
            this.result = statement.executeQuery(query.raw());
            this.havingResultList = true;
        } else {
            statement.executeQuery(query.raw());
            this.havingResultList = false;
        }
    }

    protected <T> T mapObject(Class<T> clazz, List<Field> fields, ResultSet resultSet) throws Exception {

        T instance = clazz.getConstructor().newInstance();
        for (Field f : fields) {
            Object value = resultSet.getObject(f.getName());
            f.set(instance, value);
        }

        return instance;
    }

    @Override
    public <T> List<T> getResultList(Class<T> clazz) throws Exception {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        for (Field f : fields) {
            f.setAccessible(true);
        }

        if (!this.havingResultList)
            return null;

        List<T> resList = new ArrayList<>();
        while (result.next()) {
            T item = this.mapObject(clazz, fields, result);
            resList.add(item);
        }

        return resList;
    }
}

package com.example.khoildm.dam.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.khoildm.dam.Condition;

public class UpdateQuery extends DMLQuery {
    String command = "UPDATE";
    Map<String, Object> columnsMap;

    protected UpdateQuery(String table, Condition condition, Map<String, Object> columnsMap) {
        super(table, condition);
        this.columnsMap = columnsMap;
    }

    @Override
    public String raw() {
        StringBuilder sb = new StringBuilder(this.command + " ");
        sb.append(table.strip());
        if (this.columnsMap == null)
            return "";

        // update map
        sb.append(" SET ");

        List<String> map = new ArrayList<>();

        for (Map.Entry<String, Object> entry : columnsMap.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            String valueStr = value instanceof String ? "'" + ((String) value).strip() + "'" : value.toString();
            map.add(column + " = " + valueStr);
        }
        sb.append(String.join(", ", map));

        // where map
        if (this.condition != null) {
            sb.append(" WHERE ");
            sb.append(this.condition.raw());
        }

        return sb.toString();
    }
}

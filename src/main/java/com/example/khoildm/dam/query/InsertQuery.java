package com.example.khoildm.dam.query;

import java.util.Map;
import java.util.StringJoiner;

import com.example.khoildm.dam.Condition;

public class InsertQuery extends DMLQuery {
    String command = "INSERT INTO";
    Map<String, Object> columnsMap;

    protected InsertQuery(String table, Condition condition, Map<String, Object> columnsMap) {
        super(table, condition);
        this.columnsMap = columnsMap;
    }

    @Override
    public DMLQuery where(Condition condition) throws Exception {
        throw new Exception("Cannot use WHERE Clause in INSERT");
    }

    @Override
    public String raw() {
        StringBuilder sb = new StringBuilder(this.command + " ");
        sb.append(table.strip());
        if (this.columnsMap == null)
            return "";

        StringJoiner columnStringJ = new StringJoiner(", ", "(", ")");
        StringJoiner valueStrJ = new StringJoiner(", ", "(", ")");
        for (Map.Entry<String, Object> entry : columnsMap.entrySet()) {
            String column = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                columnStringJ.add(column);
                valueStrJ.add("'" + ((String) value).strip() + "'");
            } else if (value instanceof Number) {
                columnStringJ.add(column);
                valueStrJ.add(value.toString());
            } else if (value instanceof Boolean) {
                columnStringJ.add(column);
                valueStrJ.add(value.toString());
            }
        }
        sb.append(" ").append(columnStringJ);
        sb.append(" VALUES ");
        sb.append(valueStrJ);

        return sb.toString();
    }
}

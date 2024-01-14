package com.example.khoildm.ORM;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.khoildm.User;
import com.example.khoildm.ORM.annotations.Column;
import com.example.khoildm.ORM.annotations.Entity;
import com.example.khoildm.ORM.annotations.Id;
import com.example.khoildm.ORM.conn_pool.ConnectionPool;
import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.connector.PostgreSQLConnector;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.repository.AbstractQueryResult;
import com.example.khoildm.dam.repository.DefaultRepository;
import com.example.khoildm.dam.where.Where;
import com.example.khoildm.dam.where.builder.ConditionBuilder;

public abstract class Repository<T> {
    protected PostgreSQLConnector connector;

    Class<T> clazz;

    String tableName;
    Map<String, Field> idMap;
    Map<String, Field> columnsMap; // column name, property name

    ConnectionPool instance = null;

    public Repository(Class<T> clazz) throws Exception {
        Entity entity = clazz.getAnnotation(Entity.class);
        if (entity == null)
            throw new Exception("This is not a entity class");
        this.clazz = clazz;

        this.tableName = entity.schema() != "" ? entity.schema() : clazz.getName();
        mapColumn();

        this.instance = ConnectionPool.getInstance();
        if (this.instance == null)
            throw new Exception("Database have not initialized");
    }

    private void mapColumn() {
        HashMap<String, Field> colMap = new HashMap<>();
        HashMap<String, Field> idMap = new HashMap<>();

        List<Field> fields = Arrays.asList(this.clazz.getDeclaredFields());
        for (Field f : fields) {
            f.setAccessible(true);

            Column column = f.getAnnotation(Column.class);
            Id id = f.getAnnotation(Id.class);

            if (column != null) {
                String col_name = f.getName();
                System.out.println(column.name());
                String pro_name = f.getName();
                if (!column.name().equals("")) {
                    col_name = column.name();
                }
                colMap.put(col_name, f);
                continue;
            }

            if (id != null) {
                String col_name = f.getName();
                String pro_name = f.getName();
                if (!id.name().equals("")) {
                    col_name = id.name();
                }
                idMap.put(col_name, f);
                continue;
            }

        }

        this.idMap = idMap;
        this.columnsMap = colMap;
    }

    public List<T> findAll() {
        try {
            DefaultRepository repo = instance.getRepository();
            if (repo.connect()) {
                DMLQuery query = new DMLQuery().from(this.tableName).select("*");
                AbstractQueryResult res = repo.execute(query);
                List<T> resL = res.getResultList(clazz);
                return resL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(T entity) {
        try {
            DefaultRepository repo = instance.getRepository();
            if (repo.connect()) {
                Map<String, Object> columnsMap = new HashMap<>();
                Map<String, Object> idMap = new HashMap<>();

                // obtain id
                for (Map.Entry<String, Field> entry : this.idMap.entrySet()) {
                    String colName = entry.getKey();
                    Field property = entry.getValue();
                    property.setAccessible(true);
                    Object value = property.get(entity);
                    columnsMap.put(colName, value);
                    idMap.put(colName, value);
                }

                // obtain other col
                for (Map.Entry<String, Field> entry : this.columnsMap.entrySet()) {
                    String colName = entry.getKey();
                    Field property = entry.getValue();
                    property.setAccessible(true);
                    Object value = property.get(entity);
                    columnsMap.put(colName, value);
                }

                ConditionBuilder idCon = new ConditionBuilder();
                for (Map.Entry<String, Object> entry : idMap.entrySet()) {
                    String colName = entry.getKey();
                    Object value = entry.getValue();
                    idCon = idCon.where(colName, "=", value);
                }

                DMLQuery check = new DMLQuery().from(tableName).select().where(idCon);
                AbstractQueryResult checkRes = repo.execute(check);
                if (!checkRes.havingResultList() || checkRes.getResultList(this.clazz).isEmpty()) { // entity did not
                                                                                                    // exist insert new
                                                                                                    // one
                    DMLQuery query = new DMLQuery().from(this.tableName).insert(columnsMap);
                    repo.execute(query);
                } else { // entity exist update
                    DMLQuery query = new DMLQuery().from(this.tableName).update(columnsMap).where(idCon);
                    repo.execute(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        try {
            DefaultRepository repo = instance.getRepository();
            if (repo.connect()) {
                Map<String, Object> columnsMap = new HashMap<>();

                Map<String, Object> idMap = new HashMap<>();

                // obtain id
                for (Map.Entry<String, Field> entry : this.idMap.entrySet()) {
                    String colName = entry.getKey();
                    Field property = entry.getValue();
                    property.setAccessible(true);
                    Object value = property.get(entity);
                    columnsMap.put(colName, value);
                    idMap.put(colName, value);
                }

                ConditionBuilder idCon = new ConditionBuilder();
                for (Map.Entry<String, Object> entry : idMap.entrySet()) {
                    String colName = entry.getKey();
                    Object value = entry.getValue();
                    idCon = idCon.where(colName, "=", value);
                }

                DMLQuery query = new DMLQuery().from(this.tableName).delete().where(idCon);
                repo.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.example.khoildm;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.khoildm.dam.ORM.Repository;
import com.example.khoildm.dam.connector.PostgreSQLConnector;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.repository.AbstractQueryResult;
import com.example.khoildm.dam.repository.DefaultRepository;
import com.example.khoildm.dam.where.builder.ConditionBuilder;
import com.example.khoildm.dam.Condition;

public class UserRepository extends Repository<User> {
    
    public UserRepository(PostgreSQLConnector connector) {
        super(connector);
    }

    @Override
    public User find(Integer id) {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                // Get table name from User class annotation
                Table table = User.class.getAnnotation(Table.class);
                String tableName = (table != null) ? table.name() : "users";
                
                // Get column name from id field annotation
                Field idField = User.class.getDeclaredField("id");
                Column column = idField.getAnnotation(Column.class);
                String columnName = (column != null) ? column.name() : "id";

                Condition c = new ConditionBuilder().where(columnName, "=", id);
                DMLQuery query = new DMLQuery().from(tableName).select("*").where(c);
                AbstractQueryResult res = repo.execute(query);
                List<User> resL = res.getResultList(User.class);
                return resL.get(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                // Get table name from User class annotation
                Table table = User.class.getAnnotation(Table.class);
                String tableName = (table != null) ? table.name() : "users";

                DMLQuery query = new DMLQuery().from(tableName).select("*");
                AbstractQueryResult res = repo.execute(query);
                List<User> resL = res.getResultList(User.class);
                return resL;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User entity) {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                // Get table name from User class annotation
                Table table = User.class.getAnnotation(Table.class);
                String tableName = (table != null) ? table.name() : "users";

                Map<String, Object> columnsMap = new HashMap<>();

                // Get column names and values from User class field annotations
                for (Field field : entity.getClass().getDeclaredFields()) {
                    Column column = field.getAnnotation(Column.class);
                    
                    if (column != null) {
                        String columnName = column.name();
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        columnsMap.put(columnName, value);
                    }
                }

                DMLQuery query = new DMLQuery().from(tableName).insert(columnsMap);
                repo.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                 // Get table name from User class annotation
                Table table = User.class.getAnnotation(Table.class);
                String tableName = (table != null) ? table.name() : "users";

                Map<String, Object> columnsMap = new HashMap<>();
                
                // Get column names and values from User class field annotations
                for (Field field : User.class.getDeclaredFields()) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        String columnName = column.name();
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        columnsMap.put(columnName, value);
                    }
                }

                // Get column name from id field annotation
                Field idField = User.class.getDeclaredField("id");
                Column idColumn = idField.getAnnotation(Column.class);
                String idColumnName = (idColumn != null) ? idColumn.name() : "id";

                Condition c = new ConditionBuilder().where(idColumnName, "=", entity.id);
                DMLQuery query = new DMLQuery().from(tableName).update(columnsMap).where(c);
                repo.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        // try {
        //     SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
        //     DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
        //     if (repo.connect()) {
        //         // Get table name from User class annotation
        //         Table table = User.class.getAnnotation(Table.class);
        //         String tableName = (table != null) ? table.name() : "users";
                
        //         // Get column name from id field annotation
        //         Field idField = User.class.getDeclaredField("id");
        //         Column column = idField.getAnnotation(Column.class);
        //         String columnName = (column != null) ? column.name() : "id";
                
        //         Condition c = new ConditionBuilder().where(columnName, "=", id);
        //         DMLQuery query = new DMLQuery().from(tableName).delete().where(c);
        //         repo.execute(query);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}

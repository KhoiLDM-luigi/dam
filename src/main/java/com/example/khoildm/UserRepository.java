package com.example.khoildm;

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
    public User find(Long id) {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                Condition c = new ConditionBuilder().where("id", "=", id);
                DMLQuery query = new DMLQuery().from("users").select("*").where(c);
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
                DMLQuery query = new DMLQuery().from("users").select("*");
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
                Map<String, Object> columnsMap = new HashMap<>();
                columnsMap.put("id", entity.getId());
                columnsMap.put("name", entity.getName());
                DMLQuery query = new DMLQuery().from("users").insert(columnsMap);
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
                Map<String, Object> columnsMap = new HashMap<>();
                columnsMap.put("id", entity.getId());
                columnsMap.put("name", entity.getName());
                Condition c = new ConditionBuilder().where("id", "=", entity.getId());
                DMLQuery query = new DMLQuery().from("users").update(columnsMap).where(c);
                repo.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
            DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");
            if (repo.connect()) {
                Condition c = new ConditionBuilder().where("id", "=", id);
                DMLQuery query = new DMLQuery().from("users").delete().where(c);
                repo.execute(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

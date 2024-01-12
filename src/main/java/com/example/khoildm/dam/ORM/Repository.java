package com.example.khoildm.dam.ORM;

import java.util.List;

import com.example.khoildm.dam.connector.PostgreSQLConnector;

public abstract class Repository<T> {
    protected PostgreSQLConnector connector;

    public Repository(PostgreSQLConnector connector) {
        this.connector = connector;
    }

    public abstract T find(Integer id);

    public abstract List<T> findAll();

    public abstract void save(T entity);

    public abstract void update(T entity);

    public abstract void delete(Long id);
}
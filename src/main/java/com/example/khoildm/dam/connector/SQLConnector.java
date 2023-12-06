package com.example.khoildm.dam.connector;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.khoildm.dam.Connector;
import com.example.khoildm.dam.SQLConnConfig;

public abstract class SQLConnector implements Connector<Connection> {
    SQLConnConfig config;
    Connection connection;

    public SQLConnector(SQLConnConfig config) {
        this.config = config;
    }

    abstract public boolean connect();

    public boolean disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Connection getRepository() {
        return connection;
    }
}

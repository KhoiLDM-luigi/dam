package com.example.khoildm.dam.factory.connector;

import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.connector.PostgreSQLConnector;
import com.example.khoildm.dam.connector.SQLConnector;

public class PostgreSQLConnectorFactory implements SQLConnectorFactory {
    public SQLConnector create(SQLConnConfig config) throws ClassNotFoundException {
        return new PostgreSQLConnector(config);
    }

    public SQLConnector create(String host, String port, String database, String username, String password)
            throws ClassNotFoundException {
        return new PostgreSQLConnector(host, port, database, username, password);
    }
}

package com.example.khoildm.dam.factory.connector;

import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.connector.MySQLConnector;
import com.example.khoildm.dam.connector.SQLConnector;

public class MySQLConnectorFactory implements SQLConnectorFactory {
    public SQLConnector create(SQLConnConfig config) throws ClassNotFoundException {
        return new MySQLConnector(config);
    }

    public SQLConnector create(String host, String port, String database, String username, String password)
            throws ClassNotFoundException {
        return new MySQLConnector(host, port, database, username, password);
    }
}

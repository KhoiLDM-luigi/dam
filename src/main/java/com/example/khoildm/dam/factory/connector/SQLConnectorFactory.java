package com.example.khoildm.dam.factory.connector;

import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.connector.SQLConnector;

public interface SQLConnectorFactory {
    SQLConnector create(SQLConnConfig conn) throws ClassNotFoundException;

    SQLConnector create(String host, String port, String database, String username, String password)
            throws ClassNotFoundException;
}

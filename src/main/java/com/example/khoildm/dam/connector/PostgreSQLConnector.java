package com.example.khoildm.dam.connector;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.khoildm.dam.SQLConnConfig;

public class PostgreSQLConnector extends SQLConnector {
    public PostgreSQLConnector(SQLConnConfig config)
            throws ClassNotFoundException {
        super(config);
        Class.forName("org.postgresql.Driver");
    }

    public PostgreSQLConnector(String host, String port, String database, String username, String password)
            throws ClassNotFoundException {
        super(new SQLConnConfig(host, port, database, username, password));
        Class.forName("org.postgresql.Driver");
    }

    @Override
    public boolean connect() {
        StringBuilder urlBuilder = new StringBuilder("jdbc:postgresql://");
        urlBuilder.append(config.host);
        urlBuilder.append(":");
        urlBuilder.append(config.port);
        urlBuilder.append("/");
        urlBuilder.append(config.database);

        String url = urlBuilder.toString();

        try {
            connection = DriverManager.getConnection(url, config.username, config.password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

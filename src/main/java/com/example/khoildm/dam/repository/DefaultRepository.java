package com.example.khoildm.dam.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.khoildm.dam.connector.SQLConnector;
import com.example.khoildm.dam.Query;
import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.query.SelectQuery;

public class DefaultRepository {
    SQLConnectorFactory connectorFactory;
    SQLConnConfig config;

    SQLConnector connector;

    Statement statement;

    public DefaultRepository(SQLConnectorFactory connectorFactory, SQLConnConfig config) {
        this.connectorFactory = connectorFactory;
        this.config = config;
    }

    public DefaultRepository(SQLConnectorFactory connectorFactory, String host, String port, String database,
            String username, String password) {
        this.connectorFactory = connectorFactory;
        this.config = new SQLConnConfig(host, port, database, username, password);
    }

    public boolean connect() {
        try {
            this.connector = connectorFactory.create(config);
            return connector.connect();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disconnect() {
        if (connector == null)
            return false;
        return connector.disconnect();
    }

    private Query lastQuery;

    private void checkStatement(Connection connection, Query query) throws SQLException {
        boolean lastQueryWasGetButNewQueryIsUpdate = query instanceof SelectQuery
                && !(lastQuery instanceof SelectQuery);
        boolean lastQueryWasUpdateButNewQueryIsGet = !(query instanceof SelectQuery)
                && lastQuery instanceof SelectQuery;
        if (lastQueryWasGetButNewQueryIsUpdate || lastQueryWasUpdateButNewQueryIsGet) {
            this.statement.close();
            this.statement = connection.createStatement();
        }
    }

    public AbstractQueryResult execute(Query query) {
        if (!(query instanceof DMLQuery))
            return null;

        Connection connection = this.connector.getRepository();

        try {
            if (this.statement == null || lastQuery == null) {
                this.statement = connection.createStatement();
            } else {
                checkStatement(connection, query);
            }

            this.lastQuery = query;
            String sql = query.raw();

            System.out.println(sql);
            ResultSetQueryResult result = new ResultSetQueryResult();
            ResultSet item = null;

            if (query instanceof SelectQuery) {
                result.havingResultList = true;
                item = statement.executeQuery(sql);
            } else {
                statement.executeUpdate(sql);
            }
            result.result = item;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

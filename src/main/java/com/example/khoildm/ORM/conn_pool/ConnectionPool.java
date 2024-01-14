package com.example.khoildm.ORM.conn_pool;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.repository.DefaultRepository;

public class ConnectionPool {
    static ConnectionPool instance;

    int maxConnection;

    Random rand = new Random();

    List<DefaultRepository> repos;

    private ConnectionPool(Class<? extends SQLConnectorFactory> connectorFactoryClass, SQLConnConfig config,
            int maxConnection)
            throws Exception {

        this.maxConnection = maxConnection;

        ArrayList<DefaultRepository> repos = new ArrayList<>();
        for (int i = 0; i < maxConnection; i++) {
            Constructor<? extends SQLConnectorFactory> constructor = connectorFactoryClass.getConstructor();

            SQLConnectorFactory connectorFac = constructor.newInstance();
            repos.add(new DefaultRepository(connectorFac, config));
        }

        this.repos = repos;
    }

    static public ConnectionPool getInstance() {
        return instance;
    }

    static public ConnectionPool getInstance(Class<? extends SQLConnectorFactory> connector, SQLConnConfig config,
            int maxConnection)
            throws Exception {
        if (instance == null)
            instance = new ConnectionPool(connector, config, maxConnection);
        return instance;
    }

    public DefaultRepository getRepository() {
        int conn = this.rand.nextInt(maxConnection);
        return this.repos.get(conn);
    }
}

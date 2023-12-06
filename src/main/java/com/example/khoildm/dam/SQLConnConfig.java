package com.example.khoildm.dam;

public class SQLConnConfig {
    public String host;
    public String port;
    public String database;
    public String username;
    public String password;

    public SQLConnConfig() {

    }

    public SQLConnConfig(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
}

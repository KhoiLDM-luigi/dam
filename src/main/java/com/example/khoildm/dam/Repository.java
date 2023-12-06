package com.example.khoildm.dam;

public interface Repository<T> {
    boolean connect();

    boolean disconnect();

    T execute(Query query);

    T executeRaw(String raw);
}

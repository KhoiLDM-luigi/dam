package com.example.khoildm.dam;

public interface Connector<T> {
    boolean connect();

    boolean disconnect();

    T getRepository();

}

package com.example.khoildm.dam.repository;

import java.util.List;

public abstract class AbstractQueryResult {
    protected boolean havingResultList = false;

    public boolean havingResultList() {
        return havingResultList;
    }

    public abstract <T> List<T> getResultList(Class<T> clazz) throws Exception;
}

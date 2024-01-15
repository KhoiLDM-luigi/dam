package com.example.khoildm.ORM;

import java.util.List;

public interface AbstractRepository<T> {

    public List<T> findAll();

    public void save(T entity);

    public void update(T entity);

    public void delete(Long id);
}

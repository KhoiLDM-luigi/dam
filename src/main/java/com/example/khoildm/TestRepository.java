package com.example.khoildm;

import com.example.khoildm.ORM.Repository;

public class TestRepository extends Repository<Test> {
    public TestRepository() throws Exception {
        super(Test.class);
    }
}

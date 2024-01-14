package com.example.khoildm;

import java.util.List;

import com.example.khoildm.ORM.conn_pool.ConnectionPool;
import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;

public class Main {

    public static void main(String[] args) {

        SQLConnConfig config = new SQLConnConfig("localhost", "5432", "test", "khoildm", "123");
        try {

            // --- Test ORM 1 ---

            // // Initialize database
            // ConnectionPool.getInstance(PostgreSQLConnectorFactory.class, config, 5);
            
            // // Create repository
            // TestRepository repo = new TestRepository();

            // // Create a list of Test
            // List<Test> resL = repo.findAll();
            
            // // Print out the list
            // for (Test i : resL) {
            //     System.out.printf("%d: %s, \n", i.id, i.name);
            // }

            // // Create a new entity
            // Test n1 = new Test();

            // // Set value for the new entity
            // n1.id = 2;
            // n1.name = "hELLO";

            // // Save the new entity to database
            // repo.save(n1);

            // // Print out the list
            // resL = repo.findAll();
            // for (Test i : resL) {
            //     System.out.printf("%d: %s, \n", i.id, i.name);
            // }

            // // Delete the entity from database
            // repo.delete(n1);

            // --- Test ORM 2` ---
            
            // Initialize database
            ConnectionPool.getInstance(PostgreSQLConnectorFactory.class, config, 5);
            
            // Create repository
            Test2Repository repo = new Test2Repository();

            // Create a list of Test2
            List<Test2> resL = repo.findAll();

            // Print out the list
            for (Test2 i : resL) {
                System.out.printf("%d: %s, \n", i.id, i.name, i.active, i.value);
            }
            
            // Create a new Entity
            Test2 n2 = new Test2();
            
            // Set value for the new entity
            n2.name = "Test2";
            n2.id = 1;
            n2.active = true;
            n2.value = 1.5;
            
            // Save the new entity to database
            repo.save(n2);

            // Print out the list
            resL = repo.findAll();
            for (Test2 i : resL) {
                System.out.printf("%d: %s, \n", i.id, i.name, i.active, i.value);
            }

            // Delete the entity from database
            repo.delete(n2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
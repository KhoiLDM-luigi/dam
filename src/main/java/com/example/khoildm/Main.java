package com.example.khoildm;

import com.example.khoildm.ORM.conn_pool.ConnectionPool;
import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SQLConnConfig config = new SQLConnConfig("localhost", "5432", "test", "khoildm", "123");
        try {

            // --- Test ORM 1 ---

//            // Initialize database
//            ConnectionPool.getInstance(PostgreSQLConnectorFactory.class, config, 5);
//
//            // Create repository
//            TestRepository repo = new TestRepository();
//
//            // Create a list of Test
//            List<Test> resL = repo.findAll();
//
//            // Print out the list
//            for (Test i : resL) {
//                System.out.printf("%d: %s, \n", i.getId(), i.getName());
//            }
//
//            // Create a new entity
//            Test n1 = new Test();
//
//            // Set value for the new entity
//            n1.setId(1);
//            n1.setName("hELLO");
//
//            // Save the new entity to database
//            repo.save(n1);
//
//            // Print out the list
//            resL = repo.findAll();
//            for (Test i : resL) {
//                System.out.printf("%d: %s, \n", i.getId(), i.getName());
//            }
//
//            // Delete the entity from database
//            repo.delete(n1);

            // --- Test ORM 2 ---
            
            // Initialize database
            ConnectionPool.getInstance(PostgreSQLConnectorFactory.class, config, 5);
            
            // Create repository
            Test2Repository repo = new Test2Repository();

            // Create a list of Test2
            List<Test2> resL = repo.findAll();

            // Print out the list
            for (Test2 i : resL) {
                System.out.println(i.getId() + ": " + i.getName() + ", " + i.getActive() + ", " + i.getValue());
            }
            
            // Create a new Entity
            Test2 n2 = new Test2();
            
            // Set value for the new entity
            n2.setId(2);
            n2.setName("test2");
            n2.setActive(true);
            n2.setValue(1.2);
            
            // Save the new entity to database
            repo.save(n2);

            // Print out the list
            resL = repo.findAll();
            for (Test2 i : resL) {
                System.out.println(i.getId() + ": " + i.getName() + ", " + i.getActive() + ", " + i.getValue());
            }

            // Update the entity

            n2.setId(2);
            n2.setName("test2");
            n2.setActive(false); // <--- Change this value to false to see the change
            n2.setValue(1.2);

            repo.save(n2);

            // Delete the entity from database
            repo.delete(n2);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Number format error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
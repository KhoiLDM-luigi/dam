package com.example.khoildm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.repository.AbstractQueryResult;
import com.example.khoildm.dam.repository.DefaultRepository;
import com.example.khoildm.dam.where.builder.ConditionBuilder;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository(null);

        // Create a new user
        User newUser = new User();
        newUser.name = "John Doe";
        newUser.id = 1;

        userRepository.save(newUser);

        // Update a user
        User existingUser = userRepository.find(1);
        if (existingUser != null) {
            existingUser.name = "Jane Doe";
            userRepository.update(existingUser);
        }

        // try {
        // Condition c = new ConditionBuilder().where("A", "=", "B")
        // .notWhere("A", "=", "C")
        // .orNotWhere("D", "<>", 2);

        // DMLQuery query = new DMLQuery().from("test").select("A", "B", "C",
        // "D").where(c);
        // System.out.println(query.raw() + "\n");

        // Condition c2 = new ConditionBuilder().where("A", "=", "B").exist(query);

        // DMLQuery query2 = new DMLQuery().from("test2").select("A, B").where(c2);
        // System.out.println(query2.raw() + "\n");

        // Map<String, Object> cm = new HashMap<>();
        // cm.put("A", 3);
        // cm.put("B", "Test val");
        // DMLQuery query3 = new DMLQuery().from("test3").insert(cm);
        // System.out.println(query3.raw() + "\n");

        // DMLQuery query4 = new DMLQuery().from("test4").update(cm).where(c);
        // System.out.println(query4.raw() + "\n");

        // DMLQuery query5 = new DMLQuery().from("test5").delete().where(c);
        // System.out.println(query5.raw() + "\n");

        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // try {
        //     SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
        //     DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432", "test", "khoildm", "123");

        //     if (repo.connect()) {

        //         // DMLQuery query = new DMLQuery().from("test2").select("*").orderBy("id");

        //         // AbstractQueryResult res = repo.execute(query);
        //         // List<Test2> resL = res.getResultList(Test2.class);
        //         // for (Test2 i : resL) {
        //         //     System.out.printf("%d: %s, \n", i.id, i.name);
        //         // }

        //         // DMLQuery query2 = new DMLQuery().from("test2").select("*");

        //         // AbstractQueryResult res2 = repo.execute(query2);
        //         // List<Test2> resL2 = res2.getResultList(Test2.class);
        //         // for (Test2 i : resL2) {
        //         //     System.out.printf("%d: %s, \n", i.id, i.name);
        //         // }

        //         // Condition c = new ConditionBuilder().where("active", "=", true);
        //         // DMLQuery query2 = new DMLQuery().from("test2").select("*").where(c);

        //         // AbstractQueryResult res2 = repo.execute(query2);
        //         // List<Test2> resL2 = res2.getResultList(Test2.class);
        //         // for (Test2 i : resL2) {
        //         // System.out.printf("%d: %s\n", i.id, i.name);
        //         // }

        //         // Map<String, Object> colMap = new HashMap<>();
        //         // colMap.put("active", true);

        //         // Condition c2 = new ConditionBuilder().where("active", "=", false);

        //         // DMLQuery query3 = new DMLQuery().from("test2").update(colMap).where(c2);

        //         // repo.execute(query3);

        //         // AbstractQueryResult res3 = repo.execute(query2);
        //         // List<Test2> resL3 = res3.getResultList(Test2.class);
        //         // for (Test2 i : resL3) {
        //         // System.out.printf("%d: %s\n", i.id, i.name);
        //         // }


        //         // Condition condition = new ConditionBuilder().where("id", "=", 1);

        //         // DMLQuery delete = new DMLQuery().from("test").delete().where(condition);
        //         // repo.execute(delete);

        //          Map<String, Object> number1 = new HashMap<>();
        //          number1.put("id", 1);
        //          number1.put("name", "thinh");

        //          DMLQuery insert = new DMLQuery().from("test").insert(number1);
        //          repo.execute(insert);

        //         DMLQuery query = new DMLQuery().from("test").select("*");

        //         AbstractQueryResult res = repo.execute(query);
        //         List<Test> resL = res.getResultList(Test.class);
        //         for (Test i : resL) {
        //             System.out.printf("%d: %s, \n", i.id, i.name);
        //         }

                

        //         // Map<String, Object> number2 = new HashMap<>();
        //         // number2.put("name", "khoi");
        //         // DMLQuery update = new DMLQuery().from("test").update(number2).where(condition);
        //         // repo.execute(update);
                
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
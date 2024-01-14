package com.example.khoildm;

import java.util.List;

import com.example.khoildm.ORM.conn_pool.ConnectionPool;
import com.example.khoildm.dam.Condition;
import com.example.khoildm.dam.SQLConnConfig;
import com.example.khoildm.dam.factory.connector.PostgreSQLConnectorFactory;
import com.example.khoildm.dam.factory.connector.SQLConnectorFactory;
import com.example.khoildm.dam.query.DMLQuery;
import com.example.khoildm.dam.repository.AbstractQueryResult;
import com.example.khoildm.dam.repository.DefaultRepository;
import com.example.khoildm.dam.where.builder.ConditionBuilder;

public class Main {

    public static void main(String[] args) {

        SQLConnConfig config = new SQLConnConfig("localhost", "5432", "test", "khoildm", "123");
        try {
            // initialize database
            ConnectionPool.getInstance(PostgreSQLConnectorFactory.class, config, 5);
            TestRepository repo = new TestRepository();

            List<Test> resL = repo.findAll();
            for (Test i : resL) {
                System.out.printf("%d: %s, \n", i.id, i.name);
            }

            Test n1 = new Test();

            n1.id = 2;
            n1.name = "hELLO";

            repo.save(n1);

            resL = repo.findAll();
            for (Test i : resL) {
                System.out.printf("%d: %s, \n", i.id, i.name);
            }

            repo.delete(n1);

        } catch (Exception e) {
            e.printStackTrace();
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
        // SQLConnectorFactory factory = new PostgreSQLConnectorFactory();
        // DefaultRepository repo = new DefaultRepository(factory, "localhost", "5432",
        // "test", "khoildm", "123");

        // if (repo.connect()) {

        // // DMLQuery query = new DMLQuery().from("test2").select("*").orderBy("id");

        // // AbstractQueryResult res = repo.execute(query);
        // // List<Test2> resL = res.getResultList(Test2.class);
        // // for (Test2 i : resL) {
        // // System.out.printf("%d: %s, \n", i.id, i.name);
        // // }

        // // DMLQuery query2 = new DMLQuery().from("test2").select("*");

        // // AbstractQueryResult res2 = repo.execute(query2);
        // // List<Test2> resL2 = res2.getResultList(Test2.class);
        // // for (Test2 i : resL2) {
        // // System.out.printf("%d: %s, \n", i.id, i.name);
        // // }

        // // Condition c = new ConditionBuilder().where("active", "=", true);
        // // DMLQuery query2 = new DMLQuery().from("test2").select("*").where(c);

        // // AbstractQueryResult res2 = repo.execute(query2);
        // // List<Test2> resL2 = res2.getResultList(Test2.class);
        // // for (Test2 i : resL2) {
        // // System.out.printf("%d: %s\n", i.id, i.name);
        // // }
    }
}
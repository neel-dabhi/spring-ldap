package com.ndabhi.demo.Helper;


import com.ndabhi.demo.Model.Collisions;
import com.ndabhi.demo.Model.Kangaroo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DBHelper{
    private static DBHelper dbHelper = new DBHelper();

    private DBHelper(){};
    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    public boolean writeDB(Integer x1, Integer v1, Integer x2, Integer v2, Integer pos){
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.rbc.kangaroo");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Collisions collisions = new Collisions();
            collisions.setX1(x1);
            collisions.setV1(v1);
            collisions.setX2(x2);
            collisions.setV2(v2);
            collisions.setPosition(pos);
            collisions.setTimeStamp(generateTimeStamp());

            entityManager.persist(collisions);
            entityManager.getTransaction().commit();
            return true;

        }catch (Exception e){
            if (null != entityManager){
                entityManager.getTransaction().rollback();
            }
        }finally {
            entityManager.close();
        }
        return false;
    }


    public List<Collisions> readDB(){

        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.rbc.kangaroo");
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery("SELECT c FROM Collisions c");
            return query.getResultList();

        }catch (Exception e){

        }finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }


    private String generateTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static  DBHelper getInstance(){
        return dbHelper;
    }
}

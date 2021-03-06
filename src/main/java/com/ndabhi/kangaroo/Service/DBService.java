package com.ndabhi.kangaroo.Service;


import com.ndabhi.kangaroo.Model.CollisionsDAO;
import com.ndabhi.kangaroo.Model.ReqResModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class DBService {
    private static final DBService dbHelper = new DBService();

    private DBService() {
    }

    EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;

    public boolean writeDB(ReqResModel reqResModel) {
        Integer x1 = reqResModel.getX1();
        Integer v1 = reqResModel.getV1();
        Integer x2 = reqResModel.getX2();
        Integer v2 = reqResModel.getV2();
        Integer pos = reqResModel.getCollision();
        CollisionsDAO collisionsDAO;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.rbc.kangaroo");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            collisionsDAO = new CollisionsDAO();
            collisionsDAO.setX1(x1);
            collisionsDAO.setV1(v1);
            collisionsDAO.setX2(x2);
            collisionsDAO.setV2(v2);
            collisionsDAO.setPosition(pos);
            collisionsDAO.setTimeStamp(generateTimeStamp());

            entityManager.persist(collisionsDAO);
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (null != entityManager) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (null != entityManager) {
                entityManager.close();
            }
        }
        return false;
    }


    public List readDB() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.rbc.kangaroo");
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CollisionsDAO c");
        return query.getResultList();
    }


    private String generateTimeStamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static DBService getInstance() {
        return dbHelper;
    }
}

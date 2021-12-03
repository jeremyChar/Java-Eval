package com.freestack.evaluation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UberApi {


    public static final String PERSISTANCE_UNIT_NAME = "myPostGresqlEntityManager";
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);

    public static void enrollUser(UberUser uberUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction();
        entityManager.persist(uberUser);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void enrollDriver(UberDriver uberDriver) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction();
        entityManager.persist(uberDriver);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Booking bookOneDriver(UberUser uberUser) {
        Booking booking = new Booking();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction();
        Query query = entityManager.createQuery("SELECT d FROM Uberdriver WHERE available = true");
        List drivers = query.getResultList();
        if(drivers != null) {
            booking.setUser(uberUser);
            UberDriver uberDriver = (UberDriver) drivers.get(0);
            booking.setDriver(uberDriver);
            entityManager.persist(booking);
            return booking;
        }
        else {
            return null;
        }

    }

    public static Booking finishBooking(Booking booking){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction();

    }
}

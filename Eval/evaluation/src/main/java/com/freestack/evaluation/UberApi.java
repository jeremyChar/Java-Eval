package com.freestack.evaluation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.util.List;

public class UberApi {


    public static final String PERSISTANCE_UNIT_NAME = "myPostGreSqlEntityManager";
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);

    public static void enrollUser(UberUser uberUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(uberUser);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void enrollDriver(UberDriver uberDriver) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        uberDriver.setAvailable(true);
        entityManager.persist(uberDriver);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Booking bookOneDriver(UberUser uberUser) {
        Booking booking = new Booking();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT d FROM UberDriver d WHERE available = true");
        List drivers = query.getResultList();
        if(drivers.size() != 0) {
            booking.setUser(uberUser);
            UberDriver uberDriver = (UberDriver) drivers.get(0);
            uberDriver.setAvailable(false);
            booking.setDriver(uberDriver);
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
            entityManager.close();
            return booking;
        }
        else {
            return null;
        }

    }

    public static Booking finishBooking(Booking booking1){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT b FROM Booking b WHERE b.id = :id");
        Booking bookingbd = (Booking) query.setParameter("id",booking1.getId()).getSingleResult();
        booking1.setEndOfTheBooking(Instant.now());
        Query query1 = entityManager.createQuery("SELECT d FROM UberDriver d WHERE d.id = :id");
        UberDriver driver = (UberDriver) query1.setParameter("id", booking1.getDriver().getId()).getSingleResult();
        driver.setAvailable(true);
        entityManager.merge(booking1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return booking1;
    }

    public static void evaluateDriver(Booking booking1, int evaluationOfTheUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        booking1.setScore(evaluationOfTheUser);
        entityManager.merge(booking1);
        entityManager.getTransaction().commit();
        entityManager.close();
        }

    public static List<Booking> listDriverBookings(UberDriver uberDriver) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT b FROM Booking b ");
        List<Booking> bookings = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return bookings;
    }

    public static List<Booking> listUnfinishedBookings() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT b FROM Booking b WHERE endOfTheBooking = null");
        List<Booking> bookings = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return bookings;
    }

    public static float meanScore(UberDriver uberDriver) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT b FROM Booking b");
        List<Booking> bookings = query.getResultList();
        Integer mean = 0;
        Integer total = 0;
        Integer compteur = 0;
        for (Booking booking: bookings
             ) { Integer score = booking.getScore();
            if(score != null) {
                total = total + score;
                compteur++ ;
                mean = total / compteur;
            }
        }
        return mean;
    }
}


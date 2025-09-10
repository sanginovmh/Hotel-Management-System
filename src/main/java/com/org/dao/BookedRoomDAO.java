package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.BookedRoom;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookedRoomDAO {
    private static BookedRoomDAO instance;

    private BookedRoomDAO() {
    }

    public static BookedRoomDAO getInstance() {
        if (instance == null) {
            instance = new BookedRoomDAO();
        }
        return instance;
    }

    public List<BookedRoom> findOverlapping(LocalDate checkIn, LocalDate checkOut) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        List<BookedRoom> overlapping = em
                .createQuery(
                        "SELECT br FROM BookedRoom br WHERE br.checkIn <= :checkOut AND br.checkOut >= :checkIn",
                        BookedRoom.class
                )
                .setParameter("checkOut", checkOut)
                .setParameter("checkIn", checkIn)
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return overlapping;
    }
}

package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.Admin;
import com.org.entity.Photo;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class PhotoDAO implements DataAccessObject<Photo> {
    private static PhotoDAO instance;

    private PhotoDAO() {
    }

    public static PhotoDAO getInstance() {
        if (instance == null) {
            instance = new PhotoDAO();
        }
        return instance;
    }

    @Override
    public void save(Photo photo) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        if (photo.getId() != null)
            em.merge(photo);
        else
            em.persist(photo);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Photo> findById(Integer id) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        Photo photo = em
                .find(Photo.class, id);

        em.getTransaction().commit();
        em.close();

        return Optional
                .ofNullable(photo);
    }

    @Override
    public List<Photo> findAll() {
        EntityManager em = EntityManagerDAO
                .getEntityManager();

        em.getTransaction().begin();

        List<Photo> all = em
                .createQuery(
                        "SELECT p FROM Photo p",
                        Photo.class
                )
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return all;
    }

    @Override
    public void delete(Photo photo) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        em
                .remove(photo);

        em.getTransaction().commit();
        em.close();
    }
}

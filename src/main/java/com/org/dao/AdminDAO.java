package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.Admin;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AdminDAO implements DataAccessObject<Admin> {
    private static AdminDAO instance;

    private AdminDAO() {
    }

    public static AdminDAO getInstance() {
        if (instance == null) {
            instance = new AdminDAO();
        }
        return instance;
    }

    @Override
    public void save(Admin admin) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        if (admin.getId() != null)
            em.merge(admin);
        else
            em.persist(admin);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        Admin admin = em
                .find(Admin.class, id);

        em.getTransaction().commit();
        em.close();

        return Optional
                .ofNullable(admin);
    }

    @Override
    public List<Admin> findAll() {
        EntityManager em = EntityManagerDAO
                .getEntityManager();

        em.getTransaction().begin();

        List<Admin> all = em
                .createQuery(
                        "SELECT a FROM Admin a",
                        Admin.class
                )
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return all;
    }

    @Override
    public void delete(Admin admin) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        em
                .remove(admin);

        em.getTransaction().commit();
        em.close();
    }

    public Optional<Admin> login(String email, String password) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        List<Admin> admins = em
                .createQuery(
                        "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password",
                        Admin.class
                )
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        em.getTransaction().commit();
        em.close();

        if (!admins.isEmpty()) {
            return Optional.of(
                    admins
                            .getFirst());
        }
        return Optional
                .empty();
    }
}

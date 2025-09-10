package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.Manager;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ManagerDAO implements DataAccessObject<Manager> {
    private static ManagerDAO instance;

    private ManagerDAO() {
    }

    public static ManagerDAO getInstance() {
        if (instance == null) {
            instance = new ManagerDAO();
        }
        return instance;
    }

    @Override
    public void save(Manager manager) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        if (manager.getId() != null)
            em.merge(manager);
        else
            em.persist(manager);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Manager findById(Integer id) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        Manager manager = em
                .find(Manager.class, id);

        em.getTransaction().commit();
        em.close();

        return manager;
    }

    @Override
    public List<Manager> findAll() {
        EntityManager em = EntityManagerDAO
                .getEntityManager();

        em.getTransaction().begin();

        List<Manager> all = em
                .createQuery(
                        "SELECT m FROM Manager m",
                        Manager.class
                )
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return all;
    }

    @Override
    public void delete(Manager manager) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        em
                .remove(manager);

        em.getTransaction().commit();
        em.close();
    }

    public Optional<Manager> login(String email, String password) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        List<Manager> managers = em
                .createQuery(
                        "SELECT m FROM Manager m WHERE m.email = :email AND m.password = :password",
                        Manager.class
                )
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        em.getTransaction().commit();
        em.close();

        if (!managers.isEmpty()) {
            return Optional.of(
                    managers
                            .getFirst());
        }
        return Optional
                .empty();
    }
}

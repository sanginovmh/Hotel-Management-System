package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.Admin;
import com.org.entity.Branch;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class BranchDAO implements DataAccessObject<Branch> {
    private static BranchDAO instance;

    private BranchDAO() {
    }

    public static BranchDAO getInstance() {
        if (instance == null) {
            instance = new BranchDAO();
        }
        return instance;
    }

    @Override
    public void save(Branch branch) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        if (branch.getId() != null)
            em.merge(branch);
        else
            em.persist(branch);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Branch> findById(Integer id) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        Branch branch = em
                .find(Branch.class, id);

        em.getTransaction().commit();
        em.close();

        return Optional
                .ofNullable(branch);
    }

    @Override
    public List<Branch> findAll() {
        EntityManager em = EntityManagerDAO
                .getEntityManager();

        em.getTransaction().begin();

        List<Branch> all = em
                .createQuery(
                        "SELECT b FROM Branch b",
                        Branch.class
                )
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return all;
    }

    @Override
    public void delete(Branch branch) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        em
                .remove(branch);

        em.getTransaction().commit();
        em.close();
    }
}

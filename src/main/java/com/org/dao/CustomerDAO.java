package com.org.dao;

import com.org.db.EntityManagerDAO;
import com.org.entity.Customer;
import com.org.interfaces.DataAccessObject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CustomerDAO implements DataAccessObject<Customer> {
    private static CustomerDAO instance;

    private CustomerDAO() {
    }

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    @Override
    public void save(Customer customer) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        if (customer.getId() != null)
            em.merge(customer);
        else
            em.persist(customer);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer findById(Integer id) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        Customer customer = em
                .find(Customer.class, id);

        em.getTransaction().commit();
        em.close();

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        EntityManager em = EntityManagerDAO
                .getEntityManager();

        em.getTransaction().begin();

        List<Customer> all = em
                .createQuery(
                        "SELECT c FROM Customer c",
                        Customer.class
                )
                .getResultList();

        em.getTransaction().commit();
        em.close();

        return all;
    }

    @Override
    public void delete(Customer customer) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        em
                .remove(customer);

        em.getTransaction().commit();
        em.close();
    }

    public Optional<Customer> login(String email, String password) {
        EntityManager em =
                EntityManagerDAO
                        .getEntityManager();

        em.getTransaction().begin();

        List<Customer> customers = em
                .createQuery(
                        "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password",
                        Customer.class
                )
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        em.getTransaction().commit();
        em.close();

        if (!customers.isEmpty()) {
            return Optional.of(
                    customers
                            .getFirst());
        }
        return Optional
                .empty();
    }
}

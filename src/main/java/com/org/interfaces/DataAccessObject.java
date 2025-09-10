package com.org.interfaces;

import java.util.List;

public interface DataAccessObject<D> {
    void save(D d);

    D findById(Integer id);

    List<D> findAll();

    void delete(D d);
}

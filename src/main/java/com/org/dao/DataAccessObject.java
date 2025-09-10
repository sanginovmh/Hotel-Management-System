package com.org.dao;

import java.util.List;

public interface DataAccessObject {
    void save(Object o);

    void update(Integer id, Object object);

    Object findById(Integer id);

    List<Object> findAll();

    void delete(Integer id);
}

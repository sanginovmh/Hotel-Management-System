package com.org.interfaces;

import java.util.List;
import java.util.Optional;

public interface DataAccessObject<D> {
    void save(D d);

    Optional<D> findById(Integer id);

    List<D> findAll();

    void delete(D d);
}

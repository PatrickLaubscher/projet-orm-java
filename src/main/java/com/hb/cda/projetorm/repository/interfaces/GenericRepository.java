package com.hb.cda.projetorm.repository.interfaces;

import java.util.List;


public interface GenericRepository<T, K> {

    List<T> findAll(Class<T> entity);
    T findById(Class<T> entity, K id);
    boolean persist(T entity);
    boolean update(T entity);
    boolean delete(T entity);

}

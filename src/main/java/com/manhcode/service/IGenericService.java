package com.manhcode.service;

public interface IGenericService<T> {
    T findById(int id);
    void save(T t);
    void delete(T t);
}

package com.profitsoft.sinelnikov.service;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<E, K extends Number> {
    public abstract List<E> getAll() throws SQLException;

    public abstract void update(E entity) throws SQLException;

    public abstract E getEntityById(K id) throws SQLException;

    public abstract void delete(E entity) throws SQLException;

    public abstract void add(E entity) throws SQLException;
}
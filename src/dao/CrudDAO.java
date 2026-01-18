package dao;

import java.util.List;

public interface CrudDAO<T> {

    T create(T t);

    T readById(int id);

    List<T> readAll();

    boolean update(T t);

    boolean delete(int id);
}

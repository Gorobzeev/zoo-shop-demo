package dao;


import java.util.List;

public interface AbstractDao <T> {

    List<T> loadAll();

    T findById(int id);

    boolean delete(T item);

    boolean update(T changed);

    T add(T item);

}

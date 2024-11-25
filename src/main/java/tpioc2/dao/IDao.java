package tpioc2.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDao<T> {

    boolean create (T o);
    boolean delete (T o);
    boolean update (T o);
    T findById(Long id);
    List<T> findAll();

}

package biz.bagira.shds.eshop.dao;

import java.util.List;

/**
 * Created by Dmitriy on 05.10.2016.
 */
public interface AbstractDAO<T> {
       public T create(T entity);
       public void delete(T entity);
       public T edit (T entity);
       public T getById(Integer id);
       public List<T> getAll();
}

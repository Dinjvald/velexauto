package lv.velexauto.velex.database;

import java.util.List;

/**
 * Created by Dinjvald on 23/03/15.
 */

public interface CommonMethodsDAO<T> {

    void create(T type) throws DBException;

    T getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(T type) throws DBException;

    List<T> getAll() throws DBException;

}

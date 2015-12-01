package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.CommonMethodsDAO;
import lv.velexauto.velex.database.DBConnection;
import lv.velexauto.velex.database.DBException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Dinjvald on 23/03/15.
 */
@Transactional
public abstract class CommonMetodsDAOImpl <T> extends DBConnection implements CommonMethodsDAO <T> {

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public CommonMetodsDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().
                getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T type) throws DBException {
        getCurrentSession().persist(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(Long id) throws DBException {
        Session session = getCurrentSession();
        T type = (T) session.get(persistentClass, id);
        session.delete(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Long id) throws DBException {
        return (T) getCurrentSession().get(persistentClass, id);
    }

    @Override
    public void update(T type) throws DBException {
        getCurrentSession().update(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() throws DBException {
        return getCurrentSession().createCriteria(persistentClass).list();
    }
}

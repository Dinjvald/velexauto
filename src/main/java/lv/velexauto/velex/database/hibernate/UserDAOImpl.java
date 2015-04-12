package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Dinjvald on 12/04/15.
 */

@Component("HibernateDAOUser")
@Transactional
public class UserDAOImpl extends CommonMetodsDAOImpl <User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DBException {
        return (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }
}

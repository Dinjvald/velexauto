package lv.velexauto.velex.database;

import lv.velexauto.velex.domain.User;

/**
 * Created by Dinjvald on 12/04/15.
 */
public interface UserDAO extends CommonMethodsDAO <User> {

    public User getByLogin(String login) throws DBException;
}

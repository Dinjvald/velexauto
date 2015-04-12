package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.UserRoleDAO;
import lv.velexauto.velex.domain.UserRole;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Dinjvald on 12/04/15.
 */

@Component("HibernateDAOUserRole")
@Transactional
public class UserRoleDAOImpl extends CommonMetodsDAOImpl <UserRole> implements UserRoleDAO {
}

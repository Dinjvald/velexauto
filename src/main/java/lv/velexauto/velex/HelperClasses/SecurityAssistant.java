package lv.velexauto.velex.HelperClasses;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import lv.velexauto.velex.domain.User;
import lv.velexauto.velex.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Dinjvald on 07/12/15.
 */

@Component("SecurityAssistant")
public class SecurityAssistant {

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    public String getCurrentUserLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public Employee getCurrentEmployee() throws DBException {

        User user = userDAO.getByLogin(getCurrentUserLogin());
        return user.getEmployee();
    }

    public List<UserRole> getCurrentUserRoles() throws DBException {
        User user = userDAO.getByLoginWithRoles(getCurrentUserLogin());
        return user.getUserRoles();
    }

    public boolean hasCurrentUserAdminRole() throws DBException {
        List<UserRole> roles = getCurrentUserRoles();
        for (UserRole role : roles) {
            if (role.getRole().equals(DataValidateAssistant.ROLE_ADMIN)) return true;


        }
        return false;
    }

    public Company getCurrentCompany() throws DBException {

        Employee employee = getCurrentEmployee();
        return employee.getCompany();
    }

}

package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.EmployeeDAO;
import lv.velexauto.velex.domain.Employee;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Dinjvald on 23/11/15.
 */
@Component("HibernateDAOEmployee")
@Transactional
public class EmployeeDAOImpl extends CommonMetodsDAOImpl<Employee> implements EmployeeDAO {
}

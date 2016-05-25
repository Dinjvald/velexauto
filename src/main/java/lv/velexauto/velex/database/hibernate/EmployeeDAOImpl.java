package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.EmployeeDAO;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Dinjvald on 23/11/15.
 */
@Component("HibernateDAOEmployee")
@Transactional
public class EmployeeDAOImpl extends CommonMetodsDAOImpl<Employee> implements EmployeeDAO {

    @Override
    public List<Employee> getCompaniesEmployees(Company company) {
        return getCurrentSession().createCriteria(Employee.class)
                .add(Restrictions.eq("company", company))
                .list();
    }
}

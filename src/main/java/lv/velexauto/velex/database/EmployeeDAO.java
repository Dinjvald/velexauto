package lv.velexauto.velex.database;

import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;

import java.util.List;

/**
 * Created by Dinjvald on 23/11/15.
 */
public interface EmployeeDAO extends CommonMethodsDAO<Employee> {

    public List getCompaniesEmployees(Company company);
}

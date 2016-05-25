package lv.velexauto.velex.mvc;

import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.EmployeeDAO;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dinjvald on 16/03/16.
 */

@Controller
public class ProtectedStartPageController {

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("HibernateDAOEmployee")
    private EmployeeDAO employeeDAO;

    @RequestMapping(value = {"protected/home"}, method = {RequestMethod.GET})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws DBException {

        Employee employee = securityAssistant.getCurrentEmployee();
        Company company = employee.getCompany();
        List<Employee> list = employeeDAO.getCompaniesEmployees(company);
        request.setAttribute("currentEmployee", employee);
        request.setAttribute("currentCompany", company);

        return new ModelAndView("Protected/HomeProtected", "employee", list);
    }
}

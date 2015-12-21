package lv.velexauto.velex.mvc.Protected;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Dinjvald on 17/12/15.
 */
@Controller
public class AgreementListResultController {

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    @RequestMapping(value = {"protected/agreementListResult"}, method = RequestMethod.POST)
    public ModelAndView agreementListResult(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {

        String string = "Testing";

        Employee employee = securityAssistant.getCurrentEmployee();
        java.util.Date starDate = dateAssistant.stringToDate(request.getParameter("startDate"));
        java.util.Date endDate = dateAssistant.stringToDate(request.getParameter("endDate"));

        List<Agreement> list = agreementDAO.getListByDateRange(starDate, endDate, employee);
        request.setAttribute("agreement", list);
        request.setAttribute("message", "Testing");
        return new ModelAndView("Protected/AgreementListResult");
    }
}

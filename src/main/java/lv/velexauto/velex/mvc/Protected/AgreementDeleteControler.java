package lv.velexauto.velex.mvc.Protected;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dinjvald on 05/01/16.
 */

@Controller
public class AgreementDeleteControler {

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @RequestMapping(value = {"protected/deleteAgreement"}, method = RequestMethod.POST)
    public @ResponseBody String deleteAgreement(@RequestParam(value = "agreementID") long agreementID) throws DBException {

        long currentManagerID = securityAssistant.getCurrentEmployee().getEmployeeId();
        long agreementManagerID = agreementDAO.getById(agreementID).getEmployee().getEmployeeId();

        if (currentManagerID != agreementManagerID) return "can't delete";

        try {
            agreementDAO.delete(agreementID);
            return "success";
        } catch (DBException | IllegalArgumentException e) {
            e.printStackTrace();
            return "error";
        }
    }
}

package lv.velexauto.velex.mvc;

import lv.velexauto.velex.HelperClasses.AgreementRequestBody;
import lv.velexauto.velex.HelperClasses.DataValidateAssistant;
import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Dinjvald on 03/12/15.
 */
@Controller
public class AgreementController {

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    @Autowired
    @Qualifier("DataValidateAssistant")
    private DataValidateAssistant dataValidateAssistant;

    @RequestMapping(value = {"protected/add-agreement"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String addAgreement(@RequestBody AgreementRequestBody agreementRB) throws ParseException, DBException {
        if (!dataValidateAssistant.isAgreementRequestBodyValid(agreementRB)) {
            return DataValidateAssistant.ERROR;
        }
        Agreement agreement = toAgreementDomain(agreementRB);
        agreementDAO.create(agreement);
        return DataValidateAssistant.SUCCESS;
    }

    @RequestMapping(value = {"protected/update-agreement"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String updateAgreement(@RequestBody AgreementRequestBody agreementRB) throws DBException, ParseException {
        if (!dataValidateAssistant.isAgreementRequestBodyValid(agreementRB)) {
            return DataValidateAssistant.ERROR;
        }
        long currentManagerId = securityAssistant.getCurrentEmployee().getEmployeeId();
        long agreementManagerId = agreementDAO.getById(agreementRB.getAgreementId()).getEmployee().getEmployeeId();
        if (!securityAssistant.hasCurrentUserAdminRole() && currentManagerId != agreementManagerId)
            return DataValidateAssistant.CANT_UPDATE;

        Agreement agreement = toAgreementDomain(agreementRB);
        try {
            agreementDAO.update(agreement);
        } catch (Throwable e) {
            e.printStackTrace();
            return DataValidateAssistant.ERROR;
        }
        return dateAssistant.dateToString(agreement.getEstimatedDateOfPayment());
    }

    @RequestMapping(value = {"protected/agreement-list-result"}, method = RequestMethod.POST)
    public ModelAndView agreementListResult(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date starDate = dateAssistant.stringToDate(request.getParameter("startDate"));
        java.util.Date endDate = dateAssistant.stringToDate(request.getParameter("endDate"));
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(starDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/delete-agreement"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteAgreement(@RequestParam(value = "agreementId") long agreementId) throws DBException {
        long currentManagerId = securityAssistant.getCurrentEmployee().getEmployeeId();
        long agreementManagerId;
        try {
            agreementManagerId = agreementDAO.getById(agreementId).getEmployee().getEmployeeId();
        } catch (DBException | NullPointerException e) {
            e.printStackTrace();
            return DataValidateAssistant.ERROR;
        }

        if (!securityAssistant.hasCurrentUserAdminRole() && currentManagerId != agreementManagerId)
            return DataValidateAssistant.CANT_DELETE;
        try {
            agreementDAO.delete(agreementId);
            return DataValidateAssistant.SUCCESS;
        } catch (DBException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            return DataValidateAssistant.ERROR;
        }
    }

    @RequestMapping(value = {"protected/this-month-agreements"}, method = RequestMethod.GET)
    public ModelAndView thisMonthAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date endDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date startDate = dateAssistant.getFirstDateOfCurrentMonth(endDate);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/this-quarter-agreements"}, method = RequestMethod.GET)
    public ModelAndView thisQuarterAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date endDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date startDate = dateAssistant.getFirstDateOfCurrentQuarter(endDate);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/this-half-year-agreements"}, method = RequestMethod.GET)
    public ModelAndView thisHalfYearAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date endDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date startDate = dateAssistant.getFirstDateOfCurrentHalfYear(endDate);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/this-year-agreements"}, method = RequestMethod.GET)
    public ModelAndView thisYearAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date endDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date startDate = dateAssistant.getFirstDateOfCurrentYear(endDate);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/this-and-last-years-agreements"}, method = RequestMethod.GET)
    public ModelAndView thisAndLastYearsAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date endDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date startDate = dateAssistant.getFirstDateOfLastYear(endDate);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/unpaid-agreements"})
    public ModelAndView unpaidAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date currentDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        List<Agreement> list = agreementDAO.getUnpaidAgreements(company);

        for (int x = 0; x < list.size(); x++) {
            int compareArgument = currentDate.compareTo(list.get(x).getEstimatedDateOfPayment());
            if (compareArgument == 1) {
                list.remove(x);
                x--;
            }
        }
        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/late-payment-agreements"}, method = RequestMethod.GET)
    public ModelAndView latePaymentAgreements(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date currentDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        List<Agreement> list = agreementDAO.getUnpaidAgreements(company);

        for (int x = 0; x < list.size(); x++) {
            int compareArgument = currentDate.compareTo(list.get(x).getEstimatedDateOfPayment());
            if (compareArgument == -1 || compareArgument == 0) {
                list.remove(x);
                x--;
            }
        }
        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    @RequestMapping(value = {"protected/planned-agreements"}, method = RequestMethod.GET)
    public ModelAndView plannedAgreement(HttpServletRequest request, HttpServletResponse response) throws DBException, ParseException {
        Company company = securityAssistant.getCurrentCompany();
        java.util.Date startDate = dateAssistant.getCurrentSystemDateWithoutTimestamp();
        java.util.Date endDate = dateAssistant.addDaysToDate(startDate, 360);
        java.util.Date defaultDate = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);

        request.setAttribute("defValues", dataValidateAssistant.getDefaultValuesMap());
        List<Agreement> list = agreementDAO.getListByDateRange(startDate, endDate, defaultDate, company);
        return new ModelAndView("Protected/AgreementListResult", "agreement", list);
    }

    private Agreement toAgreementDomain(AgreementRequestBody agreementRB) throws ParseException, DBException {
        Employee employee = securityAssistant.getCurrentEmployee();
        Agreement agreement = new Agreement();
        if (agreementRB.getAgreementId() != DataValidateAssistant.DEFAULT_INT) {
            agreement.setAgreementId(agreementRB.getAgreementId());
        }
        agreement.setEmployee(employee);
        agreement.setCompany(employee.getCompany());
        agreement.setAgreementNr(agreementRB.getAgreementNr());
        agreement.setInvoiceNr(agreementRB.getInvoiceNr());
        agreement.setClientName(agreementRB.getClientName());
        agreement.setLoadingDate(dateAssistant.stringToDate(agreementRB.getLoadingDate()));
        agreement.setLoadingAddress(agreementRB.getLoadingAddress());
        agreement.setUnloadingDate(dateAssistant.stringToDate(agreementRB.getUnloadingDate()));
        agreement.setUnloadingAddress(agreementRB.getUnloadingAddress());
        agreement.setDriver(agreementRB.getDriver());
        agreement.setPlateNr(agreementRB.getPlateNr());
        agreement.setPrice(agreementRB.getPrice());
        agreement.setValueAddedTax(agreementRB.getValueAddedTax());
        agreement.setPaymentTerm(agreementRB.getPaymentTerm());
        agreement.setInvoiceSendDate(dateAssistant.stringToDate(agreementRB.getInvoiceSendDate()));
        agreement.setOnBehalfOf(agreementRB.getOnBehalfOf());
        agreement.setFileLinkAgreement(agreementRB.getFileLinkAgreement());
        agreement.setFileLinkInvoice(agreementRB.getFileLinkInvoice());
        agreement.setNotes(agreementRB.getNotes());
        agreement.setPaid(Boolean.parseBoolean(agreementRB.getPaid()));

        java.util.Date paymentDate = calculatePaymentDate(agreement.getInvoiceSendDate(), agreement.getPaymentTerm());
        agreement.setEstimatedDateOfPayment(paymentDate);
        return agreement;
    }

    private java.util.Date calculatePaymentDate(java.util.Date date, int paymentTerm) throws ParseException {
        java.util.Date def = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);
        if (date == def || paymentTerm == DataValidateAssistant.DEFAULT_INT) return def;
        return dateAssistant.addDaysToDate(date, paymentTerm);
    }

}

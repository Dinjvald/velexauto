package lv.velexauto.velex.database;

import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;

import java.util.List;

/**
 * Created by Dinjvald on 23/03/15.
 */

public interface AgreementDAO extends CommonMethodsDAO <Agreement> {

    List<Agreement> getListByDateRange(java.util.Date startDate, java.util.Date endDate, Company company);

    List<Agreement> getUnpaidAgreements(Company company);

}

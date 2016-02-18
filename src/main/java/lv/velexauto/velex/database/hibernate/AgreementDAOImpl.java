package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Dinjvald on 23/03/15.
 */

@Component("HibernateDAOAgreement")
@Transactional
public class AgreementDAOImpl extends CommonMetodsDAOImpl <Agreement> implements AgreementDAO {

    @Override
    public List<Agreement> getListByDateRange(Date startDate, Date endDate, Company company) {
        List list = getCurrentSession().createCriteria(Agreement.class)
                .add(Restrictions.eq("company", company))
                .add(Restrictions.between("unloadingDate", startDate, endDate))
                .list();
        return list;
    }

    @Override
    public List<Agreement> getUnpaidAgreements(Company company) {
        List list = getCurrentSession().createCriteria(Agreement.class)
                .add(Restrictions.eq("company", company))
                .add(Restrictions.eq("paid", false))
                .list();
        return list;
    }
}

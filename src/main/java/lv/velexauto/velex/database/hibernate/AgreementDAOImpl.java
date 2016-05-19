package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DataValidateAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Company;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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
public class AgreementDAOImpl extends CommonMetodsDAOImpl<Agreement> implements AgreementDAO {

    @Override
    public List<Agreement> getListByDateRange(Date startDate, Date endDate, Date defDate, Company company) {
        Criteria criteria = getCurrentSession().createCriteria(Agreement.class);
        Criterion dateRange = Restrictions.and(Restrictions.eq("company", company), Restrictions.between("unloadingDate", startDate, endDate));
        Criterion defaultDate = Restrictions.and(Restrictions.eq("company", company), Restrictions.eq("unloadingDate", defDate));
        List list = criteria.add(Restrictions.or(dateRange, defaultDate)).list();
        return list;
    }

    @Override
    public List<Agreement> getUnpaidAgreements(Company company, Date date) {
        List list = getCurrentSession().createCriteria(Agreement.class)
                .add(Restrictions.eq("company", company))
                .add(Restrictions.eq("paid", false))
                .add(Restrictions.lt("unloadingDate", date))
                .list();
        return list;
    }
}

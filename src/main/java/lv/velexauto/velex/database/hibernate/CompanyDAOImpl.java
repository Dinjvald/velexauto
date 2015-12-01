package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.CompanyDAO;
import lv.velexauto.velex.domain.Company;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Dinjvald on 23/11/15.
 */
@Component("HibernateDAOCompany")
@Transactional
public class CompanyDAOImpl extends CommonMetodsDAOImpl<Company> implements CompanyDAO {
}

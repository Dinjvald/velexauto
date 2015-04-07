package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.domain.Agreement;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Dinjvald on 23/03/15.
 */

@Component("HibernateDAOAgreement")
@Transactional
public class AgreementDAOImpl extends CommonMetodsDAOImpl <Agreement> implements AgreementDAO {
}

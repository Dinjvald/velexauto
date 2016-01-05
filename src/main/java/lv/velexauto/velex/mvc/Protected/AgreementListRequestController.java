package lv.velexauto.velex.mvc.Protected;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 17/12/15.
 */

@Controller
public class AgreementListRequestController {

    @RequestMapping(value = {"protected/agreementListRequest"}, method = RequestMethod.GET)
    public ModelAndView agreementListRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("Protected/AgreementListRequest");
    }
}
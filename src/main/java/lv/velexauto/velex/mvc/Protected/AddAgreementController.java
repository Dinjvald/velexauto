package lv.velexauto.velex.mvc.Protected;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 03/12/15.
 */
@Controller
public class AddAgreementController {

    @RequestMapping(value = {"protected/addagreement"})
    public ModelAndView addAgreement(HttpServletRequest request,
                                     HttpServletResponse response) {
        return null;
    }
}

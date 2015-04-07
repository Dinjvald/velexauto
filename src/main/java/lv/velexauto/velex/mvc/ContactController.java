package lv.velexauto.velex.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 06/03/15.
 */
@Controller
public class ContactController {

    @RequestMapping(value = "Contact", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        return new ModelAndView("Contact");
    }
}

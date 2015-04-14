package lv.velexauto.velex.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 14/04/15.
 */
@Controller
public class StartPageController {

    @RequestMapping(value = {"/", "/index", "/homepage"}, method = {RequestMethod.GET})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {
        return new ModelAndView("index");
    }
}

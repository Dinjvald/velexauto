package lv.velexauto.velex.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 21/05/15.
 */
@Controller
public class EmptyControllers {

    @RequestMapping(value = {"/", "index", "homepage"}, method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,
                                       HttpServletResponse response) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"aboutus"}, method = {RequestMethod.GET})
    public ModelAndView aboutUs(HttpServletRequest request,
                                       HttpServletResponse response) {
        return new ModelAndView("AboutUs");
    }

    @RequestMapping(value = {"contacts"}, method = {RequestMethod.GET})
    public ModelAndView contacts(HttpServletRequest request,
                                HttpServletResponse response) {
        return new ModelAndView("Contacts");
    }

    @RequestMapping(value = {"login"}, method = {RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request,
                             HttpServletResponse response) {
        return new ModelAndView("Login");
    }

    @RequestMapping(value = {"protected/home"}, method = {RequestMethod.GET})
    public ModelAndView home(HttpServletRequest request,
                             HttpServletResponse response) {
        return new ModelAndView("Protected/HomeProtected");
    }
}
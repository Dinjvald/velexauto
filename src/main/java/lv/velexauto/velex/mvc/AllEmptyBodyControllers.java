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
public class AllEmptyBodyControllers {

    @RequestMapping(value = {"/", "index", "home-page"}, method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response) {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"about-us"}, method = {RequestMethod.GET})
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

    @RequestMapping(value = {"testlocale"}, method = {RequestMethod.GET})
    public ModelAndView testLocale(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ForTests/TestLocale");
    }

    @RequestMapping(value = {"protected/agreement-form"}, method = RequestMethod.GET)
    public ModelAndView agreementForm(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("Protected/AgreementForm");
    }

    @RequestMapping(value = {"protected/agreement-list-request"}, method = RequestMethod.GET)
    public ModelAndView agreementListRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("Protected/AgreementListRequest");
    }
}
package lv.velexauto.velex.ForTestsPurposes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dinjvald on 04/11/15.
 */

@Controller
public class TestControllers {

    @RequestMapping(value = {"velexAjax"}, method = RequestMethod.GET)
    public ModelAndView testAJAX(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ForTests/TestAjaxJSON");
    }
}

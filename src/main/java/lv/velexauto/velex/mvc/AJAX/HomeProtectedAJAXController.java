package lv.velexauto.velex.mvc.AJAX;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dinjvald on 11/10/15.
 */
@Controller
public class HomeProtectedAJAXController {

    @RequestMapping(value = {"protected/testAjax"}, method = {RequestMethod.GET})
    public @ResponseBody String prosesAjaxRequest() {
        String response = "This is a test for AJAX";
        return response;
    }

    @RequestMapping(value = {"testAjax"}, method = {RequestMethod.GET})
    public @ResponseBody String prosesAjaxRequestIndex() {
        String response = "This is a test for AJAX non protected";
        return response;
    }
}

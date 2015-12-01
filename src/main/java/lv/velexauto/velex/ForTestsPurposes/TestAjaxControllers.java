package lv.velexauto.velex.ForTestsPurposes;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dinjvald on 04/11/15.
 */
@Controller
public class TestAjaxControllers {

    @RequestMapping(value = {"dotheajax"}, method = RequestMethod.POST)
    public @ResponseBody String testAjax(@RequestBody HumanDomain humanDomain) {
        System.out.println(humanDomain.getUsername());
        System.out.println(humanDomain.getAge());
        System.out.println(humanDomain.getDate());
        return "Success";
    }
}

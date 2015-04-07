package lv.velexauto.velex.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinjvald on 01/03/15.
 */
@Controller
public class TransportAdviceController {

    @RequestMapping(value = "Transport", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView model = new ModelAndView();

        String name = request.getParameter("Name");
        String surname = request.getParameter("Surname");
        String transportType = request.getParameter("transportType");
        String answer = "Something went wrong";

        if (transportType.equals("refrigerator")) {
            answer = "We have 2 vehicles for you";
        }
        if (transportType.equals("tilt")) {
            answer = "We have 5 vehicles for you";
        }
        if (transportType.equals("platform")) {
            answer = "We can't offer you anything. Sorry";
        }

        List<String> result = new ArrayList<String>();
        result.add(name);
        result.add(surname);
        result.add(answer);

        model.setViewName("Transport");
        model.addObject("model", result);
        return model;
    }
}

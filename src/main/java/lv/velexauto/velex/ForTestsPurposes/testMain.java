package lv.velexauto.velex.ForTestsPurposes;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class testMain {

    public static void main(String[] args) {

        Map<String, String> list = new HashMap();

        String result = "Success";
        String message = "Form is saved";

        list.put("result", result);
        list.put("message", message);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        System.out.println(json);

    }
}

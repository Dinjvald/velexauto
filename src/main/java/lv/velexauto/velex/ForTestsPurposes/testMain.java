package lv.velexauto.velex.ForTestsPurposes;

import lv.velexauto.velex.HelperClasses.DateAssistant;

import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class testMain {

    public static void main(String[] args) throws ParseException {

        List<String > list = new ArrayList<>();
        list.add("f");
        list.add("t");
        list.add("f");
        list.add("f");
        list.add("f");
        list.add("t");
        list.add("f");

        for (int x = 0; x < list.size(); x++) {
//            System.out.println("This is " + x + "iteration");
            System.out.println(list.get(x));
            if (list.get(x).equals("t")) {
                list.remove(x);
                x--;
            }
        }

        System.out.println("- - -");
        for (String text : list) {
            System.out.println(text);
        }
    }
}

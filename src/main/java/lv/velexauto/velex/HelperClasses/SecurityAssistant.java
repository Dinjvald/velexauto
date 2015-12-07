package lv.velexauto.velex.HelperClasses;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Dinjvald on 07/12/15.
 */

@Component("SecurityAssistant")
public class SecurityAssistant {

    public String getCurrentUserLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return auth.getName();
    }

}

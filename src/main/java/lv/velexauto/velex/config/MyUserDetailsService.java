package lv.velexauto.velex.config;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.User;
import lv.velexauto.velex.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinjvald on 12/04/15.
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userDAO.getByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());

        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> roles) {

        List<GrantedAuthority> result = new ArrayList();
        for (UserRole userRole : roles) {
            result.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return result;
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication
            (User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true, true, authorities);
    }
}

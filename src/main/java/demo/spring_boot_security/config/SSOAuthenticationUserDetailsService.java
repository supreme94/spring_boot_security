package demo.spring_boot_security.config;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import demo.spring_boot_security.entity.CurrentUser;
import demo.spring_boot_security.entity.SSOUserDetails;
import demo.spring_boot_security.entity.Subscriber;

class SSOAuthenticationUserDetailsService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        String principal = (String) token.getPrincipal();
        System.out.println("*** principal *** "  + principal);
        if(!StringUtils.isEmpty(principal)) {
            return  new CurrentUser(new Subscriber(principal));
        }

        return null;
    }
}
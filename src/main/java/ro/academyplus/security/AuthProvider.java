package ro.academyplus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ro.academyplus.services.LoginService;

import java.util.ArrayList;

@Service
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    LoginService loginSrv;

    @Override
    protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String password = (String)usernamePasswordAuthenticationToken.getCredentials();
        if (password != null && userName != null && loginSrv.shouldAuthenticate(userName, password)) {
            System.out.println("AuthProvider returned OK!");
            return new org.springframework.security.core.userdetails.User(userName, password, true, true, true, true, new ArrayList<>());
        }
        else {
            System.out.println("AuthProvider threw exception!");
            throw new BadCredentialsException("Invalid Username/Password for user " + userName);
        }
    }
}

package pl.tomzwi.budget.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import pl.tomzwi.budget.entity.Role;
import pl.tomzwi.budget.entity.Token;
import pl.tomzwi.budget.exception.TokenNotFoundException;
import pl.tomzwi.budget.service.TokenService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        if ( token == null ) {
            throw new BadCredentialsException("Invalid token");
        }

        Token tokenObject = null;
        try {
            tokenObject = tokenService.getToken(token);
        } catch ( TokenNotFoundException ex ) {
            throw new BadCredentialsException("Could not find token", ex);
        }

//        if ( !tokenObject.getUser().getActive() ) {
//            throw new BadCredentialsException("User not actived yet");
//        }

        List<Role> roles = tokenObject.getUser().getRoles();
        Collection<SimpleGrantedAuthority> granted = new ArrayList<>();
        roles.forEach( role -> {
            granted.add( new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });

        return new UsernamePasswordAuthenticationToken(tokenObject.getUser(), token, granted );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}

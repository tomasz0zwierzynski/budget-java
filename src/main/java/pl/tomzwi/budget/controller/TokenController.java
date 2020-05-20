package pl.tomzwi.budget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomzwi.budget.configuration.CurrentlyLoggedUser;
import pl.tomzwi.budget.configuration.ErrorEntityPreparator;
import pl.tomzwi.budget.entity.Role;
import pl.tomzwi.budget.entity.Token;
import pl.tomzwi.budget.entity.User;
import pl.tomzwi.budget.exception.InvalidUsernamePasswordException;
import pl.tomzwi.budget.model.ErrorResponse;
import pl.tomzwi.budget.model.TokenResponse;
import pl.tomzwi.budget.model.UserBody;
import pl.tomzwi.budget.model.UserResponse;
import pl.tomzwi.budget.service.TokenService;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/${security.endpoint.token.prefix}" )
public class TokenController {

    @Autowired
    private ErrorEntityPreparator errorEntity;

    @Autowired
    private TokenService tokenService;

    @PostMapping( "/token" )
    public ResponseEntity<TokenResponse> token(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        Token token = tokenService.getToken(username, password);
        TokenResponse response = new TokenResponse(
                token.getUser().getUsername(),
                token.getToken(),
                DateTimeFormatter.ISO_DATE_TIME.format(token.getExpires()),
                token.getUser().getRoles().stream().map(Role::getName).collect(Collectors.toList())
                );
        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @GetMapping( "/current" )
    public ResponseEntity<UserResponse> current(@CurrentlyLoggedUser User user) {
        UserResponse userBody = new UserResponse(user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return new ResponseEntity<>( userBody, HttpStatus.OK );
    }

    @ExceptionHandler( { InvalidUsernamePasswordException.class } )
    public ResponseEntity<ErrorResponse> handleInvalidUsernamePasswordException( Exception ex ) {
        return errorEntity.prepare( HttpStatus.BAD_REQUEST, ex );
    }

}

package pl.tomzwi.budget.service;

import pl.tomzwi.budget.entity.Token;
import pl.tomzwi.budget.exception.InvalidUsernamePasswordException;
import pl.tomzwi.budget.exception.TokenNotFoundException;

public interface TokenService {

    Token getToken(String username, String password ) throws InvalidUsernamePasswordException;

    Token getToken(String token) throws TokenNotFoundException;

    void purge();

}

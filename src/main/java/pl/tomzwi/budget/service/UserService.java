package pl.tomzwi.budget.service;

import pl.tomzwi.budget.entity.User;
import pl.tomzwi.budget.exception.*;

import java.util.Collection;

public interface UserService {

    User getByUsername( String username ) throws UserNotFoundException;

    boolean isUsernamePasswordCorrect( String username, String password );

    User registerUser( String username, String password, String email ) throws UserAlreadyExistsException, UserEmailAlreadyExistsException;

    User activateUser( String username, String code ) throws UserNotFoundException, UserActivateCodeNotCorrectException, UserAlreadyActivatedException;

    User activateUser( String username ) throws UserNotFoundException, UserAlreadyActivatedException;

    User addRoles( String username, Collection<String> roles ) throws UserNotFoundException, RoleNotFoundException;

    User removeRoles( String username, Collection<String> roles ) throws UserNotFoundException, RoleNotFoundException;

}

package pl.tomzwi.budget.service;

import pl.tomzwi.budget.entity.Role;
import pl.tomzwi.budget.exception.RoleAlreadyDefinedException;
import pl.tomzwi.budget.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role addRole(String name) throws RoleAlreadyDefinedException;

}

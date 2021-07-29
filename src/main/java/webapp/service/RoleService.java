package webapp.service;

import webapp.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void create(Role role);

    void update(Role role);

    void delete(Role role);

    List<Role> getAllRoles();

//    Set<Role> getSetRoles();

    Role getRoleById(long id);

    Role getRoleByRolename(String rolename);

}

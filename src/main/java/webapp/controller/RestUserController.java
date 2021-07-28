//package webapp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import webapp.ExeptionHandler.DataInfoHandler;
//import webapp.ExeptionHandler.UserWithSuchLoginExist;
//import webapp.entity.User;
//import webapp.service.UserService;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api")
//public class RestUserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public RestUserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<Set<User>> apiGetAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> apiGetOneUser(@PathVariable("id") long id) {
//        User user = userService.getUserById(id);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<DataInfoHandler> apiAddNewUser(@Valid @RequestBody User user,
//                                                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String error = getErrorsFromBindingResult(bindingResult);
//            return new ResponseEntity<>(new DataInfoHandler(error), HttpStatus.BAD_REQUEST);
//        }
//        try {
//            userService.create(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (DataIntegrityViolationException e) {
//            throw new UserWithSuchLoginExist("User with such login Exist");
//        }
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<DataInfoHandler> apiUpdateUser(@PathVariable("id") long id,
//                                                         @RequestBody @Valid User user,
//                                                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String error = getErrorsFromBindingResult(bindingResult);
//            return new ResponseEntity<>(new DataInfoHandler(error), HttpStatus.BAD_REQUEST);
//        }
//        try {
//            userService.update(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (DataIntegrityViolationException e) {
//            throw new UserWithSuchLoginExist("User with such login Exist");
//        }
//    }
//
//    @DeleteMapping("users/{id}")
//    public ResponseEntity<DataInfoHandler> apiDeleteUser(@PathVariable("id") long id) {
//        userService.getUserById(id);
//        return new ResponseEntity<>(new DataInfoHandler("User was deleted"), HttpStatus.OK);
//    }
//
//    private String getErrorsFromBindingResult(BindingResult bindingResult) {
//        return bindingResult.getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.joining("; "));
//    }
//
//}
package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webapp.entity.Role;
import webapp.entity.User;
import webapp.model.Role;
import webapp.model.User;
import webapp.service.RoleService;
import webapp.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestControllerCustom {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestControllerCustom(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public User editUser(@PathVariable("id") long id, @RequestBody User user) {
        List<Role> list = new ArrayList<>(user.getRoles());
        if (!list.isEmpty()) {
            long idRole = Long.parseLong(list.get(0).getRolename());
            if (idRole == 2) {
                user.setRoles(roleService.getRoleByRolename();
            } else {
                user.setRoles(Collections.singleton(roleService.getAllRoles(1L)));
            }
        }
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.getUserById(id));
    }
}


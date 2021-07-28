package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.entity.Role;
import webapp.entity.User;
import webapp.service.RoleService;
import webapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("")
    public String getUsersPage(Model model, @AuthenticationPrincipal User authUser) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("usersList", users);
        model.addAttribute("rolesList", roles);
        model.addAttribute("newUser", new User());
        model.addAttribute("authUser", authUser);
        return "main-view";
    }

    @PostMapping("/add")
    public String addUserPage(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserView(Model model, @PathVariable long id) {
        List<Role> rolesList = roleService.getAllRoles();
        model.addAttribute("rolesList", rolesList);
        model.addAttribute("user", userService.getUserById(id));
        return "useredit-modal";
    }

    @PatchMapping("/edit")
    public String updateUser(@ModelAttribute User user) {
        User oldUser = userService.getUserById(user.getId());
        oldUser.setNameFirst(user.getNameFirst());
        oldUser.setNameLast(user.getNameLast());
        oldUser.setEmail(user.getEmail());
        oldUser.setAge(user.getAge());
        if ((user.getPassword() != null) || !(user.getPassword().isEmpty())) {
            oldUser.setPassword(user.getPassword());
        }
        oldUser.setRoles(user.getRoles());
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserView(Model model, @PathVariable long id) {
        List<Role> rolesList = roleService.getAllRoles();
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("rolesList", rolesList);
        return "delete-modal";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute User user) {
        userService.delete(user);
        return "redirect:/admin";
    }

}
package crud.controllers;

import crud.models.User;
import crud.services.RoleService;
import crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String userList(Principal principal, Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "views/userPage/usersList";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User newUser,
                          Principal principal, Model model){
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "views/userPage/userCreate";
    }

    @PostMapping()
    public String create(@ModelAttribute("newUser") User newUser,
                         @RequestParam("userRolesID") Long[] roleList, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/userPage/userCreate";
        }
        newUser.setRoles(Arrays.stream(roleList).map(roleService::readRoleByID).collect(Collectors.toSet()));
        userService.saveUser(newUser);
        return "redirect:/admin/new";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("newUser") User user, @RequestParam("userRolesID") Long[] roleList,
                             BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "views/userPage/usersList";
        }
        user.setRoles(Arrays.stream(roleList).map(roleService::readRoleByID).collect(Collectors.toSet()));
        userService.updateUser(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

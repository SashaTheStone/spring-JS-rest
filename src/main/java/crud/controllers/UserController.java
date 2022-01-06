package crud.controllers;

import crud.models.User;
import crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "views/userPage/usersList";
    }

    @GetMapping("/user")
    public String currentUser(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "views/userPage/userInfo";
    }

    @GetMapping("/admin/{id}")
    public String UserInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.readUserByID(id));
        return "views/userPage/userInfoAdmin";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user){
        return "views/userPage/userCreate";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "views/userPage/userCreate";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.readUserByID(id));
        return "views/userPage/userUpdate";
    }

    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "views/userPage/userUpdate";
        }
        userService.updateUser(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

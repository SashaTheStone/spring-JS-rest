package crud.controllers;

import crud.models.User;
import crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userList(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "userPage/usersList";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.readUserByID(id));
        return "userPage/userView";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "userPage/userCreate";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "userPage/userCreate";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.readUserByID(id));
        return "userPage/userEdit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "userPage/userEdit";
        }
        userService.updateUser(id,user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}

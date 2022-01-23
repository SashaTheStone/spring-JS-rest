package crud.controllers;

import crud.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String currentUser(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "views/userPage/userInfo";
    }

}

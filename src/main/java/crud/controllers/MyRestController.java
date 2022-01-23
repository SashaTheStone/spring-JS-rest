package crud.controllers;


import crud.models.User;
import crud.services.RoleService;
import crud.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    private final UserService userService;
    private final RoleService roleService;

    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users")
    public List<User> userList(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

}

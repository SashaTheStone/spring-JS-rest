package crud.controllers;


import crud.services.RoleService;
import crud.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    private final UserService userService;
    private final RoleService roleService;

    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



}

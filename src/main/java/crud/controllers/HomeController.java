package crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {

        @GetMapping("/")
        public String helloPage() {
            return "homePage/homeview";
        }

    @GetMapping("/login")
    public String loginPage() {
        return "homePage/login";
    }


}

package app.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController
{

    // Router to Index Page
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    // Router to Login Page
    @GetMapping( "/login")
    public String routeLogin(){
        return "login";
    }

    // Router to Register Page
    @GetMapping( "/register")
    public String routeRegister(){
        return "register";
    }

    // Router to Profile Page
    @GetMapping( "/profile")
    public String routeProfile(){
        return "profile";
    }
}

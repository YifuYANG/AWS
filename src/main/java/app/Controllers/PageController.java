package app.Controllers;

import app.JpaRepository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController
{
    @Autowired
    PhotoRepository photoRepository;
    // Router to Index Page
    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("photo",photoRepository.findAll());
        return modelAndView;
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

    // Router to Photo upload Page
    @GetMapping( "/uploadphoto")
    public String routeUpload(){
        return "uploadphoto";
    }

    // Router to Photo profile Page
    @GetMapping( "/profile")
    public String routeProfile(){
        return "profile";
    }

    // Router to Photo photo detail Page
    @GetMapping( "/photodetail")
    public String routeDetail(){
        return "photodetail";
    }
}

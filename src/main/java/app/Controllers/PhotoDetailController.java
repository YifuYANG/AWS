package app.Controllers;

import app.JpaRepository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PhotoDetailController {

    @Autowired
    PhotoRepository photoRepository;

    @ResponseBody
    @GetMapping( "/photodetail/{id}")
    public ModelAndView Detail(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("photodetail");
        modelAndView.addObject("photo",photoRepository.getOne(id));
        return modelAndView;
    }
}

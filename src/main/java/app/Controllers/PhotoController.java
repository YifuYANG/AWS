package app.Controllers;


import app.Entities.Photo;
import app.JpaRepository.PhotoRepository;
import app.Service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
@AllArgsConstructor
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoRepository photoRepository;


    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("name") String name,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("desc") String desc,
                                 @RequestParam("id") Integer id){
        photoService.upload(file,name,desc,id);
        photoService.saveFile(file,name,desc,id);
        ModelAndView modelAndView = new ModelAndView("uploadphoto");
        return modelAndView;
    }

    @GetMapping( "/profile/{id}")
    public ModelAndView routeProfile(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("photo",photoRepository.findByOwnerId(id));
        return modelAndView;
    }


    @GetMapping("/profile/remove/{id}")
    public String remove(@PathVariable Integer id) {
        int ownerId=photoRepository.getOne(id).getOwnerid();
        photoRepository.deleteById(id);
        //photoService.delete(photoRepository.getOne(id).getName());
        String url = "redirect://profile/"+ownerId;
        System.out.println(url);
        return url;
    }
}
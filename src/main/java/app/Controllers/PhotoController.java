package app.Controllers;

import app.JpaRepository.UserRepository;
import app.Service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class PhotoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoService photoService;




    @PostMapping("/add")
    public String addPhoto(@RequestParam("name") String name,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("desc") String desc,
                           @RequestParam("id") Integer id) {
        //System.out.println(id);
        photoService.saveFile(file,name,desc,id);
        return "profile.html";
    }
}

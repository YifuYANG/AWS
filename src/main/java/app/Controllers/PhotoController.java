package app.Controllers;

import app.Entities.Photo;
import app.Entities.User;
import app.JpaRepository.PhotoRepository;
import app.JpaRepository.UserRepository;
import app.Service.PhotoService;
import app.Vo.AddPhotoForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class PhotoController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoRepository photoRepository;


    @PostMapping("/add")
    public String addPhoto(@RequestParam("name") String name,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("desc") String desc) throws IOException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        //User owner = userRepository.findById(newPhoto.getOwnerid()).get();
        Photo photo=new Photo();
        photoService.saveFile(file,name,desc);
        return "profile.html";
    }

}

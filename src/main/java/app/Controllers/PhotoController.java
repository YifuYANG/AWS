package app.Controllers;


import app.Entities.Photo;
import app.JpaRepository.PhotoRepository;
import app.Service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;


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

    @PostMapping(value = "/")
    public ModelAndView search(@RequestParam(value = "search", required = false, defaultValue = "") String search){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Photo> results = new ArrayList<>();
        for (Photo p : photoRepository.findAll()) {
            if (p.getName().toLowerCase().contains(search.toLowerCase())) {
                results.add(p);
            } else if(p.getOwnerid().toString().equals(search)){
                results.add(p);
            }
        }
        modelAndView.addObject("photo",results);
        return modelAndView;
    }

    @GetMapping(value = "/like/{id}")
    public ModelAndView Like(@PathVariable Integer id){
        Photo photo= new Photo();
        photo=photoRepository.getOne(id);
        photo.setLikes(photo.getLikes()+1);
        photoRepository.save(photo);
        return new ModelAndView( "redirect:/photodetail/"+id);
    }
}
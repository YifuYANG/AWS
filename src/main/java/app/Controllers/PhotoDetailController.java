package app.Controllers;

import app.Entities.Comment;
import app.Entities.Likes;
import app.Entities.Photo;
import app.JpaRepository.CommentRepository;
import app.JpaRepository.LikesRepository;
import app.JpaRepository.PhotoRepository;
import app.Vo.CommentForm;
import app.Vo.LikesForm;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class PhotoDetailController {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    CommentRepository commentRepository;

    @ResponseBody
    @GetMapping( "/photodetail/{id}")
    public ModelAndView Detail(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("photodetail");
        modelAndView.addObject("photo",photoRepository.getOne(id));
        modelAndView.addObject("comments",commentRepository.findByPhotoId(id));
        return modelAndView;
    }

    @PostMapping(value = "/like")
    public ModelAndView Like(@RequestBody LikesForm likesForm){
        ModelAndView modelAndView=new ModelAndView("redirect:/photodetail/"+likesForm.getPhotoid());
        if(likesRepository.findByUserId(photoRepository.getOne(likesForm.getPhotoid()).getOwnerid())==null
                || !Liked(likesForm.getLoginuser(), likesForm.getPhotoid())){
            Likes likes=new Likes();
            likes.setLikedphotoid(photoRepository.getOne(likesForm.getPhotoid()).getId());
            likes.setUserid(likesForm.getLoginuser());
            likesRepository.save(likes);
            Photo photo=photoRepository.getOne(likesForm.getPhotoid());
            photo.setLikes(photo.getLikes()+1);
            photoRepository.save(photo);

        }else{
            modelAndView.addObject("msg","liked already");
        }
        return modelAndView;
    }

    @PostMapping(value = "/comment")
    public ModelAndView Comment(@RequestBody CommentForm commentForm){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Comment comment=new Comment();
        comment.setComment(commentForm.getComment());
        comment.setPhotoid(commentForm.getPhotoid());
        comment.setPublisherid(commentForm.getLoginuser());
        comment.setCreatedAt(now);
        commentRepository.save(comment);
        ModelAndView modelAndView=new ModelAndView("redirect:/photodetail/"+commentForm.getPhotoid());
        return modelAndView;
    }

    //helper function
    private Boolean Liked(int userid,int photoid){
        for (Likes l : likesRepository.findByUserId(userid)){
            if(l.getLikedphotoid()==photoid){
               return true;
            }
        }
        return false;
    }
}

package app.Service;


import app.Entities.Photo;
import app.JpaRepository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.sql.Timestamp;


@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;


    public Photo saveFile(MultipartFile files,String name,String desc){
        try {
            Photo photo=new Photo();
            photo.setName(name);
            photo.setDescription(desc);
            photo.setLikes(0);
            photo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            photo.setImage(files.getBytes());
            return photoRepository.save(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

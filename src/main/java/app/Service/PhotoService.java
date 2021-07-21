package app.Service;

import app.Entities.Photo;
import app.JpaRepository.PhotoRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

@Slf4j
@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Value("${application.bucket.name}")
    private String bucket;

    @Autowired
    private AmazonS3 S3Client;

    public Photo saveFile(MultipartFile file,String name,String desc,Integer id){
        String fileName=id+"_"+file.getOriginalFilename();
        Photo photo=new Photo();
        photo.setName(name);
        photo.setOwnerid(id);
        photo.setDescription(desc);
        photo.setLikes(0);
        photo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        photo.setImageurl("https://addphoto.s3.amazonaws.com/"+fileName);
        return photoRepository.save(photo);
    }

    public String upload(MultipartFile file,String name,String desc,Integer id){
        File upload=converter(file);
        String fileName=id+"_"+file.getOriginalFilename();
        S3Client.putObject(new PutObjectRequest(bucket,fileName,upload));
        upload.delete();
        return "uploaded"+fileName;
    }

    public void delete(String filename){
        S3Client.deleteObject(bucket,filename);
    }

    private File converter(MultipartFile file){
        File converted=new File(file.getOriginalFilename());
        try(FileOutputStream fos=new FileOutputStream(converted)){
            fos.write(file.getBytes());
        } catch (IOException e){
            log.error("opps,error!");
        }
        return converted;
    }
}

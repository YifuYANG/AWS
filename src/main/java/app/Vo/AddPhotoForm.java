package app.Vo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddPhotoForm {
        private String name;
        private Integer ownerid;
        private MultipartFile image;
        private String description;
}

package app.Vo;

import lombok.Data;

@Data
public class CommentForm {
    private Integer loginuser;
    private Integer photoid;
    private String comment;
}

package org.hothyojas.mogayobackend.dtos;

import java.time.LocalDateTime;
import lombok.Data;
import org.hothyojas.mogayobackend.entities.Question;

@Data
public class QuestionMetaDto {

    private Integer id;
    private String content;
    private String photo;
    private LocalDateTime createdAt;
    private ParentMetaDto parent;

    public QuestionMetaDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.photo = question.getPhoto();
        this.createdAt = question.getCreatedAt();
        this.parent = new ParentMetaDto(question.getParent());
    }
}

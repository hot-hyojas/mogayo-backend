package org.hothyojas.mogayobackend.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.hothyojas.mogayobackend.entities.Question;

@Data
public class QuestionDto {

    private Integer id;
    private String content;
    private String photo;
    private LocalDateTime createdAt;
    private ParentMetaDto parent;
    List<DeliveryDto> deliveries;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.photo = question.getPhoto();
        this.createdAt = question.getCreatedAt();
        this.parent = new ParentMetaDto(question.getParent());
        this.deliveries = question.getDeliveries().isEmpty() ? null : question.getDeliveries().stream().map(DeliveryDto::new).collect(
            Collectors.toList());
    }
}

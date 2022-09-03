package org.hothyojas.mogayobackend.dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
    private String content;
    private String photo;
    private LocalDateTime createdAt;
    private ParentDto parent;
    List<DeliveryDto> deliveries;
}

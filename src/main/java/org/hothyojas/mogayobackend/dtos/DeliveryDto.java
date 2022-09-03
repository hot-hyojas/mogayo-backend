package org.hothyojas.mogayobackend.dtos;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeliveryDto {

    private Integer id;
    private String answer;
    private boolean isResponded;
    private LocalDateTime createdAt;
    private QuestionDto question;
    private ChildDto child;
}

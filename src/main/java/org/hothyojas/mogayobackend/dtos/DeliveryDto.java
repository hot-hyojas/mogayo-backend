package org.hothyojas.mogayobackend.dtos;

import java.time.LocalDateTime;
import lombok.Data;
import org.hothyojas.mogayobackend.entities.Delivery;

@Data
public class DeliveryDto {

    private Integer id;
    private String answer;
    private boolean isResponded;
    private LocalDateTime createdAt;
    private ChildMetaDto child;

    public DeliveryDto(Delivery delivery) {
        this.id = delivery.getId();
        this.answer = delivery.getAnswer();
        this.isResponded = delivery.isResponded();
        this.createdAt = delivery.getCreatedAt();
        this.child = new ChildMetaDto(delivery.getChild());
    }
}

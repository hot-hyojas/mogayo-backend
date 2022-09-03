package org.hothyojas.mogayobackend.dtos;

import java.util.Objects;
import lombok.Data;
import org.hothyojas.mogayobackend.entities.Child;

@Data
public class ChildDto {

    private Integer id;
    private String token;
    private String username;
    private String nickname;
    private float heartTemperature;
    private boolean available;
    private ParentDto parent;

    public ChildDto(Child child) {
        this.id = child.getId();
        this.token = child.getToken();
        this.username = child.getUsername();
        this.nickname = child.getNickname();
        this.heartTemperature = child.getHeartTemperature();
        this.available = child.isAvailable();
        this.parent = Objects.nonNull(child.getParent()) ? new ParentDto(child.getParent()) : null;
    }
}

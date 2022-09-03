package org.hothyojas.mogayobackend.dtos;

import lombok.Data;
import org.hothyojas.mogayobackend.entities.Child;

@Data
public class ChildMetaDto {

    private Integer id;
    private String token;
    private String username;
    private String nickname;
    private float heartTemperature;
    private boolean available;

    public ChildMetaDto(Child child) {
        this.id = child.getId();
        this.token = child.getToken();
        this.username = child.getUsername();
        this.nickname = child.getNickname();
        this.heartTemperature = child.getHeartTemperature();
        this.available = child.isAvailable();
    }
}

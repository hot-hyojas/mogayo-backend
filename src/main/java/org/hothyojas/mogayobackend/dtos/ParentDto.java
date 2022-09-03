package org.hothyojas.mogayobackend.dtos;

import lombok.Data;
import org.hothyojas.mogayobackend.entities.Parent;

@Data
public class ParentDto {

    private Integer id;
    private String token;
    private String username;
    private String nickname;
    private String inviteCode;
    private int useCount;

    public ParentDto(Parent parent) {
        this.id = parent.getId();
        this.token = parent.getToken();
        this.username = parent.getUsername();
        this.nickname = parent.getNickname();
        this.inviteCode = parent.getInviteCode();
        this.useCount = parent.getUseCount();
    }
}

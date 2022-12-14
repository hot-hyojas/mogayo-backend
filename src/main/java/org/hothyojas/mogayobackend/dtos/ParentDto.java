package org.hothyojas.mogayobackend.dtos;

import java.util.Objects;
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
    private ChildMetaDto child;

    public ParentDto(Parent parent) {
        this.id = parent.getId();
        this.token = parent.getToken();
        this.username = parent.getUsername();
        this.nickname = parent.getNickname();
        this.inviteCode = parent.getInviteCode();
        this.useCount = parent.getUseCount();
        this.child = Objects.nonNull(parent.getChild()) ? new ChildMetaDto(parent.getChild()) : null;
    }
}

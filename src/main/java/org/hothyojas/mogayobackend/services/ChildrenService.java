package org.hothyojas.mogayobackend.services;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.InviteCodeDto;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.repositories.ChildrenRepository;
import org.hothyojas.mogayobackend.repositories.ParentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChildrenService {

    private final ChildrenRepository childrenRepository;
    private final ParentRepository parentRepository;

    public Child getChild(int childrenId) {
        return childrenRepository.findById(childrenId).orElseThrow();
    }

    @Transactional
    public void linkChildAndParent(int childrenId, InviteCodeDto inviteCodeDto) {
        Child child = childrenRepository.findById(childrenId).orElseThrow();
        Parent parent = parentRepository.findParentByInviteCode(inviteCodeDto.getInviteCode()).orElseThrow();
        child.setParent(parent);
    }

    public Child createChild(Child child) {
        return childrenRepository.save(child);
    }
}
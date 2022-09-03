package org.hothyojas.mogayobackend.services;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.TokenDto;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.repositories.ParentsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParentsService {

    private final ParentsRepository parentsRepository;

    public Parent getParent(int parentId) {
        return parentsRepository.findById(parentId).orElseThrow();
    }

    public Parent updateParentToken(int parentId, TokenDto tokenDto) {
        Parent foundParent = parentsRepository.findById(parentId).orElseThrow();
        foundParent.setToken(tokenDto.getToken());
        return parentsRepository.save(foundParent);
    }

    public Parent createParent(Parent parent) {
        return parentsRepository.save(parent);
    }
}

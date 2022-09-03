package org.hothyojas.mogayobackend.services;

import lombok.RequiredArgsConstructor;
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

    public Parent createParent(Parent parent) {
        return parentsRepository.save(parent);
    }
}

package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.services.ParentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("parents")
public class ParentsController {

    private final ParentsService parentsService;

    @GetMapping("/{parentId}")
    public Parent getParent(@PathVariable int parentId) {
        return null;
    }
}

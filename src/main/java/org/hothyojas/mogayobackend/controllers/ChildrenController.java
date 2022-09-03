package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.services.ChildrenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("children")
public class ChildrenController {

    private final ChildrenService childrenService;

    @GetMapping("/{childrenId}")
    public Child getChild(@PathVariable int childrenId) {
        return null;
    }

    @PostMapping("/{childrenId}/parents")
    public void linkChildAndParent(@PathVariable int childrenId) {}
}

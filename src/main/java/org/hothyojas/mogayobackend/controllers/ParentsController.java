package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.config.common.BaseResponse;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.services.ParentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("parents")
@RestController
public class ParentsController {

    private final ParentsService parentsService;

    @GetMapping("/{parentId}")
    public BaseResponse<Parent> getParent(@PathVariable int parentId) {
        return new BaseResponse<>(parentsService.getParent(parentId));
    }

    @PostMapping("")
    public BaseResponse<Parent> createParent(@RequestBody Parent parent) {
        return new BaseResponse<>(parentsService.createParent(parent));
    }
}

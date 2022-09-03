package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.BaseResponse;
import org.hothyojas.mogayobackend.dtos.ParentDto;
import org.hothyojas.mogayobackend.dtos.TokenDto;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.services.ParentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("parents")
@RestController
public class ParentsController {

    private final ParentsService parentsService;

    @GetMapping("/{parentId}")
    public BaseResponse<ParentDto> getParent(@PathVariable int parentId) {
        return new BaseResponse<>(new ParentDto(parentsService.getParent(parentId)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public BaseResponse<ParentDto> createParent(@RequestBody Parent parent) {
        return new BaseResponse<>(new ParentDto(parentsService.createParent(parent)));
    }

    @PatchMapping("/{parentId}")
    public BaseResponse<ParentDto> updateChildToken(@PathVariable int parentId, @RequestBody TokenDto tokenDto) {
        return new BaseResponse<>(new ParentDto(parentsService.updateParentToken(parentId, tokenDto)));
    }
}

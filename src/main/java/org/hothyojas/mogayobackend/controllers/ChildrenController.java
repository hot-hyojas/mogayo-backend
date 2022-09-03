package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.BaseResponse;
import org.hothyojas.mogayobackend.dtos.ChildDto;
import org.hothyojas.mogayobackend.dtos.InviteCodeDto;
import org.hothyojas.mogayobackend.dtos.TokenDto;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.services.ChildrenService;
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
@RequestMapping("children")
@RestController
public class ChildrenController {

    private final ChildrenService childrenService;

    @GetMapping("/{childrenId}")
    public BaseResponse<ChildDto> getChild(@PathVariable int childrenId) {
        return new BaseResponse<>(new ChildDto(childrenService.getChild(childrenId)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public BaseResponse<ChildDto> createChild(@RequestBody Child child) {
        return new BaseResponse<>(new ChildDto(childrenService.createChild(child)));
    }

    @PatchMapping("/{childrenId}")
    public BaseResponse<ChildDto> updateChildToken(@PathVariable int childrenId, @RequestBody TokenDto tokenDto) {
        return new BaseResponse<>(new ChildDto(childrenService.updateChildToken(childrenId, tokenDto)));
    }

    @PostMapping("/{childrenId}/parents")
    public BaseResponse<?> linkChildAndParent(@PathVariable int childrenId, @RequestBody InviteCodeDto inviteCodeDto) {
        childrenService.linkChildAndParent(childrenId, inviteCodeDto);
        return new BaseResponse<>();
    }
}

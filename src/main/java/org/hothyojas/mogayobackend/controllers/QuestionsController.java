package org.hothyojas.mogayobackend.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hothyojas.mogayobackend.dtos.BaseResponse;
import org.hothyojas.mogayobackend.dtos.QuestionDto;
import org.hothyojas.mogayobackend.dtos.QuestionMetaDto;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.services.QuestionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("questions")
@RestController
public class QuestionsController {

    private final QuestionsService questionsService;

    @GetMapping("")
    public BaseResponse<List<QuestionMetaDto>> getQuestions(@RequestParam int parentId) {
        return new BaseResponse<>(questionsService.getQuestionsByParentId(parentId).stream().map(
            QuestionMetaDto::new).collect(
            Collectors.toList()));
    }

    @GetMapping("/{questionId}")
    public BaseResponse<QuestionDto> getQuestion(@PathVariable int questionId) {
        return new BaseResponse<>(new QuestionDto(questionsService.getQuestion(questionId)));
    }

    @PostMapping("")
    public void createQuestion(@RequestParam int parentId) {}

    @PatchMapping("/{questionId}/answers")
    public Question updateQuestion(@RequestParam int childId) {
        return null;
    }
}

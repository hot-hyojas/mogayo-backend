package org.hothyojas.mogayobackend.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.services.QuestionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("questions")
@RestController
public class QuestionsController {

    private final QuestionsService questionsService;

    @GetMapping("")
    public List<Question> getQuestions(@RequestParam int parentId) {
        return null;
    }

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable int questionId) {
        return null;
    }

    @PostMapping("")
    public void createQuestion(@RequestParam int parentId) {}

    @PatchMapping("/{questionId}/answers")
    public Question updateQuestion(@RequestParam int childId) {
        return null;
    }
}

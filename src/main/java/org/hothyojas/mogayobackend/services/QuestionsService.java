package org.hothyojas.mogayobackend.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    public List<Question> getQuestionsByParentId(int parentId) {
        return questionsRepository.findByParentId(parentId);
    }

    public Question getQuestion(int questionId) {
        return questionsRepository.findWithDeliveriesById(questionId).orElseThrow();
    }
}

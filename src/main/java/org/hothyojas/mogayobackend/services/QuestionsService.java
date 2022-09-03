package org.hothyojas.mogayobackend.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.repositories.DeliveryRepository;
import org.hothyojas.mogayobackend.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final DeliveryRepository deliveryRepository;

    private final ParentsService parentsService;

    public List<Question> getQuestionsByParentId(int parentId) {
        List<Question> questions = questionsRepository.findByParentId(parentId);
        questions.forEach(question -> question.setDeliveryCount(deliveryRepository.getDeliveryCountByQuestionId(
            question.getId())));
        return questions;
    }

    public Question getQuestion(int questionId) {
        return questionsRepository.findWithDeliveriesById(questionId).orElseThrow();
    }

    public Question createQuestion(Question question, int parentId) {
        question.setParent(parentsService.getParent(parentId));
        return questionsRepository.save(question);
    }
}

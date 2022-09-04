package org.hothyojas.mogayobackend.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.AnswerDto;
import org.hothyojas.mogayobackend.dtos.FcmMessageRequestDto;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.repositories.ChildrenRepository;
import org.hothyojas.mogayobackend.repositories.DeliveryRepository;
import org.hothyojas.mogayobackend.repositories.QuestionsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final DeliveryRepository deliveryRepository;

    private final ChildrenRepository childrenRepository;
    private final ParentsService parentsService;

    private final FirebaseCloudMessageService firebaseCloudMessageService;


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

    public void patchQuestionAnswerByChildId(Question question, Child child, AnswerDto answerDto) {
        Delivery delivery = deliveryRepository.getDeliveryByQuestionIdAndChildId(
            question.getId(),
            child.getId());
        delivery.setAnswer(String.valueOf(answerDto.getAnswer()));
        delivery.setResponded(answerDto.isResponsed());
        child.setAvailable(true);
        child.setHeartTemperature((float) (child.getHeartTemperature() + 0.1));
        deliveryRepository.save(delivery);
        childrenRepository.save(child);

        String token = "";
        String viewName = "createAnswerByQuestionId";
        String title = "";
        String body = "";
        int question_id = question.getId();
        int parents_id = question.getParent().getId();

        FcmMessageRequestDto dto = new FcmMessageRequestDto(viewName, title, body,
                                                            Optional.of(question_id),
                                                            Optional.of(parents_id));

        firebaseCloudMessageService.sendMessageTo(token, dto);
    }
}

package org.hothyojas.mogayobackend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.AnswerDto;
import org.hothyojas.mogayobackend.dtos.FcmMessageRequestDto;
import org.hothyojas.mogayobackend.entities.Child;
import org.hothyojas.mogayobackend.entities.Delivery;
import org.hothyojas.mogayobackend.dtos.QuestionRequestDto;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.entities.Question;
import org.hothyojas.mogayobackend.repositories.ChildrenRepository;
import org.hothyojas.mogayobackend.repositories.DeliveryRepository;
import org.hothyojas.mogayobackend.repositories.ParentsRepository;
import org.hothyojas.mogayobackend.repositories.QuestionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final DeliveryRepository deliveryRepository;

    private final ChildrenRepository childrenRepository;
    private final ParentsService parentsService;
    private final ParentsRepository parentsRepository;
    private final S3UploaderService s3UploaderService;

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

    @Transactional
    public Question createQuestion(QuestionRequestDto questionRequestDto, int parentId) {
        // parent 조회
        Parent parent = parentsRepository.findById(parentId).orElseThrow();

        // 사진 S3 업로드
        String photoUrl;
        try {
            photoUrl = s3UploaderService.uploadFiles(questionRequestDto.getPhoto(), String.format("%d/images", parentId));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // question 생성
        Question question = new Question(questionRequestDto.getContent(), parent);
        question.setPhoto(photoUrl);
        questionsRepository.save(question);

        // parent 업데이트
        parent.setUseCount(parent.getUseCount() - 1);
        parentsRepository.save(parent);

        return question;
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

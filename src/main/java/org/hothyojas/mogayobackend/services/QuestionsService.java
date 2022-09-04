package org.hothyojas.mogayobackend.services;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.dtos.QuestionRequestDto;
import org.hothyojas.mogayobackend.entities.Parent;
import org.hothyojas.mogayobackend.entities.Question;
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
    private final ParentsRepository parentsRepository;
    private final S3UploaderService s3UploaderService;

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
}

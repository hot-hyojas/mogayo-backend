package org.hothyojas.mogayobackend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hothyojas.mogayobackend.dtos.FcmMessageRequestDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirebaseCloudMessageService {

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/mogayo-backend/messages:send";
    private final ObjectMapper objectMapper;


    public void sendMessageTo(
        String targetToken,
        FcmMessageRequestDto requestDto
    ) {
        Message message = null;
        try {
            message = makeMessage(targetToken, requestDto);
            if (message == null) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
            return;
        }

        ApiFuture<String> result = FirebaseMessaging.getInstance().sendAsync(message);
        System.out.println("Successful FCM result = " + result);
    }

    private Message makeMessage(
        String targetToken,
        FcmMessageRequestDto requestDto
    ) {

        String viewName = requestDto.getViewName();
        Message message = null;

        if (viewName == "getQuestionById") {
            message = Message.builder()
                .putData("title", requestDto.getTitle())
                .putData("body", requestDto.getBody())
                .putData("questionId", String.valueOf(requestDto.getQuestionId().get()))
                .setToken(targetToken)
                .build();
        } else if (viewName == "createAnswerByQuestionId") {
            message = Message.builder()
                .putData("title", requestDto.getTitle())
                .putData("body", requestDto.getBody())
                .putData("questionId", String.valueOf(requestDto.getQuestionId().get()))
                .putData("childId", String.valueOf(requestDto.getChildId().get()))
                .setToken(targetToken)
                .build();
        } else if (viewName == "getMyPage") {
            message = Message.builder()
                .putData("title", requestDto.getTitle())
                .putData("body", requestDto.getBody())
                .putData("childId", String.valueOf(requestDto.getChildId().get()))
                .setToken(targetToken)
                .build();
        }

        return message;
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "mogayoFirebaseSecretKey.json";

        GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
            .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

}

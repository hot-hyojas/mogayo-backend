package org.hothyojas.mogayobackend.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionRequestDto {

    private String content;
    private MultipartFile photo;
}

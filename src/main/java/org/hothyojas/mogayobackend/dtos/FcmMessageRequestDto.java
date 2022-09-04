package org.hothyojas.mogayobackend.dtos;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FcmMessageRequestDto {

    String viewName;
    String title;
    String body;
    Optional<Integer> questionId;
    Optional<Integer> childId;
}


package org.hothyojas.mogayobackend.dtos;

import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.Data;

@Data
public class AnswerDto {

    private boolean isResponsed;
    private Optional<String> answer;
}

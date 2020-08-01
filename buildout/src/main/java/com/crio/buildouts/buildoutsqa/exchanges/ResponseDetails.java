package com.crio.buildouts.buildoutsqa.exchanges;

import com.crio.buildouts.buildoutsqa.dto.Options;
import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetails {

    @NotNull
    private String questionId;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String type;
    private Options options;
    private List<String> correct;
    private List<String> userAnswer;
    private String explanation;
    private boolean answerCorrect;
    //private Summary summary;

}

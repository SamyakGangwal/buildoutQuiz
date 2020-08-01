package com.crio.buildouts.buildoutsqa.exchanges;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionResponse {
    List<GetQuestions> questions;

}

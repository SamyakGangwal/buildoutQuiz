package com.crio.buildouts.buildoutsqa.exchanges;

import com.crio.buildouts.buildoutsqa.dto.Options;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetQuestions {

  @NotNull
  private String questionId;
  @NotNull
  private String title;
  @JsonIgnore
  private String description;
  @NotNull
  private String type;
  private LinkedHashMap<String, String> options;
  @JsonIgnore
  private List<String> correctAnswer;

}

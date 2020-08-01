package com.crio.buildouts.buildoutsqa.exchanges;

import com.crio.buildouts.buildoutsqa.dto.Options;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQuestions {

  @JsonProperty("questionId")
  public String questionId;
  @JsonProperty("title")
  public String title;
  @JsonProperty("type")
  public String type;
  @JsonProperty("options")
  public Options options;
  @JsonIgnore
  public String description;
  @JsonIgnore
  public List<String> correctAnswer = null;

}

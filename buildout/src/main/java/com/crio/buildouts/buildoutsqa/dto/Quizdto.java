package com.crio.buildouts.buildoutsqa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "questionId",
    "title",
    "description",
    "type",
    "options",
    "correctAnswer"
})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quizdto {

  @JsonProperty("questionId")
  private String questionId;
  @JsonProperty("title")
  private String title;
  @JsonProperty("description")
  private String description;
  @JsonProperty("type")
  private String type;
  @JsonProperty("options")
  private LinkedHashMap<String, String> options;
  @JsonProperty("correctAnswer")
  private List<String> correctAnswer = null;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
/*
"questionId": "001",
    "title": "What is the default IP address of localhost?",
    "description": "General question",
    "type": "objective-single",
    "options": {
      "1": "0.0.0.0",
      "2": "192.168.0.12",
      "3": "127.0.0.1",
      "4": "255.255.255.255"
    },
    "correctAnswer": [
      "4"
    ]
*/
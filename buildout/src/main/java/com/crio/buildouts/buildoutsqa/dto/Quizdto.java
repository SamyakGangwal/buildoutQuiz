package com.crio.buildouts.buildoutsqa.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

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
    public String questionId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("type")
    public String type;
    @JsonProperty("options")
    public Options options;
    @JsonProperty("correctAnswer")
    public List<String> correctAnswer = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

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
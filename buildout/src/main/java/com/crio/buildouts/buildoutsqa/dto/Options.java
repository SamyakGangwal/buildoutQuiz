package com.crio.buildouts.buildoutsqa.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "1",
    "2",
    "3",
    "4"
})
@Data
@NoArgsConstructor
public class Options {
//CHECKSTYLE:OFF
  @JsonProperty("1")
  public String _1;
  @JsonProperty("2")
  public String _2;
  @JsonProperty("3")
  public String _3;
  @JsonProperty("4")
  public String _4;
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

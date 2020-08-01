package com.crio.buildouts.buildoutsqa.models;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document(collection = "questions")
@NoArgsConstructor
@Builder
public class Question {

  @Id
  private String id;

  @NotNull
  @Field("moduleId")
  String moduleId;

  @NotNull
  @Field("questions")
  List<Quizdto> questions;


  /*@NotNull
    public String questionId;
    @NotNull
    public String moduleId;
    @NotNull
    public String title;
    @NotNull
    public String description;
    @NotNull
    public String type;
    @NotNull
    public Options options;
    @NotNull
    public List<String> correctAnswer = new ArrayList<>();
  */

}

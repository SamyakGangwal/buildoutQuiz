package com.crio.buildouts.buildoutsqa.controller;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.exchanges.QuestionResponse;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersRequests;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersresponse;
import com.crio.buildouts.buildoutsqa.service.QuestionService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Log4j2
@RequestMapping("/quiz")
public class QuestionsController {

  @Autowired
  private QuestionService questionService;

  @PutMapping("/{moduleId}")
  public ResponseEntity<String> saveQuestions(@RequestBody List<Quizdto> quizdto,
                                              @PathVariable String moduleId) {
    questionService.saveQuestions(quizdto,moduleId);

    return new ResponseEntity<>("", HttpStatus.OK);
  }

  @GetMapping("/{moduleId}")
  public ResponseEntity<QuestionResponse> getQuestions(@PathVariable String moduleId) {
    QuestionResponse questionResponse = questionService.findQuestions(moduleId);
    return ResponseEntity.ok().body(questionResponse);
  }

  @PostMapping("/{moduleId}")
  public ResponseEntity<ValidateAnswersresponse> checkAnswers(
      @PathVariable String moduleId,
      @Valid @RequestBody ValidateAnswersRequests request) {
    ValidateAnswersresponse validateAnswers = questionService
        .checkAnswers(moduleId, request);
    return ResponseEntity.ok().body(validateAnswers);
  }
}

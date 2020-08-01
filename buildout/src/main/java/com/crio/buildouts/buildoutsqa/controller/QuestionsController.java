package com.crio.buildouts.buildoutsqa.controller;

import com.crio.buildouts.buildoutsqa.exchanges.QuestionResponse;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersRequests;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersresponse;
import com.crio.buildouts.buildoutsqa.service.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/quiz")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    /*@PutMapping("/{moduleId}")
    public String saveQuestions(Quizdto quizdto, @PathVariable String moduleId) {

        return questionService.saveQuestions(quizdto,moduleId);
    }*/
    @GetMapping("/{moduleId}")
    public ResponseEntity<QuestionResponse> getQuestions(@PathVariable String moduleId) {
        QuestionResponse questionResponse = questionService.findQuestions(moduleId);
        return ResponseEntity.ok().body(questionResponse);
    }

    @PostMapping("/{moduleId}")
    public ResponseEntity<ValidateAnswersresponse> checkAnswers(@PathVariable String moduleId,
                                                                @Valid @RequestBody ValidateAnswersRequests request) {
        ValidateAnswersresponse validateAnswers = questionService.checkAnswers(moduleId,request);
        return ResponseEntity.ok().body(validateAnswers);
    }
}

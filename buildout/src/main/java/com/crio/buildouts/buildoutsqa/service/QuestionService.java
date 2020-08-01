package com.crio.buildouts.buildoutsqa.service;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.exchanges.GetQuestions;
import com.crio.buildouts.buildoutsqa.exchanges.QuestionResponse;
import com.crio.buildouts.buildoutsqa.exchanges.ResponseDetails;
import com.crio.buildouts.buildoutsqa.exchanges.Summary;
import com.crio.buildouts.buildoutsqa.exchanges.UserResponse;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersRequests;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersresponse;
import com.crio.buildouts.buildoutsqa.reposervice.QuestionRepositoryserivceimpl;
import com.crio.buildouts.buildoutsqa.reposervice.Repositoryservice;
import com.jayway.jsonpath.internal.function.numeric.Sum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionService {
  @Autowired
  private Repositoryservice questionrepo;

  /*public String saveQuestions(Quizdto quizdto, String moduleId) {
        Question question = Question.builder()
                .correctAnswer(quizdto.getCorrectAnswer())
                .description(quizdto.getDescription())
                .questionId(quizdto.getQuestionId())
                .title(quizdto.getTitle())
                .moduleId(moduleId)
                .type(quizdto.getType())
                .build();

        questionrepo.save(question);

        return question.getQuestionId();
    }
  */

  public QuestionResponse findQuestions(String moduleId) {

    ModelMapper modelMapper = new ModelMapper();
    List<Quizdto> loadquestions = questionrepo.findallquestions(moduleId);

    List<GetQuestions> loadAllquestions = new ArrayList<>();

    for (Quizdto questionDto : loadquestions) {
      loadAllquestions.add(modelMapper.map(questionDto, GetQuestions.class));
    }

    return new QuestionResponse(loadAllquestions);
  }

  public ValidateAnswersresponse checkAnswers(String moduleId,
                                              ValidateAnswersRequests answerRequest) {
    List<Quizdto> questionsAsked = questionrepo.findallquestions(moduleId);

    List<UserResponse> checkuseranswers = answerRequest.getResponses();

    ValidateAnswersresponse checkAnswerResponse = verifyAnswer(questionsAsked, checkuseranswers);

    return checkAnswerResponse;
  }

  private ValidateAnswersresponse verifyAnswer(List<Quizdto> questionsAsked,
                                               List<UserResponse> checkuseranswers) {
    ValidateAnswersresponse result = new ValidateAnswersresponse();

    Map<String, Quizdto> quizMap = new HashMap<>();

    for (Quizdto quizdto : questionsAsked) {
      quizMap.put(quizdto.getQuestionId(), quizdto);
    }

    //ValidateAnswersresponse validateAnswersresponse = new ValidateAnswersresponse();
    List<ResponseDetails> questionResults = new ArrayList<>();
    Summary summary = new Summary();

    int score = 0;
    for (UserResponse userResponse : checkuseranswers) {
      ResponseDetails responseDetails = new ResponseDetails();

      Quizdto qdto = quizMap.get(userResponse.getQuestionId());

      //Quizdto responsedto = new Quizdto();

      //for response details
      responseDetails.setQuestionId(qdto.getQuestionId());
      responseDetails.setTitle(qdto.getTitle());
      responseDetails.setDescription(qdto.getDescription());
      responseDetails.setType(qdto.getType());
      responseDetails.setOptions(qdto.getOptions());
      responseDetails.setCorrect(qdto.getCorrectAnswer());
      responseDetails.setUserAnswer(userResponse.getUserResponse());
      responseDetails.setExplanation(null);

      if (checkAnswer(responseDetails.getUserAnswer(),
          responseDetails.getCorrect())) {
        responseDetails.setAnswerCorrect(true);
        score++;
      } else {
        responseDetails.setAnswerCorrect(false);
      }

      questionResults.add(responseDetails);
    }

    result.setQuestions(questionResults);

    summary.setScore(score);
    summary.setTotal(checkuseranswers.size());

    result.setSummary(summary);

    return result;
  }

  private boolean checkAnswer(List<String> userResponse1, List<String> actualAnswer1) {
    Collections.sort(userResponse1);
    Collections.sort(actualAnswer1);

    return actualAnswer1.equals(userResponse1);
  }

}

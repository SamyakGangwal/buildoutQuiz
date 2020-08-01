package com.crio.buildouts.buildoutsqa.service;

import com.crio.buildouts.buildoutsqa.exchanges.*;
import com.crio.buildouts.buildoutsqa.reposervice.QuestionRepositoryserivceimpl;
import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.reposervice.repositoryservice;
import com.jayway.jsonpath.internal.function.numeric.Sum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    private repositoryservice questionrepo;

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
    }*/

    public QuestionResponse findQuestions(String moduleId) {

        ModelMapper modelMapper = new ModelMapper();
        List<Quizdto> loadquestions = questionrepo.findallquestions(moduleId);

        List<GetQuestions> loadAllquestions = new ArrayList<>();

        for (Quizdto questionDto : loadquestions) {
            loadAllquestions.add(modelMapper.map(questionDto, GetQuestions.class));
        }

        return new QuestionResponse(loadAllquestions);
    }

    public ValidateAnswersresponse checkAnswers(String moduleId, ValidateAnswersRequests AnswerRequest) {
        List<Quizdto> questionsAsked = questionrepo.findallquestions(moduleId);

        List<UserResponse> checkuseranswers = AnswerRequest.getResponses();

        ValidateAnswersresponse CheckAnswerResponse = verifyAnswer(questionsAsked, checkuseranswers);

        return CheckAnswerResponse;
    }

    private ValidateAnswersresponse verifyAnswer(List<Quizdto> questionsAsked,List<UserResponse> checkuseranswers) {
        ValidateAnswersresponse result = new ValidateAnswersresponse();

        Map<String,Quizdto> QuizMap = new HashMap<>();

        for (Quizdto quizdto:questionsAsked) {
            QuizMap.put(quizdto.getQuestionId(),quizdto);
        }

        ValidateAnswersresponse validateAnswersresponse = new ValidateAnswersresponse();
        List<ResponseDetails> QuestionResults = new ArrayList<>();
        Summary summary = new Summary();

        int score = 0;
        for (UserResponse userResponse : checkuseranswers) {
            ResponseDetails responseDetails = new ResponseDetails();

            Quizdto qdto = QuizMap.get(userResponse.getQuestionId());

            Quizdto responsedto = new Quizdto();

            //for response details
            responseDetails.setQuestionId(qdto.getQuestionId());
            responseDetails.setTitle(qdto.getTitle());
            responseDetails.setDescription(qdto.getDescription());
            responseDetails.setType(qdto.getType());
            responseDetails.setOptions(qdto.getOptions());
            responseDetails.setCorrect(qdto.getCorrectAnswer());
            responseDetails.setUserAnswer(userResponse.getUserResponse());
            responseDetails.setExplanation(null);

            if(checkAnswer(responseDetails.getUserAnswer()
                    ,responseDetails.getCorrect())) {
                responseDetails.setAnswerCorrect(true);
                score++;
            }
            else {
                responseDetails.setAnswerCorrect(false);
            }

            QuestionResults.add(responseDetails);
        }

        result.setQuestions(QuestionResults);

        summary.setScore(score);
        summary.setTotal(checkuseranswers.size());

        result.setSummary(summary);

        return result;
    }

    private boolean checkAnswer(List<String> UserResponse1,List<String> ActualAnswer1) {
        Collections.sort(UserResponse1);
        Collections.sort(ActualAnswer1);

        return ActualAnswer1.equals(UserResponse1);
    }

}

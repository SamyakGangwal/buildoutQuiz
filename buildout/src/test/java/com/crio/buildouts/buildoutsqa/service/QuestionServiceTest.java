package com.crio.buildouts.buildoutsqa.service;

import com.crio.buildouts.buildoutsqa.exchanges.QuestionResponse;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersRequests;
import com.crio.buildouts.buildoutsqa.exchanges.ValidateAnswersresponse;
import com.crio.buildouts.buildoutsqa.reposervice.repositoryservice;
import com.crio.buildouts.buildoutsqa.buildoutsapp;
import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.util.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {buildoutsapp.class})
@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    private final String MODULEID = "1";

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private repositoryservice repositoryserviceimpl;

    private List<Quizdto> questionList = null;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        questionList = loadAllQuestions();
    }

    @Test
    public void findQuestionstest() throws JsonProcessingException, JSONException {
        assertNotNull(repositoryserviceimpl);
        assertNotNull(questionService);

        when(repositoryserviceimpl
                .findallquestions(anyString()))
                .thenReturn(questionList);
        QuestionResponse getquestionresponse = questionService.findQuestions(MODULEID);

        assertEquals(questionList.size(),getquestionresponse.getQuestions().size());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(repositoryserviceimpl, times(1))
                .findallquestions(captor.capture());

        String ResponseString = objectMapper.writeValueAsString(getquestionresponse);
        String shouldBeResponse = Utility
                .fileToString("fixtures/sample_get_questions_response.json");
        JSONAssert.assertEquals(shouldBeResponse, ResponseString, true);

    }

    @Test
    public void ValidateAnswersresponsetest() throws IOException, JSONException {

        assertNotNull(repositoryserviceimpl);
        assertNotNull(questionService);

        when(repositoryserviceimpl
                .findallquestions(anyString()))
                .thenReturn(questionList);
        String Content = Utility
                .fileToString("fixtures/sample_submit_question_request.json");

        ValidateAnswersRequests validateAnswerRequest = objectMapper
                .readValue(Content,ValidateAnswersRequests.class);

        ValidateAnswersresponse validateAnswersresponse = questionService
                .checkAnswers(MODULEID,validateAnswerRequest);
        assertEquals(questionList.size(),validateAnswersresponse.getQuestions().size());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(repositoryserviceimpl, times(1))
                .findallquestions(captor.capture());

        String responseString = objectMapper.writeValueAsString(validateAnswersresponse);
        String shouldBeResponse =  Utility
                .fileToString("fixtures/sample_submit_question_response.json");
        JSONAssert.assertEquals(shouldBeResponse, responseString, true);
    }

    private List<Quizdto> loadAllQuestions() throws IOException {
        String content = Utility.fileToString("initial_data_load.json");
        return objectMapper.readValue(content, new TypeReference<List<Quizdto>>() {});
    }
}
/*
* @Test
    public void ValidateAnswersresponsetest throws IOException, {

        assertNotNull(repositoryserviceimpl);
        assertNotNull(questionService);

        when(repositoryserviceimpl
                     .findallquestions(anyString()))
            .thenReturn(questionList);

        String requestContent = Utility
                .fileToString("fixtures/sample_submit_question_request.json");

        ValidateAnswersRequests validateAnswerRequest = objectMapper
                .readValue(requestContent, ValidateAnswersRequests.class);

        ValidateAnswersresponse validateAnswerResponse = questionService
                .checkAnswers(MODULEID, validateAnswerRequest);
        assertEquals(questionList.size(), validateAnswerResponse.getQuestionDetails().size());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(quizRepositoryService, times(1))
            .getQuestions(captor.capture());

        String responseString = objectMapper.writeValueAsString(validateAnswerResponse);
        String shouldBeResponse =  Utility
            .fileToString(FIXTURE + "/sample_submit_question_response.json");
        JSONAssert.assertEquals(shouldBeResponse, responseString, true);
    }
* */
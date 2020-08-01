package com.crio.buildouts.buildoutsqa.controller;

import com.crio.buildouts.buildoutsqa.buildoutsapp;
import com.crio.buildouts.buildoutsqa.dto.Options;
import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.service.QuestionService;
import com.crio.buildouts.buildoutsqa.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {buildoutsapp.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuestionsControllerTest {

    @InjectMocks
    private QuestionsController questionsController;

    @Mock
    private QuestionService questionService;

    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(questionsController).build();
    }

    private static final String QUIZ_API_URI = "/quiz/";

    @Test
    void getQuestionsTest() throws Exception {

        URI uri = UriComponentsBuilder
                .fromPath("/quiz/")
                .pathSegment("1")
                .build().toUri();

        assertEquals(QUIZ_API_URI + "1", uri.toString());

        MockHttpServletResponse response = mvc.perform(
                get(uri.toString()).accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void checkAnswersTest() throws Exception {

        String requestContent = Utility
                .fileToString("fixtures/sample_submit_question_request.json");

        URI uri = UriComponentsBuilder
                .fromPath("/quiz/")
                .pathSegment("1")
                .build().toUri();

        MockHttpServletResponse response = mvc.perform(
                get(uri.toString()).accept(APPLICATION_JSON)
                .content(requestContent)
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
package com.crio.buildouts.buildoutsqa.reposervice;

import com.crio.buildouts.buildoutsqa.buildoutsapp;
import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.models.Question;
import com.crio.buildouts.buildoutsqa.util.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
//import redis.embedded.RedisServer;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {buildoutsapp.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuestionRepositoryserivceimplTest {

    List<Quizdto> allquestions = new ArrayList<>();

    @Autowired
    private repositoryservice Repositoryservice;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    void setup() throws IOException {
        allquestions = listOfquestions();
        Question test_questions = new Question();
        test_questions.setModuleId("1");
        test_questions.setQuestions(allquestions);
        mongoTemplate.save(test_questions, "questions");
    }

    @AfterEach
    void teardown() {
        mongoTemplate.dropCollection("questions");
    }

    @Test
    void findallquestionstest(@Autowired MongoTemplate mongoTemplate) {
        List<Quizdto> testquiz = Repositoryservice.findallquestions("1");

        assertEquals(3,testquiz.size());
    }

    private List<Quizdto> listOfquestions() throws IOException {
        String fixture =
                Utility.fileToString("initial_data_load.json");

        return objectMapper.readValue(fixture, new TypeReference<List<Quizdto>>() {
        });
    }
}
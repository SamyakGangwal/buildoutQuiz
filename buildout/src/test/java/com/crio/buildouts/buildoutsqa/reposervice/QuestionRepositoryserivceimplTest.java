package com.crio.buildouts.buildoutsqa.reposervice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crio.buildouts.buildoutsqa.Buildoutsapp;
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

@SpringBootTest(classes = {Buildoutsapp.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuestionRepositoryserivceimplTest {

  List<Quizdto> allquestions = new ArrayList<>();

  @Autowired
  private Repositoryservice repositoryservice;
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private ObjectMapper objectMapper;


  @BeforeEach
  void setup() throws IOException {
    allquestions = listOfquestions();
    Question testquestions = new Question();
    testquestions.setModuleId("1");
    testquestions.setQuestions(allquestions);
    mongoTemplate.save(testquestions, "questions");
  }

  @AfterEach
  void teardown() {
    mongoTemplate.dropCollection("questions");
  }

  @Test
  void findallquestionstest(@Autowired MongoTemplate mongoTemplate) {
    List<Quizdto> testquiz = repositoryservice.findallquestions("1");

    assertEquals(3, testquiz.size());
  }

  private List<Quizdto> listOfquestions() throws IOException {
    String fixture =
        Utility.fileToString("initial_data_load.json");

    return objectMapper.readValue(fixture, new TypeReference<List<Quizdto>>() {
    });
  }
}
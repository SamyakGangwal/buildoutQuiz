package com.crio.buildouts.buildoutsqa;

import static org.assertj.core.api.Assertions.assertThat;

import com.crio.buildouts.buildoutsqa.controller.QuestionsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildoutsappTest {

  @Autowired
  QuestionsController questionsController;

  @Test
  public void testapp() throws Exception {
    assertThat(questionsController).isNotNull();
  }

}
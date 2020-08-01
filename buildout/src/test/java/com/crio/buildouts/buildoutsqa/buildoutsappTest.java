package com.crio.buildouts.buildoutsqa;

import com.crio.buildouts.buildoutsqa.controller.QuestionsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class buildoutsappTest {

    @Autowired
    QuestionsController questionsController;

    @Test
    public void testapp() throws Exception {
        assertThat(questionsController).isNotNull();
    }

}
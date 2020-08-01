package com.crio.buildouts.buildoutsqa.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class QuizdtoTest {

  @Test
  public void testserialization() throws IOException, JSONException {
    final String input =
        "{\n"
            + "    \"questionId\": \"001\",\n"
            + "    \"title\": \"What is the default IP address of localhost?\",\n"
            + "    \"description\": \"General question\",\n"
            + "    \"type\": \"objective-single\",\n"
            + "    \"options\": {\n"
            + "      \"1\": \"0.0.0.0\",\n"
            + "      \"2\": \"192.168.0.12\",\n"
            + "      \"3\": \"127.0.0.1\",\n"
            + "      \"4\": \"255.255.255.255\"\n"
            + "    },\n"
            + "    \"correctAnswer\": [\n"
            + "      \"4\"\n"
            + "    ]\n"
            + "  }";

    Quizdto quiz = new Quizdto();

    quiz = new ObjectMapper().readValue(input, Quizdto.class);

    String jsonstring = "";
    jsonstring = new ObjectMapper().writeValueAsString(quiz);

    JSONAssert.assertEquals(input, jsonstring, true);
  }

}
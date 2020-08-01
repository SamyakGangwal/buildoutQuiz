package com.crio.buildouts.buildoutsqa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/*
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = QuestionsController.class)
@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class RestaurantControllerintegrationtest {

    private static final String QUIZ_API_URI = "/quiz/";

    @InjectMocks
    QuestionsController questionsController;

    @MockBean
    QuestionService questionService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(questionsController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void correctQueryOkResponseAndListOfRestaurants() throws Exception {
        //Sample Response

        String sampleResponse = "001";

        when(questionService
                .saveQuestions(any(Quizdto.class),anyString()))
                .thenReturn(sampleResponse);



        URI uri = UriComponentsBuilder
                .fromPath(QUIZ_API_URI)
                .pathSegment("1")
                .build().toUri();

        assertEquals(QUIZ_API_URI + "1", uri.toString());

        Options options = new Options();
        options._1 = "a";
        options._2 = "b";
        options._3 = "c";
        options._4 = "d";
        Quizdto quizdto = Quizdto.builder()
                .correctAnswer(Collections.singletonList("4"))
                .description("asdfgh")
                .questionId("001")
                .title("qwetrrt")
                .type("piogfgjfohn")
                .options(options)
                .build();

        MockHttpServletResponse response = mvc.perform(
            put(uri.toString(),quizdto).accept(APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


        assertEquals("001", response.getContentAsString());


    }
}*/

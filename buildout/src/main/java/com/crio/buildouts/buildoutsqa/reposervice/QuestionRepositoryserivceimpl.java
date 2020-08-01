package com.crio.buildouts.buildoutsqa.reposervice;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.models.Question;
import com.crio.buildouts.buildoutsqa.Repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.inject.Provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;



@Service
public class QuestionRepositoryserivceimpl implements repositoryservice {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;


    @Override
    public List<Quizdto> findallquestions(String moduleId) {
        List<Quizdto> questions = null;

        ModelMapper modelmapper = modelMapperProvider.get();

        List<Question> getquestions = extractMongoData(mongoTemplate,moduleId);

        if(getquestions.size()>0) {
            questions = getquestions.get(0).getQuestions();
        }

        /*
        * mongoTemplate.find(new Query().addCriteria(Criteria.where("moduleId").is(moduleId)),
            QuizQuestionsEntity.class);
          List<QuestionDto> questions = null;
          if (quizQuestionsEntities.size() > 0) {
          questions = quizQuestionsEntities.get(0).getQuestions();
          }
        * */

        return questions;
    }

    public List<Question> extractMongoData(MongoTemplate mongoTemplate,
                                           String moduleId) {
        List<Question> getQuestions = mongoTemplate.find(new Query().addCriteria(Criteria.where("moduleId").is(moduleId))
                ,Question.class);

        return getQuestions;
    }
}

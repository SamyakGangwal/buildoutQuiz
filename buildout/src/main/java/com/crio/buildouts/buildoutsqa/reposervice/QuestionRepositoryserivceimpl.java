package com.crio.buildouts.buildoutsqa.reposervice;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;
import com.crio.buildouts.buildoutsqa.models.Question;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRepositoryserivceimpl implements Repositoryservice {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;


  @Override
  public List<Quizdto> findallquestions(String moduleId) {
    List<Quizdto> questions = new ArrayList<>();
    String questionId = "";
    List<Question> getquestions = extractMongoData(mongoTemplate, moduleId,questionId);

    if (getquestions.size() > 0) {
      questions = getquestions.get(0).getQuestions();  
    }
    
    
    return questions;
  }

  private List<Question> extractMongoData(MongoTemplate mongoTemplate,
                                         String moduleId,String questionId) {
    
    Query query = new Query();

    query.addCriteria(new Criteria().andOperator(
        Criteria.where("moduleId").is(moduleId)));

    List<Question> getQuestions = mongoTemplate
        .find(query,Question.class);

    return getQuestions;
  }

  @Override
  public void savequestions(List<Quizdto> quizdtos,String moduleId) {
    Question question = new Question();

    question.setModuleId(moduleId);
    question.setQuestions(quizdtos);

    mongoTemplate.dropCollection("questions");
    mongoTemplate.save(question,"questions");
  }
}

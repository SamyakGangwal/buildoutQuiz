package com.crio.buildouts.buildoutsqa.repository;

import com.crio.buildouts.buildoutsqa.models.Question;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

}

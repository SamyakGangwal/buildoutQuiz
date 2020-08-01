package com.crio.buildouts.buildoutsqa.Repository;


import com.crio.buildouts.buildoutsqa.models.Question;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question,String> {

}

package com.crio.buildouts.buildoutsqa.reposervice;

import com.crio.buildouts.buildoutsqa.dto.Quizdto;

import java.util.List;


public interface Repositoryservice {

  public List<Quizdto> findallquestions(String moduleId);

}

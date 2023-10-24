package com.nijin.Xmeme.service;


import com.nijin.Xmeme.data.MemeEntity;
import com.nijin.Xmeme.exchange.GetMemesResponse;
import com.nijin.Xmeme.repository.MemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemesService {

  @Autowired
  private MemesRepository Repository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public boolean addMeme(MemeEntity memedto) {

    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(memedto.getName()));
    query.addCriteria(Criteria.where("url").is(memedto.getUrl()));
    query.addCriteria(Criteria.where("caption").is(memedto.getCaption()));

    boolean alreadyExist = mongoTemplate.exists(query, "memes");

    if (alreadyExist) return true;
    else Repository.save(memedto);

    return false;
  }

  public GetMemesResponse getMemes() {

    List<MemeEntity> memeFetch = Repository.findAll(Sort.by(Sort.Direction.DESC, "id"));

    int size = memeFetch.size();

    if (size==0) {
      return new GetMemesResponse(memeFetch);
    } else if (size < 100) {
      return new GetMemesResponse(memeFetch);
    } else {
      return new GetMemesResponse(memeFetch.stream().limit(100).collect(Collectors.toList()));
    }
  }

  public Optional<MemeEntity> getMeme(String memeid) {

    return Repository.findById(memeid);
  }
}

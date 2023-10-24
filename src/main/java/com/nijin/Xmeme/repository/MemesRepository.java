package com.nijin.Xmeme.repository;


import com.nijin.Xmeme.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemesRepository extends MongoRepository<MemeEntity, String> {

}

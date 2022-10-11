package cl.yai.amandaia.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AIAMessageCustomRepositoryImpl implements AIAMessageCustomRepository{

    @Autowired
    private MongoTemplate mongoTemplate;
    
    
}

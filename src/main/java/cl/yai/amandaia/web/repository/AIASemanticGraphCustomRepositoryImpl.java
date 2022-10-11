package cl.yai.amandaia.web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AIASemanticGraphCustomRepositoryImpl implements AIASemanticGraphCustomRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
}

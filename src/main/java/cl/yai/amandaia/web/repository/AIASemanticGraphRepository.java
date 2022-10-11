package cl.yai.amandaia.web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import cl.yai.amandaia.web.model.AIASemanticGraph;

public interface AIASemanticGraphRepository extends MongoRepository<AIASemanticGraph, String>, AIASemanticGraphCustomRepository{

}

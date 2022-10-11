package cl.yai.amandaia.web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.yai.amandaia.web.model.AIAMessage;

public interface AIAMessageRepository extends MongoRepository<AIAMessage, String>, AIAMessageCustomRepository{

}

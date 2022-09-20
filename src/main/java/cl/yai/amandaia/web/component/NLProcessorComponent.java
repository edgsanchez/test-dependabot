package cl.yai.amandaia.web.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import cl.yai.amandaia.web.model.AIAMessage;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Component
public class NLProcessorComponent {

	private StanfordCoreNLP pipeline;
	private static Logger log = LoggerFactory.getLogger(NLProcessorComponent.class);
	
	public NLProcessorComponent() {
		super();
		this.pipeline = new StanfordCoreNLP("spanish");
		log.info("Init CoreNLP Ready");
	}

	public void getCoreDocument(String txt) {
		//Gson gson = new GsonBuilder().create();
		//AIAMessage aiaMessage = gson.fromJson(txt, AIAMessage.class);
		//CoreDocument document = this.pipeline.processToCoreDocument(aiaMessage.getBody().getTxt());
		//log.info(String.valueOf(document.sentences().get(0).dependencyParse()));
	}
	
	
	
	
}

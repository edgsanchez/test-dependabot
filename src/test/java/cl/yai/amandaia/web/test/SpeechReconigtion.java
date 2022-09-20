package cl.yai.amandaia.web.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

public class SpeechReconigtion {

	private static Logger log = LoggerFactory.getLogger(SpeechReconigtion.class);

	@Test @Order(1)
	public void espTest() {
		Configuration configuration = new Configuration();
		String path = new ClassPathResource("/sphinx-models/es.dict").getPath();
		log.info(path);
		// Load model from the jar
		configuration
				.setAcousticModelPath("/Users/edgsanchez/trabajos/yai/amanda-IA/src/main/resources/sphinx-model/es-es");
		configuration
				.setDictionaryPath("/Users/edgsanchez/trabajos/yai/amanda-IA/src/main/resources/sphinx-model/es.dict");
		configuration
				.setLanguageModelPath("/Users/edgsanchez/trabajos/yai/amanda-IA/src/main/resources/sphinx-model/es-20k.lm");

		try {
			StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
					configuration);
			log.info("XDDDDDDDD2222");
			InputStream is = new ClassPathResource("/sounds/demo_esp.wav").getInputStream();
	        recognizer.startRecognition(is);
	        SpeechResult result;
	        while ((result = recognizer.getResult()) != null) {

	            System.out.format("Hypothesis: %s\n", result.getHypothesis());

	            System.out.println("List of recognized words and their times:");
	            for (WordResult r : result.getWords()) {
	                System.out.println(r);
	            }

	            System.out.println("Best 3 hypothesis:");
	            for (String s : result.getNbest(3))
	                System.out.println(s);

	        }
	        recognizer.stopRecognition();	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test @Order(2)
	public void engTest() {
		
		
	}
}

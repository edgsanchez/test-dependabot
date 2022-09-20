package cl.yai.amandaia.web.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.yai.amandaia.web.component.NLProcessorComponent;

public class NPLProcessorComponentTest {
	private static Logger log = LoggerFactory.getLogger(NPLProcessorComponentTest.class);
	
	private static NLProcessorComponent nlpComponent;
	//http://davidvandegrift.com/blog?id=62
	
	@BeforeAll
	public static void initNPL() {
		log.info("Init NPL");
		nlpComponent = new NLProcessorComponent();		
	}
	
	@Test
	public void dependencyParse01() {
		log.info("dependencyParse01");
		String txt1 = "{\"head\": {\"producer\": \"aia-language-speech_recognition\", \"creationDate\": \"2022-09-19 20:24:38\"}, \"body\": {\"txt\": \"Ella manda modo análisis\"}}";
		nlpComponent.getCoreDocument("{\"head\": {\"producer\": \"aia-language-speech_recognition\", \"creationDate\": \"2022-09-19 20:30:14\"}, \"body\": {\"txt\": \"y fuiste durante años\"}}");
	}
}

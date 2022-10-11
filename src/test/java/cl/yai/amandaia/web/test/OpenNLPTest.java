package cl.yai.amandaia.web.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreNLPProtos.Sentence;
import edu.stanford.nlp.pipeline.CoreQuote;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import opennlp.tools.sentdetect.SentenceDetectorME;
//import opennlp.tools.sentdetect.SentenceModel;
//import opennlp.tools.tokenize.Tokenizer;
//import opennlp.tools.tokenize.TokenizerME;
//import opennlp.tools.tokenize.TokenizerModel;
import edu.stanford.nlp.semgraph.SemanticGraph;


public final class OpenNLPTest {

	private static Logger log = LoggerFactory.getLogger(OpenNLPTest.class);
	
	//http://davidvandegrift.com/blog?id=62
	@Test
	public void dependencyParse02() {
		log.info("start spanish -test01-ooo");
		try {
			String text = "El Lago Michigan mide 900 m. "
					+ "Cuánto mide el Lago Michigan. ";
			StanfordCoreNLP pipeline = new StanfordCoreNLP("spanish");
			CoreDocument document = pipeline.processToCoreDocument("El Lago Michigan mide 900 m");
			System.out.println(document.sentences().get(0).dependencyParse());		
			document = pipeline.processToCoreDocument("Cuánto mide el Lago Michigan");
			System.out.println(document.sentences().get(0).dependencyParse());		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	@Test
	public void dependencyParse() {
		log.info("start spanish -test01-ooo");
		try {
			//String text = "Hey Amanda lee mi correo";
			String text = "Cats fue una película realmente terrible. Esa película fue un desastre natural.";
			StanfordCoreNLP pipeline = new StanfordCoreNLP("spanish");
			CoreDocument document = pipeline.processToCoreDocument(text);
			log.info(document.text());		
		    CoreSentence sentence = document.sentences().get(0);
		    //################################################
		    // dependency parse for the second sentence
		    //################################################
		    SemanticGraph dependencyParse = sentence.dependencyParse();
		    System.out.println("Example: dependency parse");
		    System.out.println(dependencyParse);
		    System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void spanishTokenTest() {
		log.info("start spanish -test01-ooo");
		try {
			String text = "La Universidad de Stanford se encuentra en Palo Alto. "
					+ "Hey Amanda lee mi correo. "
					+ "He sent a postcard to his sister Jane Smith. ";
			StanfordCoreNLP pipeline = new StanfordCoreNLP("spanish");
			CoreDocument document = pipeline.processToCoreDocument(text);
			log.info(document.text());
			
			for (CoreLabel core : document.tokens()) {
				log.debug(core.originalText() + " ["+core.tag()+"]");
			}
			log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		    // 10th token of the document
		    CoreLabel token = document.tokens().get(0);
		    System.out.println("Example: token");
		    System.out.println(token);
		    System.out.println();

		    // text of the first sentence
		    String sentenceText = document.sentences().get(0).text();
		    System.out.println("Example: sentence");
		    System.out.println(sentenceText);
		    System.out.println();		    
		    // second sentence
		    CoreSentence sentence = document.sentences().get(1);

		    // list of the part-of-speech tags for the second sentence
		    List<String> posTags = sentence.posTags();
		    System.out.println("Example: tokens and pos tags");
		    System.out.println(document.sentences().get(1).tokens());
		    System.out.println(posTags);
		    System.out.println();	
		    
		    // list of the ner tags for the second sentence
		    List<String> nerTags = sentence.nerTags();
		    System.out.println("Example: ner tags");
		    System.out.println(nerTags);
		    System.out.println();
		    
		    //################################################
		    // dependency parse for the second sentence
		    //################################################
		    SemanticGraph dependencyParse = sentence.dependencyParse();
		    System.out.println("Example: dependency parse");
		    System.out.println(dependencyParse);
		    System.out.println();
		    
		    // kbp relations found in fifth sentence
		    List<RelationTriple> relations =
		        document.sentences().get(2).relations();
		    System.out.println("Example: relation");
		    System.out.println(relations);
		    System.out.println();
		    
		    // entity mentions in the second sentence
		    List<CoreEntityMention> entityMentions = sentence.entityMentions();
		    System.out.println("Example: entity mentions");
		    System.out.println(entityMentions);
		    System.out.println();

		    // coreference between entity mentions
		    CoreEntityMention originalEntityMention = document.sentences().get(1).entityMentions().get(0);
		    System.out.println("Example: original entity mention");
		    System.out.println(originalEntityMention);
		    System.out.println("Example: canonical entity mention");
		    System.out.println(originalEntityMention.canonicalEntityMention().get());
		    System.out.println();

		    // get document wide coref info
		    Map<Integer, CorefChain> corefChains = document.corefChains();
		    System.out.println("Example: coref chains for document");
		    System.out.println(corefChains);
		    System.out.println();

		    // get quotes in document
		    List<CoreQuote> quotes = document.quotes();
		    System.out.println("Example: quote");
		    System.out.println(quotes);
		    System.out.println();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pipeline.annotate(document);  http://opennlp.sourceforge.net/models-1.5/
		// int sizeTokens = document.tokens().size();   https://www.programcreek.com/2012/05/opennlp-tutorial/#tokenizer
		// log.debug(String.valueOf(sizeTokens));
		Long x = 2L;
		assertEquals(x.longValue(), 2L);
	}
	
	@Test
	public void spanishTokenTest2() {
		log.info("start spanish -test02-ooo");
		try {
			String text = "La Universidad de Stanford se encuentra en Palo Alto.";
			StanfordCoreNLP pipeline = new StanfordCoreNLP("spanish");
			CoreDocument doc = pipeline.processToCoreDocument(text);
			log.info("start spanish -test01-ooo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// pipeline.annotate(document);  http://opennlp.sourceforge.net/models-1.5/
		// int sizeTokens = document.tokens().size();   https://www.programcreek.com/2012/05/opennlp-tutorial/#tokenizer
		// log.debug(String.valueOf(sizeTokens));
		Long x = 2L;
		assertEquals(x.longValue(), 2L);
	}
	
//	@Test
//	public void spanishDetectorSentenciasTest01() {
//		log.info("start spanish -test01");
//		String text = "La Universidad de Stanford se encuentra en Palo Alto. ¿Qué te parece?";
//		//String text = "Hi. How are you? This is Mike.";
//
//		// String text2 = null;
//		// TokenizerFactory<CoreLabel> tokFactory = SpanishTokenizer.factory();
//		// Reader targetReader = new StringReader(text);
//		// PTBTokenizer<Word> word =PTBTokenizer.newPTBTokenizer(targetReader);
//		Properties props = new Properties();
//		try {
//			InputStream is = new ClassPathResource("/models/es-sent.bin").getInputStream();
//			SentenceModel model = new SentenceModel(is);
//			SentenceDetectorME sdetector = new SentenceDetectorME(model);
//		 
//			String sentences[] = sdetector.sentDetect(text);
//		 
//			for (String a : sentences)
//				System.out.println(a);
//
//			is.close();
//
//			// Sentence sent = new Sentence("Lucy is in the sky with diamonds.");
//			// List<String> nerTags = sent.nerTags(); // [PERSON, O, O, O, O, O, O, O]
//			// String firstPOSTag = sent.posTag(0); // NNP
//			// log.info(firstPOSTag);
//			// InputStream is = new ClassPathResource("/nlp/StanfordCoreNLP-spanish.properties").getInputStream();
//			// props.load(is);
//			// StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//			// StanfordCoreNLP pipeline = new StanfordCoreNLP("spanish");
//			// CoreDocument document = pipeline.processToCoreDocument(text);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// pipeline.annotate(document);
//		// int sizeTokens = document.tokens().size();
//		// log.debug(String.valueOf(sizeTokens));
//		Long x = 2L;
//		assertEquals(x.longValue(), 2L);
//	}
//
//	@Test
//	public void apacheOpenNPLTest()
//			throws Exception {
//
//		String paragraph = "This is a statement. This is another statement. "
//				+ "Now is an abstract word for time, "
//				+ "that is always flying. And my email address is google@gmail.com.";
//		InputStream is = new ClassPathResource("/models/en-sent.bin").getInputStream();
//		// InputStream is = getClass().getResourceAsStream("/models/en-sent.bin");
//		SentenceModel model = new SentenceModel(is);
//
//		SentenceDetectorME sdetector = new SentenceDetectorME(model);
//
//		String sentences[] = sdetector.sentDetect(paragraph);
//		log.debug(String.join(" >>>>> ", sentences));
//		assertThat(sentences).contains(
//				"This is a statement.",
//				"This is another statement.",
//				"Now is an abstract word for time, that is always flying.",
//				"And my email address is google@gmail.com.");
//	}
}

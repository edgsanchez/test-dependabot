package cl.yai.amandaia.web.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.yai.amandaia.web.model.AIAMessage;
import cl.yai.amandaia.web.model.AIASemanticGraph;
import cl.yai.amandaia.web.model.AIASemanticGraphNode;
import cl.yai.amandaia.web.repository.AIAMessageRepository;
import cl.yai.amandaia.web.repository.AIASemanticGraphRepository;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.ArrayCoreMap;
import edu.stanford.nlp.util.CoreMap;

@Component
public class NLProcessorComponent {

	private StanfordCoreNLP pipeline;
	private static Logger log = LoggerFactory.getLogger(NLProcessorComponent.class);
	private static final String METRIC_NAME = "aia_nlp_component_message.increment";
	
	@Autowired
	private AIAMessageRepository aiaMsgRepo;
	
	@Autowired
	private AIASemanticGraphRepository aiaSemanticRepo;
	
	@Autowired
	private DatadogClientComponent dataDogClientComponent;
	
	public NLProcessorComponent() {
		super();
		this.pipeline = new StanfordCoreNLP("spanish");
		log.info("Init CoreNLP Ready");
	}
	
	private AIASemanticGraphNode toNode(IndexedWord word, String relation, String semanticTreeNode, AIASemanticGraphNode parent) {
		AIASemanticGraphNode node = new AIASemanticGraphNode();
		node.setRelationType(relation);
		node.setIndex(Long.valueOf(word.toCopyIndex()));
		node.setOriginalText(word.originalText());
		node.setSemanticNodeTree(semanticTreeNode);
		node.setTag(word.tag());
		node.setParent(parent);
		return node;
	}
	
	public AIASemanticGraph generateSemanticGraph(CoreDocument document, AIAMessage aiaMessage) {
		AIASemanticGraph graph = new AIASemanticGraph();
		graph.setSentence(aiaMessage.getBody().getCmd());
		graph.setAiaMessageId(aiaMessage.getId());
		String dotFormat = document.sentences().get(0).dependencyParse().toDotFormat();
		String aiaTreeString = document.sentences().get(0).dependencyParse().toString();
		String formattedTxt = document.sentences().get(0).dependencyParse().toFormattedString();
		//aiaTreeString = aiaTreeString.replaceAll(" ", "_");
		graph.setDotFormat(dotFormat);
		log.info(aiaTreeString);
		log.info(dotFormat);
		log.info(formattedTxt);
		log.info(document.sentences().get(0).dependencyParse().toPOSList());
		List<AIASemanticGraphNode> listNodes = new ArrayList<>();
		SemanticGraph semanticGraph = document.sentences().get(0).dependencyParse();
		List<SemanticGraphEdge> listGgraph = semanticGraph.edgeListSorted();
		IndexedWord semanticGraphRoot = semanticGraph.getFirstRoot();
		AIASemanticGraphNode rootNode, parentNode, currentNode;
		rootNode = this.toNode(semanticGraphRoot, "root", null, null);
		listNodes.add(rootNode);
		for (SemanticGraphEdge semanticGraphEdge : listGgraph) {
			//log.info(semanticGraphEdge.toString());
			IndexedWord word = semanticGraphEdge.getTarget();
			parentNode = this.toNode(semanticGraphEdge.getSource(), null, null, null);
			//log.info(word.originalText() + "/" + word.tag() + "-" + word.toCopyIndex() + " ("+ semanticGraphEdge.getRelation().getShortName() + ")");
			currentNode = this.toNode(word, semanticGraphEdge.getRelation().getShortName(), semanticGraphEdge.toString(), parentNode);
			parentNode = currentNode;
			listNodes.add(currentNode);
		}
		graph.setAiaMessageId(aiaMessage.getId());
		graph.setCreationDate(new Date());
		graph.setNodes(listNodes);
		graph.setFormattedText(formattedTxt);
		graph.setSemanticTree(aiaTreeString);
		return graph;
	}
	
	public void getCoreDocument(String txt) {
		if(txt.contains("{'head':")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd hh:mm:ss").create();
			AIAMessage aiaMessage = gson.fromJson(txt, AIAMessage.class);
			if (aiaMessage != null && aiaMessage.getBody() != null && aiaMessage.getBody().getIsAia() != null && !aiaMessage.getBody().getCmd().isEmpty()) {
				log.info("*********************************************");
				log.info(aiaMessage.getBody().getCmd());
				CoreDocument document = this.pipeline.processToCoreDocument(aiaMessage.getBody().getCmd());
				dataDogClientComponent.incrementCounter(METRIC_NAME);
				dataDogClientComponent.incrementCounter("aia_nlp_" + aiaMessage.getBody().getClassification() + ".increment");
				//TODO: move aiaMsgRepo.save a sppech reconigtion
				aiaMessage = aiaMsgRepo.save(aiaMessage);
				AIASemanticGraph aiaGraph = this.generateSemanticGraph(document, aiaMessage);
				aiaGraph =  aiaSemanticRepo.save(aiaGraph);
				log.info("aiaGraphId= " + aiaGraph.getId());
			}
		}
	}

}

package cl.yai.amandaia.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class AIASemanticGraph implements Serializable{

	private static final long serialVersionUID = -1336267020845874638L;

	@Id
	private String id;
	private String semanticTree;
	private String dotFormat;
	private String formattedText;
	private String sentence;	
	private String aiaMessageId;
	private List<AIASemanticGraphNode> nodes = new ArrayList<>();
	private Date creationDate;
	
	public String getFormattedText() {
		return formattedText;
	}
	public void setFormattedText(String formattedText) {
		this.formattedText = formattedText;
	}
	public String getDotFormat() {
		return dotFormat;
	}
	public void setDotFormat(String dotFormat) {
		this.dotFormat = dotFormat;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<AIASemanticGraphNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<AIASemanticGraphNode> nodes) {
		this.nodes = nodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSemanticTree() {
		return semanticTree;
	}
	public void setSemanticTree(String semanticTree) {
		this.semanticTree = semanticTree;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public String getAiaMessageId() {
		return aiaMessageId;
	}
	public void setAiaMessageId(String aiaMessageId) {
		this.aiaMessageId = aiaMessageId;
	}
	
	
	
}

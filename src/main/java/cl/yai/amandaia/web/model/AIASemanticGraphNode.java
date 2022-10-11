package cl.yai.amandaia.web.model;

public class AIASemanticGraphNode {

	private String originalText;
	private String tag;
	private Long index;
	private String relationType;
	private AIASemanticGraphNode parent;
	private String semanticNodeTree;
	public String getOriginalText() {
		return originalText;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public AIASemanticGraphNode getParent() {
		return parent;
	}
	public void setParent(AIASemanticGraphNode parent) {
		this.parent = parent;
	}
	public String getSemanticNodeTree() {
		return semanticNodeTree;
	}
	public void setSemanticNodeTree(String semanticNodeTree) {
		this.semanticNodeTree = semanticNodeTree;
	}
	
	
	
}

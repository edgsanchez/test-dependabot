package cl.yai.amandaia.web.model;

import java.io.Serializable;

public class AIAMessage implements Serializable{

	private static final long serialVersionUID = 3757142439224427786L;

	private AIAMessageHeader head;
	private AIAMessageBody body;
	
	public AIAMessageHeader getHead() {
		return head;
	}
	public void setHead(AIAMessageHeader head) {
		this.head = head;
	}
	public AIAMessageBody getBody() {
		return body;
	}
	public void setBody(AIAMessageBody body) {
		this.body = body;
	}
	
	
	
}

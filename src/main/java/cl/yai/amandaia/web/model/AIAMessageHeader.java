package cl.yai.amandaia.web.model;

import java.util.Date;

public class AIAMessageHeader {

	private String producer;
	private Date creationDate;

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}

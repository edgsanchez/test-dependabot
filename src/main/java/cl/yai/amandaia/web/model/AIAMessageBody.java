package cl.yai.amandaia.web.model;

public class AIAMessageBody {

	private String cmd;
	private String msg;
	private Boolean isAia;
	private String classification;
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getIsAia() {
		return isAia;
	}
	public void setIsAia(Boolean isAia) {
		this.isAia = isAia;
	}

	
	
	
}

package webservice.axis.bean;

import java.io.Serializable;

//�Զ�����
public class Bean implements Serializable {

	private String id;
	private String name;
	//ʹ�������

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

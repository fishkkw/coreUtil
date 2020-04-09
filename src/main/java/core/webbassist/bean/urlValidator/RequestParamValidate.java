package core.webbassist.bean.urlValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParamValidate {
	
	private String url;
	
	private String requied;
	
	private boolean isCheck = true;
	
	private List<Map> checkMap = new ArrayList<Map>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequied() {
		return requied;
	}

	public void setRequied(String requied) {
		this.requied = requied;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public List<Map> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(List<Map> checkMap) {
		this.checkMap = checkMap;
	} 
	
	
	
	

}

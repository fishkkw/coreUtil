package core.webbassist.bean;

import java.util.List;

public class UrlWhiteConfig {
	
	private List<String> urlWhite;
	
	private String errorHanderClassName;
	
	private String enable;

	public List<String> getUrlWhite() {
		return urlWhite;
	}

	public void setUrlWhite(List<String> urlWhite) {
		this.urlWhite = urlWhite;
	}

	public String getErrorHanderClassName() {
		return errorHanderClassName;
	}

	public void setErrorHanderClassName(String errorHanderClassName) {
		this.errorHanderClassName = errorHanderClassName;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	

}

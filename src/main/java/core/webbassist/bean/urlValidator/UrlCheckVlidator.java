package core.webbassist.bean.urlValidator;

import java.util.HashMap;
import java.util.Map;

public class UrlCheckVlidator {
	private RequestMethod method;
	
	private String errorMassage;
	
	private String datapatter;
	
	private String validatorHandler;
	
	private Map<String, RequestParamValidate> paramValidate = new HashMap<String, RequestParamValidate>();

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}

	public String getErrorMassage() {
		return errorMassage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	}

	public String getDatapatter() {
		return datapatter;
	}

	public void setDatapatter(String datapatter) {
		this.datapatter = datapatter;
	}

	public String getValidatorHandler() {
		return validatorHandler;
	}

	public void setValidatorHandler(String validatorHandler) {
		this.validatorHandler = validatorHandler;
	}

	public Map<String, RequestParamValidate> getParamValidate() {
		return paramValidate;
	}

	public void setParamValidate(Map<String, RequestParamValidate> paramValidate) {
		this.paramValidate = paramValidate;
	} 
	
	
}

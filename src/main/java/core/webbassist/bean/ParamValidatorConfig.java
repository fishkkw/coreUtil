package core.webbassist.bean;

import java.util.HashMap;
import java.util.Map;

import core.webbassist.bean.paramValidator.ValidatorConfig;

public class ParamValidatorConfig {
	
	private Map<String,ValidatorConfig> alias = new HashMap<>();

	public Map<String, ValidatorConfig> getAlias() {
		return alias;
	}

	public void setAlias(Map<String, ValidatorConfig> alias) {
		this.alias = alias;
	} 
	
}

package core.webbassist.bean.paramValidator;

import java.util.HashMap;
import java.util.Map;

public class ParamValidatorConfig {
	
	private Map<String,ValidatorConfig> alias = new HashMap<>();

	public Map<String, ValidatorConfig> getAlias() {
		return alias;
	}

	public void setAlias(Map<String, ValidatorConfig> alias) {
		this.alias = alias;
	} 
	
}

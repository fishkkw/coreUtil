package core.webbassist.bean.paramValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidatorConfig {

	private List<Map> validator = new ArrayList<>();

	private String errorMassage;

	public List<Map> getValidator() {
		return validator;
	}

	public void setValidator(List<Map> validator) {
		this.validator = validator;
	}

	public String getErrorMassage() {
		return errorMassage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	} 

}

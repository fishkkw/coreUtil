package core.webbassist.bean;

import java.util.HashMap;
import java.util.Map;

import core.webbassist.bean.paramValidator.ParamValidatorConfig;
import core.webbassist.bean.paramValidator.UrlWhiteConfig;
import core.webbassist.bean.urlValidator.RequestMethod;
import core.webbassist.bean.urlValidator.UrlCheckVlidator;

public class FileCatch {

	private UrlWhiteConfig config = new UrlWhiteConfig();

	private static final FileCatch CATCH = new FileCatch();

	private ParamValidatorConfig paramConfig = new ParamValidatorConfig();

	private Map<RequestMethod, UrlCheckVlidator> map = new HashMap<RequestMethod, UrlCheckVlidator>();

	public static FileCatch init() {
		return CATCH;
	}

	public Map<RequestMethod, UrlCheckVlidator> getMap() {
		return map;
	}

	public void setMap(Map<RequestMethod, UrlCheckVlidator> map) {
		this.map = map;
	}

	public UrlWhiteConfig getConfig() {
		return config;
	}

	public void setConfig(UrlWhiteConfig config) {
		this.config = config;
	}

	public ParamValidatorConfig getParamConfig() {
		return paramConfig;
	}

	public void setParamConfig(ParamValidatorConfig paramConfig) {
		this.paramConfig = paramConfig;
	}

}

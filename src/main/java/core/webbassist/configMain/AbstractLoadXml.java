package core.webbassist.configMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import core.webbassist.bean.FileCatch;
import core.webbassist.bean.paramValidator.ParamValidatorConfig;
import core.webbassist.bean.paramValidator.UrlWhiteConfig;
import core.webbassist.bean.paramValidator.ValidatorConfig;
import core.webbassist.bean.urlValidator.RequestMethod;
import core.webbassist.bean.urlValidator.RequestParamValidate;
import core.webbassist.bean.urlValidator.UrlCheckVlidator;

public abstract class AbstractLoadXml {

	private static final Logger logger = LoggerFactory.getLogger(AbstractLoadXml.class);
	
	private static Map<RequestMethod, UrlCheckVlidator> requestMethodmap = new HashMap<RequestMethod, UrlCheckVlidator>();

	protected String path;

	public abstract void LoadConfig() throws ServletException;

	public static AbstractLoadXml init(String resourceType) {
		if (".zip".equals(resourceType)) {
			logger.info("configType is zip..");
			return new ConfigLoadZip();
		}
		// 其他
		return new ConfigLoadZip();
	}

	protected void paresCommonXml(Document document) {
		Element rootElement = document.getRootElement();
		if (rootElement == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		}
		this.addUrlWhiteConfig(rootElement);
		this.addParamValidatorConfig(rootElement);
	}
	
	/**
	 * @param document
	 */
	/**
	 * @param document
	 */
	/**
	 * @param document
	 */
	protected void paresURLCheckXml(Document document) {
		Element rootElement = document.getRootElement();
		if (rootElement == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		} 
		Element validator = rootElement.element("param_validator");
		String url = validator.attributeValue("url");
		String method = validator.attributeValue("method");
		String errorMassage = validator.attributeValue("errorMassage");
		String datapatter = validator.attributeValue("datapatter");
		String validatorHandler = validator.attributeValue("validatorHandler");
		RequestMethod key = new RequestMethod(url, method);
		UrlCheckVlidator value = new UrlCheckVlidator();
		value.setErrorMassage(errorMassage);
		value.setDatapatter(datapatter);
		value.setValidatorHandler(validatorHandler);
		value.setMethod(key);
		Map<String, RequestParamValidate> paramValidate = value.getParamValidate();
		Iterator<Element> elementIterator = validator.elementIterator("parameter");

		while(elementIterator.hasNext()) {
			Element next = elementIterator.next();
			RequestParamValidate validate = new RequestParamValidate();
			validate.setUrl(url);
			String requied = next.attributeValue("requied");
			validate.setRequied(requied);
			String isCheck = next.attributeValue("isCheck");
			validate.setCheck(isCheck == "false");
			String name = next.attributeValue("name");
			List<Map> checkMap = validate.getCheckMap();
			Iterator<Element> validators = next.elementIterator("validator");
			while(validators.hasNext()) {
				Element next2 = validators.next(); 
				Iterator<Attribute> attributeIterator = next2.attributeIterator();
				Map<String, String> map = new HashMap<String, String>();
				//属性
				while (attributeIterator.hasNext()) {
					Attribute nextAttributeIterator = attributeIterator.next();
					map.put(nextAttributeIterator.getName(), nextAttributeIterator.getStringValue());
				}
				checkMap.add(map);
			}
			validate.setCheckMap(checkMap);
			paramValidate.put(name, validate);
		}
		value.setParamValidate(paramValidate);
		requestMethodmap.put(key, value);
	}
	
	public void setRequestMethodmap() {
		FileCatch.init().setMap(requestMethodmap);
		logger.info("requestMethodmap init()");
	}

	public void addParamValidatorConfig(Element rootElement) {
		Element element = rootElement.element("paramete-checkers");
		if (element == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		}
		//校验集合
		Iterator<Element> elementIterator = element.elementIterator("validators");
		if (elementIterator == null) {
			logger.warn("validators is null..");
			return;
		}
		ParamValidatorConfig paramConfig = new ParamValidatorConfig();
		Map<String, ValidatorConfig> alias = paramConfig.getAlias();
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			//校验名称
			Attribute aliasName = next.attribute("alias");
			String aliasString = aliasName.getStringValue();
			Attribute errorMessage = next.attribute("errorMessage");
			String errorString = errorMessage.getStringValue();
			ValidatorConfig config = new ValidatorConfig();
			config.setErrorMassage(errorString);
			//规则集合
			Iterator<Element> nextIterator = next.elementIterator("validate");
			List<Map> validator = config.getValidator();
			while (nextIterator.hasNext()) {
				Element nextAttr = nextIterator.next();
				Iterator<Attribute> attributeIterator = nextAttr.attributeIterator();
				Map<String, String> map = new HashMap<String, String>();
				//属性
				while (attributeIterator.hasNext()) {
					Attribute nextAttributeIterator = attributeIterator.next();
					map.put(nextAttributeIterator.getName(), nextAttributeIterator.getStringValue());
				}
				validator.add(map);
			}
			alias.put(aliasString, config);
		}
		paramConfig.setAlias(alias);
		FileCatch.init().setParamConfig(paramConfig);
	}

	private void addUrlWhiteConfig(Element rootElement) {
		UrlWhiteConfig config = new UrlWhiteConfig();
		Element element = rootElement.element("exclude-url");
		if (element == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		}
		Attribute enable = element.attribute("enable");
		config.setEnable(enable.getValue());
		Attribute errorHander = element.attribute("errorHander");
		config.setErrorHanderClassName(errorHander.getValue());
		Iterator<Element> elementIterator = element.elementIterator();
		if (elementIterator == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		}
		List<String> urlWhiteList = new ArrayList<String>();
		while (elementIterator.hasNext()) {
			String next = elementIterator.next().getTextTrim();
			urlWhiteList.add(next);
		}
		config.setUrlWhite(urlWhiteList);
		FileCatch.init().setConfig(config);
		logger.info("addUrlWhiteConfig end..");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

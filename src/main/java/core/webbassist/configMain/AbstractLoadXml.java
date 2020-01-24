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

import core.webbassist.bean.FileCatch;
import core.webbassist.bean.ParamValidatorConfig;
import core.webbassist.bean.UrlWhiteConfig;
import core.webbassist.bean.paramValidator.ValidatorConfig;

public abstract class AbstractLoadXml {

	private static final Logger logger = LoggerFactory.getLogger(AbstractLoadXml.class);

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

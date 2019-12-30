package core.webbassist.configMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.webbassist.bean.FileCatch;
import core.webbassist.bean.UrlWhiteConfig;

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
		this.addUrlWhiteConfig(rootElement);
	}

	private void addUrlWhiteConfig(Element rootElement) {
		if (rootElement == null) {
			logger.warn("urlWhiteConfig is null..");
			return;
		}
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

package core.webbassist;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SafetyReadXml extends SAXReader {

	@Override
	protected XMLReader createXMLReader() throws SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		try {
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		} catch (Exception e) {
		}

		try {
			return factory.newSAXParser().getXMLReader();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

}

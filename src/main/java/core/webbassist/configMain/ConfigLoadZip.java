package core.webbassist.configMain;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.webbassist.SafetyReadXml;

public class ConfigLoadZip extends AbstractLoadXml {

	private static final Logger logger = LoggerFactory.getLogger(ConfigLoadZip.class);

	@Override
	public void LoadConfig(String path) throws ServletException {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path);
		if (resourceAsStream == null) {
			logger.error("Configuration file is empty...");
			throw new ServletException();
		}
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(resourceAsStream));
		ZipEntry ze;
		try {
			while ((ze = zis.getNextEntry()) != null) {
				if (!ze.isDirectory()) {
					byte[] content = this.getFileContent(zis).getBytes();
					SafetyReadXml readXml = new SafetyReadXml();
					Document document = readXml.read(new InputStreamReader(new ByteArrayInputStream(content), "utf-8"));
					if (ze.getName().endsWith(".xml")) {
						System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");

					}
				}
			}
			zis.closeEntry();
			zis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	private String getFileContent(ZipInputStream zipper) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(zipper, "utf-8"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}

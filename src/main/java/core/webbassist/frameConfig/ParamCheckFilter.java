package core.webbassist.frameConfig;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import core.webbassist.config.VaildParamConfig;

public class ParamCheckFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(ParamCheckFilter.class);

	private VaildParamConfig config;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

	}

	public ParamCheckFilter(VaildParamConfig config) {
		this.config = config;
	}

	@Override
	protected void initFilterBean() throws ServletException {
		if (config.getPath() == null) {
			throw new ServletException("validata config path is null");
		}
		try {
			this.initConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initConfiguration() throws ServletException, IOException {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(config.getPath());
		if (resourceAsStream == null) {
			logger.error("Configuration file is empty...");
			throw new ServletException();
		}
		if (".zip".equals(this.config.getResourceType())) {
			ZipInputStream zis = new ZipInputStream(resourceAsStream);
			ZipEntry ze;
			while ((ze = zis.getNextEntry()) != null) {
				if (!ze.isDirectory()) {
					if (ze.getName().endsWith(".xml")) {
						System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");

					}
				}
			}
			zis.closeEntry();
			zis.close();
		}
	}

}

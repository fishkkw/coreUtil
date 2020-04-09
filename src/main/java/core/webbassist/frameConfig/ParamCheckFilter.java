package core.webbassist.frameConfig;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import core.webbassist.bean.paramValidator.VaildParamConfig;
import core.webbassist.configMain.AbstractLoadXml;

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
		String path = this.config.getPath();
		String resourceType = this.config.getResourceType();
		AbstractLoadXml abstractLoadXml = AbstractLoadXml.init(resourceType);
		abstractLoadXml.setPath(path);
		abstractLoadXml.LoadConfig();
	}

}

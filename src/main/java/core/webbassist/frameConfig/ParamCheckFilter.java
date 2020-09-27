package core.webbassist.frameConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.webbassist.bean.FileCatch;
import core.webbassist.bean.paramValidator.UrlWhiteConfig;
import core.webbassist.bean.urlValidator.RequestMethod;
import core.webbassist.bean.urlValidator.UrlCheckVlidator;
import core.webbassist.frameConfig.valid.ValiadUrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import core.webbassist.bean.paramValidator.VaildParamConfig;
import core.webbassist.configMain.AbstractLoadXml;

public class ParamCheckFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(ParamCheckFilter.class);

    private VaildParamConfig config;

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        FileCatch init = FileCatch.init();
        HttpServletResponse response = (HttpServletResponse) arg1;
        Map<RequestMethod, UrlCheckVlidator> map = init.getMap();
        UrlWhiteConfig config = init.getConfig();
        List<String> urlWhite = config.getUrlWhite();
        String requestURI = request.getRequestURI();
        String errorHanderClassName = config.getErrorHanderClassName();
        try {
            HashMap<String, String> errorMap = new HashMap<>();
            if (!urlWhite.contains(requestURI)) {
                String errorMsg = "%s not in wiiteUtl";
                errorMsg = String.format(Locale.ENGLISH, errorMsg, requestURI);
                errorMap.put("error", errorMsg);
            }
            Class<?> aClass = Class.forName(errorHanderClassName);
            ValiadUrlUtil valiadUrlUtil = (ValiadUrlUtil) aClass.newInstance();
            valiadUrlUtil.valiadUrl(errorMap, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ValiadUrlUtil.valiadUrl(init, httpServletRequest, response);
        logger.info("参数校验...");
        //response.sendError(403, "Forbidden");
	/*	FileCatch init = FileCatch.init();
		UrlWhiteConfig config = init.getConfig();
		init.getMap();*/
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

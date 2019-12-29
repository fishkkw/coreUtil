package core.webbassist.configMain;

import javax.servlet.ServletException;

public abstract class AbstractLoadXml {

	public abstract void LoadConfig(String path) throws ServletException;

	public static AbstractLoadXml init(String resourceType) {
		if (".zip".equals(resourceType)) {
			return new ConfigLoadZip();
		}
		// 其他
		return new ConfigLoadZip();
	}

}

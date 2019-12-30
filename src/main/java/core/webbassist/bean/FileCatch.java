package core.webbassist.bean;

public class FileCatch {

	private UrlWhiteConfig config = new UrlWhiteConfig();

	private static final FileCatch CATCH = new FileCatch();

	public static FileCatch init() {
		return CATCH;
	}

	public UrlWhiteConfig getConfig() {
		return config;
	}

	public void setConfig(UrlWhiteConfig config) {
		this.config = config;
	}

}

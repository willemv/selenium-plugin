package hudson.plugins.selenium.configuration.browser.webdriver;

import hudson.EnvVars;
import hudson.Extension;

import java.util.HashMap;
import java.util.Map;

import org.kohsuke.stapler.DataBoundConstructor;


public class OperaBrowser extends WebDriverBrowser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5094330146488965759L;

	transient final protected String PARAM_BINARY_PATH = "opera.binary";

	private String browser_binary;
	
	@DataBoundConstructor
	public OperaBrowser(int maxInstances, String version, String browser_binary) {
		super(maxInstances, version, "opera");
		this.browser_binary = browser_binary;
	}
	
	@Override
	public Map<String, String> getJVMArgs( EnvVars env ) {
		Map<String, String> args = new HashMap<String, String>();
		combine(args, PARAM_BINARY_PATH, env.expand(browser_binary));
		return args;
	}

		
    @Extension
    public static class DescriptorImpl extends WebDriverBrowserDescriptor {

    	@Override
		public String getDisplayName() {
			return "Opera";
		}

    }
}

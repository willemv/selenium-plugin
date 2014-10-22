package hudson.plugins.selenium.configuration.browser.webdriver;

import hudson.Extension;
import hudson.model.Computer;
import hudson.plugins.selenium.process.SeleniumRunOptions;

import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

public class FirefoxBrowser extends WebDriverBrowser {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1451746845341944745L;

    transient final protected String PARAM_BINARY_PATH = "firefox_binary";

    private String binary_path;

    @DataBoundConstructor
    public FirefoxBrowser(int maxInstances, String version, String binaryPath) {
        super(maxInstances, version, "firefox");
        binary_path = binaryPath;
    }

    @Exported
    public String getBinaryPath() {
        return binary_path;
    }

    @Override
    public List<String> initBrowserOptions(Computer c, SeleniumRunOptions options) {
        List<String> args = super.initBrowserOptions(c, options);
        combine(args, PARAM_BINARY_PATH, getNodeVars(c).expand(binary_path));
        return args;
    }

    @Extension
    public static class DescriptorImpl extends WebDriverBrowserDescriptor {

        public int getMaxInstances() {
            return 5;
        }

        @Override
        public String getDisplayName() {
            return "Firefox";
        }

    }
}

package hudson.plugins.selenium.configuration.browser.webdriver;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.Computer;
import hudson.plugins.selenium.configuration.browser.SeleniumBrowserServerUtils;
import hudson.plugins.selenium.process.SeleniumRunOptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

public class IEBrowser extends ServerRequiredWebDriverBrowser {

    /**
	 * 
	 */
    private static final long serialVersionUID = -241845413478474187L;

    transient private static final String PARAM_BINARY_PATH = "webdriver.ie.driver";

    @DataBoundConstructor
    public IEBrowser(int maxInstances, String version, String server_binary) {
        super(maxInstances, version, "internet explorer", server_binary);
    }

    @Override
    public Map<String, String> getJVMArgs(EnvVars env) {
        Map<String, String> args = new HashMap<String, String>();

        String server_binary = env.expand(getServer_binary());
        if (StringUtils.isBlank(server_binary)) {

        }

        combine(args, PARAM_BINARY_PATH, server_binary);
        return args;
    }

    @Override
    public void initOptions(Computer c, SeleniumRunOptions opt) {
        String server_path = SeleniumBrowserServerUtils.uploadIEDriverIfNecessary(c, getNodeVars(c).expand(getServer_binary()));
        if (server_path != null) {
            opt.getJVMArguments().put(PARAM_BINARY_PATH, server_path);
        }
        opt.addOptionIfSet("-browser", StringUtils.join(initBrowserOptions(c, opt), ","));
    }

    @Extension
    public static class DescriptorImpl extends WebDriverBrowserDescriptor {

        public int getMaxInstances() {
            return 1;
        }

        @Override
        public String getDisplayName() {
            return "Internet Explorer";
        }

    }
}

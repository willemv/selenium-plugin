/**
 * 
 */
package hudson.plugins.selenium.callables;

import hudson.remoting.Callable;

import java.util.Map;

/**
 * @author Richard Lavoie
 * 
 */
public class RemoveSeleniumServer implements Callable<Void, Exception> {

    /**
     * 
     */
    private static final long serialVersionUID = 6048096509551676769L;

    /**
     * Name of the configuration associated to the channel to stop.
     */
    private String name;

    public RemoveSeleniumServer(String config) {
        this.name = config;
    }

    /**
     * 
     */
    public Void call() throws Exception {
        Map<?, ?> configs = PropertyUtils.getProperty(SeleniumConstants.PROPERTY_STATUS);
        configs.remove(name);
        return null;
    }

}

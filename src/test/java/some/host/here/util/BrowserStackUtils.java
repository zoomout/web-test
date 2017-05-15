package some.host.here.util;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackUtils {

    private static final String BROWSER_STACK_USER_NAME = "";
    private static final String BROWSER_STACK_AUTH_KEY = "";
    private static final String BROWSER_STACK_REMOTE_URL_TEMPLATE = "https://%s:%s@hub.browserstack.com/wd/hub";

    public static URL getRemoteUrl() {
        URL url;
        String urlStr = String.format(BROWSER_STACK_REMOTE_URL_TEMPLATE, BROWSER_STACK_USER_NAME, BROWSER_STACK_AUTH_KEY);
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("Malformed URL: %s", urlStr), e);
        }
        return url;
    }
}


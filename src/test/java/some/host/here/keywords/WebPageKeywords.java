package some.host.here.keywords;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import some.host.here.pages.WebPage;

public class WebPageKeywords {
    private static final Logger LOG = LoggerFactory.getLogger(WebPageKeywords.class);
    private final WebPage webPage;

    public WebPageKeywords(final WebDriver webDriver) {
        webPage = new WebPage(webDriver);
    }

    public void openWebPage(final String url) {
        webPage.open(url);
        LOG.info("Opened web page");
    }

    public void searchFor(final String text) {
        webPage.typeText(text);
        webPage.clickSubmit();
    }

    public String getResultsStats() {
        return webPage.getResultStats();
    }

}

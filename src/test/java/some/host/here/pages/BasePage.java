package some.host.here.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static some.host.here.keywords.BaseTest.WEB_DRIVER_TIMEOUT_IN_SECONDS;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    BasePage(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WEB_DRIVER_TIMEOUT_IN_SECONDS);
    }

    public void open(final String url) {
        driver.get(url);
    }
}

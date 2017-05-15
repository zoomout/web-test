package some.host.here.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static final int WEB_DRIVER_TIMEOUT_IN_SECONDS = 15;
    public ThreadLocal<WebDriver> driverStore = new ThreadLocal<>();
    public ThreadLocal<WebPageKeywords> webPageKeywordsStore = new ThreadLocal<>();

    @BeforeMethod
    public void createWebDriver() {
        setWebDriverLocation();
        final FirefoxDriver driver = new FirefoxDriver();
        driverStore.set(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WEB_DRIVER_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        webPageKeywordsStore.set(new WebPageKeywords(driver));
    }

    private void setWebDriverLocation() {
        final String driverLocation = "drivers/geckodriver";
        URL resource = getClass().getClassLoader().getResource(driverLocation);
        if (resource == null) {
            throw new RuntimeException("File doesn't exist in location: " + driverLocation);
        }
        System.setProperty("webdriver.gecko.driver", new File(resource.getFile()).getAbsolutePath());
    }

    @AfterMethod
    public void quitWebDriver() {
        final WebDriver driver = driverStore.get();
        if (driver != null) {
            driver.quit();
        }
    }
}

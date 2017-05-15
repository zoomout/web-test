package some.host.here.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPage extends BasePage {

    @FindBy(name = "btnG")
    private WebElement submitBtn;

    @FindBy(id = "resultStats")
    private WebElement resultStatsTxt;

    @FindBy(className = "gsfi")
    private WebElement searchInput;

    public WebPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void typeText(final String text) {
        searchInput.sendKeys(text);
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public String getResultStats() {
        return resultStatsTxt.getText();
    }
}
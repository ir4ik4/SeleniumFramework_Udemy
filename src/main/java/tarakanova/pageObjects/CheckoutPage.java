package tarakanova.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tarakanova.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By listOfCountry = By.cssSelector(".list-group");

    @FindBy(css="[placeholder*='Country']")
 private WebElement country;
    @FindBy(css=".list-group button:nth-child(3)")
   private WebElement selectCountry;
    @FindBy(css=".btnn")
   private  WebElement submit;

    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(listOfCountry);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }

}

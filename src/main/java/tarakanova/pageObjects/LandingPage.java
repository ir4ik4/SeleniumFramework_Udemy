package tarakanova.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tarakanova.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement submitButton;

    @FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
    WebElement errorMessage;


    public ProductCatalogue loginApplication(String email, String password) {
   userEmail.sendKeys(email);
   userPassword.sendKeys(password);
   waitUntilElementToBeClickable(submitButton);
   submitButton.click();
   ProductCatalogue productCatalogue = new ProductCatalogue(driver);
   return productCatalogue;
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();


    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}

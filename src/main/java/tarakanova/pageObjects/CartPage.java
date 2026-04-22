package tarakanova.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tarakanova.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

@FindBy(css=".cartSection h3")
List<WebElement> productTitles;

@FindBy(xpath="//button[contains(text(), 'Checkout')]")
    WebElement checkoutButton;

public boolean verifyProductDisplayed(String productName) {
    boolean match = productTitles.stream().anyMatch(cartProduct -> cartProduct
            .getText().equalsIgnoreCase(productName));
    return match;
}
public CheckoutPage goToCheckoutPage() {
    checkoutButton.click();
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    return checkoutPage;
}

}

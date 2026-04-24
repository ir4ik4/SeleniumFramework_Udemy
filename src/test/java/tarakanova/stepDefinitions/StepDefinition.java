package tarakanova.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import tarakanova.TestComponents.BaseTest;
import tarakanova.pageObjects.*;

import java.io.IOException;

public class StepDefinition extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String email, String password)
            throws InterruptedException {

     productCatalogue = landingPage.loginApplication(email, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_Cart(String productName) throws InterruptedException {
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplayed(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String message) throws InterruptedException {
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(message));
        driver.close();
    }

}
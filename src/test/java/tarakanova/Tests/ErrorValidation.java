package tarakanova.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tarakanova.TestComponents.BaseTest;
import tarakanova.TestComponents.RetryTests;
import tarakanova.pageObjects.CartPage;
import tarakanova.pageObjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;


public class ErrorValidation extends BaseTest {

    @Test(groups= {"ErrorHandling"}, retryAnalyzer = RetryTests.class )
    public void loginErrorValidation() throws IOException {

        landingPage.loginApplication("irin@gmail.com", "Iamking@000");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws IOException {
       String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("parmi@gmail.com", "Iamking@000");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplayed("Zara Coat 333");
        Assert.assertFalse(match);
    }
    }

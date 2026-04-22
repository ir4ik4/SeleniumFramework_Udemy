package tarakanova.Tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tarakanova.TestComponents.BaseTest;
import tarakanova.pageObjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

@Test(dataProvider = "getOrderData", groups = {"Purchase"})
        public void submitOrderTest(HashMap<String, String> input) throws IOException{

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplayed(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void orderHistoryTest() throws IOException{
        ProductCatalogue productCatalogue = landingPage.loginApplication
                ("irina@gmail.com", "Iamking@000");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplayed(productName));
    }

    @DataProvider
    public Object[][] getOrderData() throws IOException {
    List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")
            + "/src/test/java/tarakanova/data/PurchaseOrder.json");
        return new Object[][]{ {data.get(0)}, {data.get(1)} };
    }
}
 /*   HashMap<String, String> map = new HashMap<>();
    map.put("email", "irina@gmail.com");
    map.put("password", "Iamking@000");
    map.put("productName", "ZARA COAT 3");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("email", "parmi@gmail.com");
        map2.put("password", "Iamking@000");
        map2.put("productName", "ADIDAS ORIGINAL"); */

package tarakanova.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tarakanova.pageObjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("irina@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.id("login")).click();

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = (WebElement) products.stream().filter(product -> product.findElement(By.cssSelector("b"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type ")).click();
        //button[contains(text(), 'Checkout')]
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> carProducts = driver.findElements(By.cssSelector(".cartSection h3"));
       boolean match = carProducts.stream().anyMatch(cartProduct -> cartProduct
               .getText().equalsIgnoreCase(productName));
       Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[contains(text(), 'Checkout')]")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")), "india")
                .build().perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list-group")));
        driver.findElement(By.cssSelector(".list-group button:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".btnn")).click();

        String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();











    }


}

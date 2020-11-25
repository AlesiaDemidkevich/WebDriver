import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

public class ElemaTest{
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
            public void driverInitiate(){
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--disable-notifications");
            option.addArguments("--disable-popup-blocking");
            option.addArguments("--window-size=1600,900");
            driver = new ChromeDriver(option);
            }

    @Test
    public void addItemsToBasketTest() throws InterruptedException {

        driver.get("https://elema.by/");
        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"main-nav\"]/div/form/div/input"));
        searchInput.click();
        searchInput.sendKeys("0С360-20-Р53");
        searchInput.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class=\"col-xs-12 col-sm-6 col-md-6\"]")));
        WebElement targetModel = driver.findElement(By.xpath("//div[@class=\"col-xs-12 col-sm-6 col-md-6\"]"));
        targetModel.click();
        WebElement targetSize = driver.findElement(By.xpath("//li[@title=\"44\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",targetSize);
        Thread.sleep(500);
        targetSize.click();
        WebElement targetHeight = driver.findElement(By.xpath("//li[@title=\"170\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",targetHeight);
        Thread.sleep(500);
        targetHeight.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href=\"javascript:void(0);\"]")));
        WebElement addToBasketButton = driver.findElement(By.xpath("//a[@href=\"javascript:void(0);\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",addToBasketButton);
        Thread.sleep(500);
        addToBasketButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href=\"/personal/cart/\"]")));
        WebElement goToBasketButton = driver.findElement(By.xpath("//a[@href=\"/personal/cart/\"]"));
        goToBasketButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@class=\"basket-items-list-table\"]")));
        List<WebElement> itemsInBasket = driver.findElements(By.xpath("//table[@class=\"basket-items-list-table\"]"));
        Assert.assertTrue(itemsInBasket.size()>0);

    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
    }
}
package Test_Cases;

import Web_Driver.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testGoogleApps {
    private WebDriver driver;
    private int countTest = 0;
    private int totalTest = 0;

    @BeforeAll
    public void setUpWebDriver(){
        this.driver = WebDriverSingleton.getInstance();
        System.out.println("Initial WebDriver successful");
    }

    @BeforeEach
    public void increaseTotalTest() {
        this.totalTest++;
    }

    @Test
    @Order(1)
    @DisplayName("Navigate to google site")
    public void openGoogleSite(){
        driver.get("https://www.google.com");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(2)
    @DisplayName("Verify URL")
    public void verifyURL(){
        String exceptedUrl = "https://www.google.com/";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedUrl, actualUrl, "Incorrect URL");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(3)
    @DisplayName("Click on Google apps")
    public void clickAppsGoogle()  {
        driver.findElement(By.className("gb_Se")).click();
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(4)
    @DisplayName("Click on Gmail")
    public void clickGoogleGmail(){
        driver.findElement(By.xpath("//a[contains(text(),'Gmail')]")).click();
        String exceptedValue = "https://www.google.com/intl/iw/gmail/about/";
        String actualValue = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedValue, actualValue, "Error: Gmail test | incorrect current URL");
        System.out.println("Test number: "+ ++countTest +" passed");
        driver.navigate().back();
    }


    @AfterEach
    public void setTimeOutAfterTest() throws InterruptedException {
        Thread.sleep(2000);
    }

    @AfterAll
    public void finishTest(){
        driver.close();
        driver.quit();
        System.out.println("Quit WebDriver successful\nTests passed :"+countTest+"/"+totalTest);
    }
}

package Test_Cases;

import Web_Driver.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testChangeLanguage {
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
    @DisplayName("Click on 'العربية' to change language from english to arabic language")
    public void changeLanguageToArabic()  {
        driver.findElement(By.xpath("//a[contains(text(),'العربية')]")).click();
        String exceptedUrl = "صور";
        String actualUrl = driver.findElement(By.xpath("//a[contains(text(),'صور')]")).getText();
        Assertions.assertEquals(exceptedUrl, actualUrl, "ERROR: Change language to arabic was field.");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(4)
    @DisplayName("Click on 'עברית' to change language from english to he-brow language")
    public void changeLanguageToHebrow()  {
        driver.findElement(By.xpath("//a[contains(text(),'עברית')]")).click();
        String exceptedUrl = "חיפוש תמונות";
        String actualUrl = driver.findElement(By.xpath("//a[contains(text(),'חיפוש תמונות')]")).getText();
        Assertions.assertEquals(exceptedUrl, actualUrl, "ERROR: Change language to he-brow was field.");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(5)
    @DisplayName("Click on 'עברית' to change language from english to he-brow language")
    public void changeLanguageToEnglish()  {
        driver.findElement(By.xpath("//a[contains(text(),'English')]")).click();
        String exceptedUrl = "Images";
        String actualUrl = driver.findElement(By.xpath("//a[contains(text(),'Images')]")).getText();
        Assertions.assertEquals(exceptedUrl, actualUrl, "ERROR: Change language to English was field.");
        System.out.println("Test number: "+ ++countTest +" passed");
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

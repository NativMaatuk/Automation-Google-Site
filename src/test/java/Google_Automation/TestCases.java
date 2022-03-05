package Google_Automation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Web_Driver.WebDriverSingleton;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestCases {
    private WebDriver driver;
    private int countTest = 0;
    private int totalTest = 0;

    @BeforeAll
    public void setUpWebDriver(){
        this.driver = WebDriverSingleton.getInstance();
        System.out.println("Initial WebDriver successful");
    }

    @BeforeEach
    public void setTimeOut() {
       this.totalTest++;
    }

    @Test
    @Order(1)
    @DisplayName("Navigate to google site")
    public void openGoogleSite(){
        driver.get("https://www.google.co.il");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(2)
    @DisplayName("Verify URL")
    public void verifyURL(){
        String exceptedUrl = "https://www.google.co.il/";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedUrl, actualUrl, "Incorrect URL");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(3)
    @DisplayName("Change language to english")
    public void changeLanguage() throws Exception {
        WebElement lan = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/a[2]"));
        if(lan.getText().equals("English")) {
            //note: click on <a> tag that contain 'English'.
            lan.click();
            driver.navigate().to("https://www.google.co.il/");
            WebElement images = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div[1]/div/div[2]/a"));
            String exceptedValue = "Images";
            String actualValue = images.getText();
            Assertions.assertEquals(exceptedValue, actualValue, "Error: Change Languages was field!");
            System.out.println("Test number: "+ ++countTest +" passed");
        }
        else{
            throw new Exception("Error: Cannot find <a> tag element  - contains 'English' ");
        }
    }

    @Test
    @Order(4)
    @DisplayName("Click on Google apps")
    public void clickAppsGoogle()  {
        driver.findElement(By.className("gb_Se")).click();
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(5)
    @DisplayName("Click on Gmail")
    public void clickGoogleMaps(){
        driver.findElement(By.xpath("//*[@id=\"gb\"]/div/div[1]/div/div[1]/a")).click();
        String exceptedValue = "https://www.google.com/intl/iw/gmail/about/";
        String actualValue = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedValue, actualValue, "Error: Gmail test | incorrect current URL");
        System.out.println("Test number: "+ ++countTest +" passed");
        driver.navigate().back();
    }

    @Test
    @Order(6)
    @DisplayName("insert string 'Fibernet' to search input")
    public void insertStringToSearchInput() {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("Fibernet");
        String exceptedValue = "Fibernet";
        String actualValue = searchInput.getAttribute("value");
        Assertions.assertEquals(exceptedValue, actualValue, "Error: insert string 'Fibernet' to search input was field.");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @AfterEach
    public void closeDriver() throws InterruptedException {
        Thread.sleep(100);
    }

    @AfterAll
    public void finishTest(){
        driver.close();
        driver.quit();
        System.out.println("Quit WebDriver successful\nTests passed :"+countTest+"/"+totalTest);
    }
}

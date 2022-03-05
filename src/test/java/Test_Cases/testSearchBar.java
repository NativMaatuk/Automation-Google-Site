package Test_Cases;

import Web_Driver.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testSearchBar {
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
    @DisplayName("Insert string 'Fibernet' to search input")
    public void insertStringToSearchInput() {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("Fibernet");
        String exceptedValue = "Fibernet";
        String actualValue = searchInput.getAttribute("value");
        Assertions.assertEquals(exceptedValue, actualValue, "Error: insert string 'Fibernet' to search input was field.");
        System.out.println("Test number: "+ ++countTest +" passed");
    }

    @Test
    @Order(4)
    @DisplayName("Click enter to search 'Fibernet")
    public void clickEnterToSearch() {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys(Keys.ENTER);
        String exceptedValue = "https://www.google.com/search?q=Fibernet&";
        String actualValue = driver.getCurrentUrl().substring(0,41);
        driver.navigate().back();
        Assertions.assertEquals(exceptedValue, actualValue, "Error: click enter to search 'Fibernet' was field.");
        System.out.println("Test number: " + ++countTest + " passed");
    }

    @Test
    @Order(5)
    @DisplayName("Click enter to search empty string")
    public void SearchEmptyString() {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.clear();
        searchInput.sendKeys(Keys.ENTER);
        String exceptedValue = "https://www.google.com/";
        String actualValue = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedValue, actualValue, "Error: should be stay in same Url and do nothing.");
        System.out.println("Test number: " + ++countTest + " passed");
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

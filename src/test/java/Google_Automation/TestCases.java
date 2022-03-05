package Google_Automation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Web_Driver.WebDriverSingleton;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestCases {
    private WebDriver driver;
    private int countTest = 1;

    @BeforeAll
    public void setUpWebDriver(){
        this.driver = WebDriverSingleton.getInstance();
        System.out.println("Initial WebDriver successful");
        driver.get("https://formy-project.herokuapp.com/form");
    }

    @BeforeEach
    public void setTimeOut() throws InterruptedException {
        Thread.sleep(600);
    }

    @Test
    @Order(1)
    @DisplayName("Check URL")
    public void checkURL(){
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        String exceptedUrl = "https://formy-project.herokuapp.com/form";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedUrl, actualUrl, "Incorrect URL");
    }

    @Test
    @Order(2)
    @DisplayName("Open form page")
    public void open_form_page(){
        String exceptedUrl = "https://formy-project.herokuapp.com/form";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(exceptedUrl, actualUrl, "Incorrect URL");
    }

    @Test
    @Order(3)
    @DisplayName("Fill first name input")
    public void fill_first_name_input(){
        driver.findElement(By.id("first-name")).clear();
        driver.findElement(By.id("first-name")).sendKeys("Nativ");
    }

    @Test
    @Order(4)
    @DisplayName("Fill last name input")
    public void fill_last_name_input(){
        driver.findElement(By.id("last-name")).clear();
        driver.findElement(By.id("last-name")).sendKeys("Maatuk");
    }

    @Test
    @Order(5)
    @DisplayName("Fill job title input")
    public void fill_job_title_input(){
        driver.findElement(By.id("job-title")).clear();
        driver.findElement(By.id("job-title")).sendKeys("QA");
    }

    /*!
    @Test
    @RepeatedTest(value = 5)
    public void fiveRefresh(){
        driver.navigate().refresh();
    }
*/
    @AfterEach
    public void closeDriver(){
        System.out.println("Test number: "+countTest++ +" passed");
    }

    @AfterAll
    public void finishTest(){
        driver.close();
        driver.quit();
        System.out.println("Quit WebDriver successful\nTests passed :"+--countTest);
    }
}

package com.energygrid.user_service.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRegisterSeleniumTest {
    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldGoToRegister() {
        driver.navigate().to("http://35.197.228.250/");

        driver.findElement(By.xpath("/html/body/app-root/app-login/div/p/a")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://35.197.228.250/register");

    }
}

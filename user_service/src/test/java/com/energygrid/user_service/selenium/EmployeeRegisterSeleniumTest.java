package com.energygrid.user_service.selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRegisterSeleniumTest {
    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        driver = new FirefoxDriver(firefoxOptions);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldEnterCredentials() throws InterruptedException {
        driver.navigate().to("http://34.89.108.169/");
        driver.findElement(By.xpath("//*[@id=\"clientNr\"]")).sendKeys("test@test.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("test2");

        Set<String> windowHandles = driver.getWindowHandles();
        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/form/button")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(10000);
        Set<String> updatedWindowHandles = driver.getWindowHandles();
        updatedWindowHandles.removeAll(windowHandles);
        for (String window: updatedWindowHandles) {
            driver.switchTo().window(window);
        }

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://34.89.108.169/login");

    }
}
package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();

    }

    public void login(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
    }

    @Test
    public void login1(){
        login();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Web Orders");

    }
    @Test
    public void negativeLoginTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2"+ Keys.ENTER);
        String errMes = driver.findElement(By.id("ctl00_MainContent_status")).getText();

        Assert.assertEquals(errMes, "Invalid Login or Password.");

    }

    @Test
    public void logOutTest(){
        login();
        driver.findElement(By.id("ctl00_logout")).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Web Orders Login");

    }


    @AfterMethod
    public void cleanUp(){
        driver.close();
    }
}

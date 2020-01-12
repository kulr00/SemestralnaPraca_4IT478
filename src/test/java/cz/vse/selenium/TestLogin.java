package cz.vse.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLogin {


    private String PREFIX = "https://digitalnizena.cz/rukovoditel/";

    private ChromeDriver driver;



    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //ChromeDriverService service = new ChromeDriverService();
        /*ChromeOptions cho = new ChromeOptions();
        cho.addArguments("headless");
        driver = new ChromeDriver(cho);
        driver.manage().window().maximize();*/

        driver = new ChromeDriver();
        ChromeOptions cho = new ChromeOptions();
        cho.addArguments("headless");
        driver = new ChromeDriver(cho);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        //driver.close();
    }

    @Test

    public void validLogin(){

        //GIVEN
        driver.get(PREFIX);

        //WHEN
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("vse456ru");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();

        //THEN
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));

    }

    @Test
    public void invalidLogin(){
        //GIVEN
        driver.get(PREFIX);

        //WHEN
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("invalidPwd");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();

        //THEN
        //Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));
        WebElement alert = driver.findElement(By.cssSelector(".alert"));
        WebElement logo = driver.findElement(By.cssSelector(".alert"));
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel"));
        Assert.assertTrue(alert!=null);
        Assert.assertTrue(logo!=null);

    }
    @Test
    public void logOff(){

        //GIVEN
        driver.get(PREFIX);

        //Login
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("vse456ru");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));

        //WHEN
        WebElement menu = driver.findElement(By.cssSelector(".fa-angle-down"));
        menu.click();

        //Cakanie nebolo potrebné
        /*WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logoff")));*/
        WebElement logOffButton = driver.findElement(By.linkText("Logoff"));
        logOffButton.click();

        //THEN
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel"));
        WebElement h3 = driver.findElement(By.className("form-title"));
        Assert.assertTrue(h3.getText().equals("Login"));
    }

}



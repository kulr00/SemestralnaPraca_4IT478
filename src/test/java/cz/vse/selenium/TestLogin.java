package cz.vse.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

    public void invalidLopgin(){
        
    }


}



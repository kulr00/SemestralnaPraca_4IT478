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

public class ProjectCreating {

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

    public void projectNotCreated(){
        //GIVEN

        driver.get(PREFIX);
        WebDriverWait wait =new WebDriverWait(driver, 1);
        //Login

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("vse456ru");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));

        //WHEN

        WebElement menu = driver.findElement(By.cssSelector(".navbar-toggle"));
        menu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        WebElement project = driver.findElement(By.linkText("Projects"));
        project.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));
        WebElement createButton = driver.findElement(By.cssSelector(".btn-primary"));
        createButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary-modal-action")));
        WebElement saveButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        saveButton.click();

        //THEN
        WebElement error = driver.findElement(By.id("fields_158-error"));
        Assert.assertTrue(error!=null);



    }

}

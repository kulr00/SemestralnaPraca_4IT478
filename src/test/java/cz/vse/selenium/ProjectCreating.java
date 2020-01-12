package cz.vse.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectCreating {

    private String PREFIX = "https://digitalnizena.cz/rukovoditel/";
    private ChromeDriver driver;



    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //ChromeDriverService service = new ChromeDriverService();
        driver = new ChromeDriver();
        /*ChromeOptions cho = new ChromeOptions();
        cho.addArguments("headless");
        driver = new ChromeDriver(cho);*/
        //driver.manage().window().maximize();

        /*driver = new ChromeDriver();
        ChromeOptions cho = new ChromeOptions();
        cho.addArguments("headless");
        driver = new ChromeDriver(cho);
        driver.manage().window().maximize();*/


    }

    @After
    public void tearDown() {
        //driver.close();
    }

    @Test

    public void projectNotCreated(){

        //GIVEN
        driver.get(PREFIX);
        WebDriverWait wait =new WebDriverWait(driver, 2);

        //Login
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("vse456ru");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));

        //WHEN
        WebElement menu = driver.findElement(By.className("fa-reorder"));
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
    @Test

    public void projectCreated(){

        //GIVEN
        driver.get(PREFIX);
        WebDriverWait wait =new WebDriverWait(driver, 3);

        //Login
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rukovoditel");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("vse456ru");
        WebElement button =driver.findElement(By.cssSelector(".btn"));
        button.click();
        Assert.assertTrue(driver.getTitle().startsWith("Rukovoditel | Dashboard"));

        //WHEN
        WebElement menu = driver.findElement(By.className("fa-reorder"));
        menu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        WebElement project = driver.findElement(By.linkText("Projects"));
        project.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-primary")));
        WebElement createButton = driver.findElement(By.cssSelector(".btn-primary"));
        createButton.click();

        //Creating project
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_157")));
        Select select = new Select(driver.findElement(By.id("fields_157")));
        select.selectByIndex(0);
        Select priority = new Select(driver.findElement(By.id("fields_156")));
        select.selectByIndex(1);
        WebElement date = driver.findElement(By.cssSelector(".date-set"));
        date.click();
        WebElement currentdate = driver.findElement(By.cssSelector("tr > .active"));
        currentdate.click();
        WebElement name = driver.findElement(By.id("fields_158"));
        name.sendKeys("Test_kulr00");
        WebElement saveButton = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        saveButton.click();

        //Find created Project
        WebElement menu2 = driver.findElement(By.className("fa-reorder"));
        menu2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        WebElement project2 = driver.findElement(By.linkText("Projects"));
        project2.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("entity_items_listing66_21_search_keywords")));
        WebElement search = driver.findElement(By.id("entity_items_listing66_21_search_keywords"));
        search.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        search.sendKeys("Test_kulr00");
        WebElement searchButton = driver.findElement(By.cssSelector(".fa-search"));
        searchButton.click();
        WebElement infoProject = driver.findElement(By.cssSelector(".fa-info"));
        infoProject.click();

        //THEN
        //Confirm that my project exists
        WebElement projectName = driver.findElement(By.cssSelector(".caption"));
        Assert.assertTrue(projectName.getText().equals("Test_kulr00"));

        //Deleting
        WebElement delete = driver.findElement(By.cssSelector(".btn-default:nth-child(1)"));
        delete.click();
        WebElement deleteProject = driver.findElement(By.linkText("Delete"));
        deleteProject.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn:nth-child(3)")));
        WebElement confirmation = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
        confirmation.click();
    }
}

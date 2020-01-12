package cz.vse.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestTasks {

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
    public void taskCretaed(){

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-info")));
        WebElement infoProject = driver.findElement(By.cssSelector(".fa-info"));
        infoProject.click();

        //WHEN
        //Creating task
        WebElement tasks = driver.findElement(By.linkText("Tasks"));
        tasks.click();
        WebElement newTask = driver.findElement(By.cssSelector(".btn-primary"));
        newTask.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_167")));
        Select selectType = new Select(driver.findElement(By.id("fields_167")));
        selectType.selectByIndex(1);

        Select selectStatus = new Select(driver.findElement(By.id("fields_169")));
        selectStatus.selectByIndex(0);

        WebElement nameTask = driver.findElement(By.id("fields_168"));
        nameTask.sendKeys("Task_kulr00");

        Select selectPriority = new Select(driver.findElement(By.id("fields_170")));
        selectPriority.selectByIndex(2);

        WebElement selectPriority2 = driver.findElement(By.id("fields_170"));
        selectPriority2.sendKeys(Keys.TAB+"Popis");

        WebElement saveTask = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
        saveTask.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-info")));
        WebElement infoTask = driver.findElement(By.cssSelector(".fa-info"));
        infoTask.click();

        //THEN
        WebElement type = driver.findElement(By.cssSelector(".form-group-167 div"));
        Assert.assertTrue(type.getText().equals("Task"));

        WebElement description = driver.findElement(By.cssSelector(".content_box_content"));
        Assert.assertTrue(description.getText().equals("Popis"));

        WebElement nameT = driver.findElement(By.cssSelector(".caption"));
        Assert.assertTrue(nameT.getText().equals("Task_kulr00"));

        WebElement priorityT= driver.findElement(By.cssSelector(".form-group-170 div"));
        Assert.assertTrue(priorityT.getText().equals("Medium"));

        WebElement statusT = driver.findElement(By.cssSelector(".form-group-169 div"));
        Assert.assertTrue(statusT.getText().equals("New"));

        //Deleting
        WebElement delete = driver.findElement(By.cssSelector(".btn-default:nth-child(1)"));
        delete.click();
        WebElement deleteProject = driver.findElement(By.linkText("Delete"));
        deleteProject.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn:nth-child(3)")));
        WebElement confirmation = driver.findElement(By.cssSelector(".btn:nth-child(3)"));
        confirmation.click();
    }
    @Test
    public void sevenTasks(){

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-info")));
        WebElement infoProject = driver.findElement(By.cssSelector(".fa-info"));
        infoProject.click();

        //WHEN
        for(int i = 0;i<7;i++) {


            WebElement tasks = driver.findElement(By.linkText("Tasks"));
            tasks.click();
            WebElement newTask = driver.findElement(By.cssSelector(".btn-primary"));
            newTask.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fields_167")));
            Select selectType = new Select(driver.findElement(By.id("fields_167")));
            selectType.selectByIndex(1);


            Select selectStatus = new Select(driver.findElement(By.id("fields_169")));
            selectStatus.selectByIndex(i);


            WebElement nameTask = driver.findElement(By.id("fields_168"));
            nameTask.sendKeys("Task_kulr00");

            Select selectPriority = new Select(driver.findElement(By.id("fields_170")));
            selectPriority.selectByIndex(2);

            WebElement selectPriority2 = driver.findElement(By.id("fields_170"));
            selectPriority2.sendKeys(Keys.TAB + "Popis");


            WebElement saveTask = driver.findElement(By.cssSelector(".btn-primary-modal-action"));
            saveTask.click();
        }

        // THEN
        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr")));
        List<WebElement> elements = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        Assert.assertTrue(elements.size() == 4);

        // Change filter
        driver.findElement(By.className("filters-preview-condition-include")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='chosen-choices'] a")));
        List<WebElement> filters = driver.findElements(By.cssSelector("[class='chosen-choices'] a"));
        filters.get(1).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();

        // 3 tasks
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr")));
        elements = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        Assert.assertTrue(elements.size() == 3);

        // Clear filters
        driver.findElement(By.className("filters-preview-condition-include")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='chosen-choices'] a")));
        filters = driver.findElements(By.cssSelector("[class='chosen-choices'] a"));
        filters.get(1).click();
        filters.get(0).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();

        // 7 tasks
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr")));
        elements = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        Assert.assertTrue(elements.size() == 8);

        // Clear all created tasks
        driver.findElement(By.id("select_all_items")).click();
        //doubleclick
        driver.findElement(By.cssSelector("[class='btn btn-default dropdown-toggle']")).click();
        driver.findElement(By.cssSelector("[class='btn btn-default dropdown-toggle']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete")));
        driver.findElement(By.linkText("Delete")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary-modal-action")));
        driver.findElement(By.className("btn-primary-modal-action")).click();

        driver.close();
    }
}

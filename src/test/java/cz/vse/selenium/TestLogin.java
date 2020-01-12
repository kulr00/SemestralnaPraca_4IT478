package cz.vse.selenium;

import org.junit.After;
import org.junit.Before;
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
    }
}



package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.Duration;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setUp(){
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
        logger.info("****** Test Started ********");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        logger.info("****** Test Completed ********");
        driver.close();
    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomInteger(){
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphanumeric(){
        String generatedString = RandomStringUtils.randomAlphabetic(4);
        String generatedNumber= RandomStringUtils.randomNumeric(4);
        return generatedNumber + generatedString;
    }
}

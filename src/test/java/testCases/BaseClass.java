package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"operatingSystem", "browser"})
    public void setUp(String operatingSystem, String browser) throws IOException {

        //Loading Config.properties file
        FileReader fileReader = new FileReader("./src//test//resources//config.properties");
        properties = new Properties();
        properties.load(fileReader);;
        logger = LogManager.getLogger(this.getClass());

        //Remote Execution
        if(properties.getProperty("executionEnvironment").equalsIgnoreCase("remote")){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            //Setting up platform (WIN11, MAC)
            if(operatingSystem.equalsIgnoreCase("windows")){
                desiredCapabilities.setPlatform(Platform.WIN11);
            }else if(operatingSystem.equalsIgnoreCase("mac")){
                desiredCapabilities.setPlatform(Platform.MAC);
            }else{
                logger.error("unsupported platform");
                return;
            }

            //Setting up browsers
            String browserName;
            switch (browser.toLowerCase()){
                case "chrome" :
                    browserName = "chrome";
                    break;
                case "edge" :
                    browserName = "MicrosoftEdge";
                    break;
                case "firefox" :
                    browserName = "firefox";
                    break;
                default:
                    logger.error("unsupported browser type: " + browser);
                    return;
            }
            desiredCapabilities.setBrowserName(browserName);

            // Initializing RemoteWebDriver
            String hubUrl = properties.getProperty("hubURL");
            if (hubUrl == null || hubUrl.isEmpty()) {
                logger.error("Hub URL is not specified");
                return;
            }
            driver = new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
        }
        //Local Execution
        if(properties.getProperty("executionEnvironment").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()){
                case "chrome" :
                    driver = new ChromeDriver();
                    break;
                case "edge" :
                    driver = new EdgeDriver();
                    break;
                case "firefox" :
                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
                    logger.info("Unsupported browser parameter, hence running tests on chrome");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL"));
        logger.info("****** Test Started ********");
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown(){
        logger.info("****** Test Completed ********");
        driver.close();
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomInteger(){
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphanumeric(){
        return "@" +RandomStringUtils.randomAlphabetic(4) + RandomStringUtils.randomNumeric(4);
    }

    public void clearCookiesAndReloadPage(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    public String captureScreen(String screenshotName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}

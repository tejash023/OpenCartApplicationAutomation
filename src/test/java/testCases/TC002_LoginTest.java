package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{

    @Test
    public void verifyLoginTest(){
        logger.info("***** Started TC02 Account Login Test ********");

        try{
            //HomePage
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked My Account");
            homePage.clickLogin();
            logger.info("Clicked Login link");

            //Login Page
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLoginEmail(properties.getProperty("email"));
            logger.info("Login email entered");
            loginPage.setLoginPassword(properties.getProperty("password"));
            logger.info("Login password entered");
            loginPage.clickLogin();
            logger.info("Clicked Login CTA");

            //MyAccount
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();
            Assert.assertEquals(targetPage, true, "Login failed");
        }catch (Exception e){
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail();
        }

        logger.info("********** Finished TC02 Account Registration Test **********");
    }
}

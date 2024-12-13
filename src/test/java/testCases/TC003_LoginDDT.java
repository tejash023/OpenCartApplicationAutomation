package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
    public void verifyLoginDDT(String email, String password, String expectedResult){
        logger.info("***** Started TC03 Account Login Test using DDT ********");
        try{
            //Homepage
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            //Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLoginEmail(email);
            loginPage.setLoginPassword(password);
            loginPage.clickLogin();

            //My Account
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExists();

            //Data VALID Case
            if(expectedResult.equalsIgnoreCase("Valid")){
                //Login Success - user navigates to My Account Page - test passed
                if(targetPage){
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);

                }
                //Login Failed - user remains on the same page - test failed
                else{
                    clearCookiesAndReloadPage();
                    Assert.assertTrue(false);

                }
            }

            //Data INVALID Case
            if(expectedResult.equalsIgnoreCase("Invalid")){

                //Login success - user navigates to My Account Page - test failed
                if(targetPage){
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);

                }
                //Login failed - test passed
                else{
                    clearCookiesAndReloadPage();
                    Assert.assertTrue(true);

                }
            }
        } catch (Exception e) {
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail();
        }
        logger.info("********** Finished TC03 Account Login Test using DDT **********");

    }
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void accountRegistrationTest(){
        logger.info("********** Started TC01 Account Registration Test **********");

        try{
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on My Account Link");
            homePage.clickRegister();
            logger.info("Clicked on Register Link");

            AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

            accountRegistrationPage.setFirstName(randomString());
            logger.info("First Name added");
            accountRegistrationPage.setLastName(randomString());
            logger.info("Last Name added");
            accountRegistrationPage.setEmail(randomString() + "@xyz.com");
            logger.info("Email added");
            accountRegistrationPage.setTelephone(randomInteger());
            logger.info("Telephone added");

            String password = randomAlphanumeric();
            accountRegistrationPage.setPassword(password);
            logger.info("Password added");
            accountRegistrationPage.setConfirmPassword(password);
            logger.info("Conf Password added");
            accountRegistrationPage.setPrivacyPolicy();
            logger.info("Privacy Policy Set");
            accountRegistrationPage.clickContinue();
            logger.info("Clicked on Continue Button");

            logger.info("Validating error message");
            String confirmationMessage = accountRegistrationPage.getConfirmationMsg();
            Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!");
        }catch (Exception e){
            logger.error("Test Failed");
            logger.debug("Debug Logs");
            Assert.fail();
        }
        logger.info("********** Finished TC01 Account Registration Test **********");

    }

}

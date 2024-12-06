package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    public void accountRegistrationTest(){
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clickRegister();

        AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

        accountRegistrationPage.setFirstName(randomString());
        accountRegistrationPage.setLastName(randomString());
        accountRegistrationPage.setEmail(randomString() + "@xyz.com");
        accountRegistrationPage.setTelephone(randomInteger());

        String password = "@" + randomAlphanumeric();
        accountRegistrationPage.setPassword(password);
        accountRegistrationPage.setConfirmPassword(password);
        accountRegistrationPage.setPrivacyPolicy();
        accountRegistrationPage.clickContinue();

        String confirmationMessage = accountRegistrationPage.getConfirmationMsg();
        Assert.assertEquals(confirmationMessage, "Your Account Has Been Created!");
    }
    
}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@name='email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@name='telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@name='confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkBoxPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String phone){
        txtTelephone.sendKeys(phone);
    }

    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void setConfirmPassword(String password){
        txtConfirmPassword.sendKeys(password);
    }

    public void setPrivacyPolicy(){
        checkBoxPolicy.click();
    }

    public void clickContinue(){
        btnContinue.click();
    }

    public String getConfirmationMsg(){
        try{
            return (msgConfirmation.getText());
        } catch (Exception e){
            return (e.getMessage());
        }
    }

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtLoginEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtLoginPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton;

    public void setLoginEmail(String email){
        txtLoginEmail.sendKeys(email);
    }

    public void setLoginPassword(String password){
        txtLoginPassword.sendKeys(password);
    }

    public void clickLogin(){
        loginButton.click();
    }
}

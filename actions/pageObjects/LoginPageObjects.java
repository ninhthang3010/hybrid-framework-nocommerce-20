package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObjects extends BasePage {

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void enterEmailTextbox(String emailAddress) {
        waitForElementVisible(driver,LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX,emailAddress);

    }

    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driver,LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver,LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
    }
}

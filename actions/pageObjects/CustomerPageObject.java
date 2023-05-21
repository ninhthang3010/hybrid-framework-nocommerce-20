package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {

    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public String getFirstNameTextboxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.FIRSTNAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.LASTNAME_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.LASTNAME_TEXTBOX,"value");
    }

    public String getEmailTextboxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.EMAIL_TEXTBOX,"value");
    }
}

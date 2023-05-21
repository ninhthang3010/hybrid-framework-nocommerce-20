package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_PageObject extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObjects loginPage;
    private CustomerPageObject customerPage;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
    }

    @Test
    public void User_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject();
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(),"");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"");

    }

    @Test
    public void User_02_Register_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getRandomEmail() {
        Random rand = new Random();
        return "john" + rand.nextInt(99999) + "kenedy.us";
    }


}

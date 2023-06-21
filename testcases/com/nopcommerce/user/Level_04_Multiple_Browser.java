package com.nopcommerce.user;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_04_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObjects loginPage;
    private CustomerPageObject customerPage;
    private String emailAddress = getRandomEmail();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = new HomePageObject(driver);
        //Mở 1 URL nào > Khởi tạo cái page đó lên (new)
        //Từ 1 page này chuyển qua page kia > Khởi tạo page đó lên
    }

    @Test
    public void User_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();
        //Sau khi click vào Register link nó sẽ mở ra trang Register page > Khởi tạo RegisterPageObject
        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"Password is required.");
    }

   @Test
    public void User_02_Register_Invalid_Email() {
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("john123456@kennedy@us");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Wrong email");

    }
    @Test
    public void User_03_Register_Invalid_Password() {
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("john123456@kennedy.us");
        registerPage.enterToPasswordTextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"Password must meet the following rules:\nmust have at least 6 characters");

    }

    @Test
    public void User_04_Register_Incorrect_Confirm_Password() {
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox("john123456@kennedy.us");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("654321");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"The password and confirmation password do not match.");

    }

    @Test
    public void User_05_Register_Success() {
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("John");
        registerPage.enterToLastNameTextbox("Kennedy");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");

    }

    @Test
    public void User_06_Login_Success() {
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToLoginLink();
        loginPage = new LoginPageObjects(driver);
        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox("123456");
        loginPage.clickLoginButton();
        homePage = new HomePageObject(driver);
        homePage.clickToMyAccountLink();
        customerPage = new CustomerPageObject(driver);
        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),"John");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),"Kennedy");
        Assert.assertEquals(customerPage.getEmailTextboxAttributeValue(),emailAddress);


    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }




}

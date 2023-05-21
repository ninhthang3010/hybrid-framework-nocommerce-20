package com.nopcommerce.user;


import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_BasePage_3_Inheritance extends BasePage{

    // Vi pham nguyên tắc: Không khởi tạo trực tiếp trên đối tượng  trên Class Test
    // Nên che giấu nó đi

    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() {

        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"),"Password is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"),"Password is required.");

    }

    @Test
    public void Register_02_Invalid_Email() {
        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");

        sendKeyToElement(driver,"//input[@id='FirstName']","John");
        sendKeyToElement(driver,"//input[@id='LastName']","Kennedy");
        sendKeyToElement(driver,"//input[@id='Email']","john1234@kennedy@us");
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Wrong email");
    }
    @Test
    public void Register_03_Invalid_Password() {

        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");

        sendKeyToElement(driver,"//input[@id='FirstName']","John");
        sendKeyToElement(driver,"//input[@id='LastName']","Kennedy");
        sendKeyToElement(driver,"//input[@id='Email']","john1234@kennedy.us");
        sendKeyToElement(driver,"//input[@id='Password']","123");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='Password-error']"),
                "Password must meet the following rules:\nmust have at least 6 characters");

    }

    @Test
    public void Register_04_Incorrect_Confirm_Password() {
        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");

        sendKeyToElement(driver,"//input[@id='FirstName']","John");
        sendKeyToElement(driver,"//input[@id='LastName']","Kennedy");
        sendKeyToElement(driver,"//input[@id='Email']","john1234@kennedy.us");
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","654321");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"),
                "The password and confirmation password do not match.");

    }
    @Test
    public void Register_05_Register_Success() {
        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");

        sendKeyToElement(driver,"//input[@id='FirstName']","John");
        sendKeyToElement(driver,"//input[@id='LastName']","Kennedy");
        sendKeyToElement(driver,"//input[@id='Email']",getEmailRandom());
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//div[@class='result']"),
                "Your registration completed");

    }

    public String getEmailRandom() {
        Random rand = new Random();
        return "john" + rand.nextInt(99999) + "@kennedy.us";
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}

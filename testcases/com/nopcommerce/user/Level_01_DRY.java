package com.nopcommerce.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_01_DRY {

    // Vi pham nguyen tac DRY (Don't Repeat Yourself)

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(),"First name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(),"Last name is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(),"Email is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(),"Password is required.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(),"Password is required.");

    }

    @Test
    public void Register_02_Invalid_Email() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Kennedy");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("john1234@kennedy@us");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(),"Wrong email");
    }
    @Test
    public void Register_03_Invalid_Password() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Kennedy");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("john1234@kennedy.us");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']"))
                        .getText(),"Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_04_Incorrect_Confirm_Password() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Kennedy");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("john1234@kennedy.us");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("654321 ");
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).
                getText(),"The password and confirmation password do not match.");

    }
    @Test
    public void Register_05_Register_Success() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Kennedy");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(getEmailRandom());
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).
                getText(),"Your registration completed");

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

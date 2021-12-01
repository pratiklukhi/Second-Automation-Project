package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiTest {

    static WebDriver driver;

    public static void clickOnElement(By by){
        driver.findElement(by).click();
    }

    public static void typeText(By by , String text){
        driver.findElement(by).sendKeys(text);
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static String currentTimeStamp(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyhhmmss");
        return sdf.format(date);
    }

    public static void waitForClickable(By by, int timeInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,timeInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForVisible(By by, int timeInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        // Type URL
        driver.get("http://demo.nopcommerce.com/");

    }
    @Test

    public void verifyUserShouldBeAbleToRegisterSuccessfully(){
        System.out.println(currentTimeStamp());

        // Click on Register button
        clickOnElement(By.xpath("//a[@href='/register?returnUrl=%2F']"));

        // Type First name
        typeText(By.name("FirstName"), "Pratik");

        // Type Last name
        typeText(By.name("LastName"), "Patel");

        // Type date of birth
        //Select day from dropdown
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByVisibleText("18");

        // Select month from dropdown
        Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByValue("9");

        // Select year
        Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByVisibleText("1993");

        // Type Email
        typeText(By.id("Email"), "pratikpatel12+" + currentTimeStamp() + "@gmail.com");

        // Type password
        typeText(By.id("Password"), "2345677");

        // Type Confirm password
        typeText(By.id("ConfirmPassword"), "2345677");

        // Click on Register button
        clickOnElement(By.xpath("//button[@name='register-button']"));

        String actualRegisterSuccessMessage=getTextFromElement(By.xpath("//div[@class='result']"));
        String expectedRegisterSuccessMessage="Your registration completed";
        Assert.assertTrue(actualRegisterSuccessMessage.equals(expectedRegisterSuccessMessage),"Registration successfully");


    }
    @AfterMethod
    public void CloseBrowsweer(){
        //driver.close();
    }

    @Test
    public void NewsComments(){

        // Click on button of Details
        clickOnElement(By.xpath("//div[@class='news-items']/div[2]/div[3]/a"));

        // Type title
        typeText(By.id("AddNewComment_CommentTitle"), "Nice Product");

       // Type comment
        typeText(By.id("AddNewComment_CommentText"), "This is an amazing product. I like this most.");

       // Click on button of New comment
        clickOnElement(By.xpath("//button[@name='add-comment']"));

        // Result message show
        String actualResult = getTextFromElement(By.xpath("//div[@class='result']"));
        String expectedResult = "News comment is successfully added.";
        Assert.assertTrue(actualResult.equals(expectedResult),"News comment is successfully added.");



    }
@Test
    public void VerifyRegisteredUserShouldBeAbleToReferAProductToFriend(){

    // Click on Register button
    clickOnElement(By.xpath("//a[@href='/register?returnUrl=%2F']"));

    // Type First name
    typeText(By.name("FirstName"), "Pratik");

    // Type Last name
    typeText(By.name("LastName"), "Patel");

    // Type date of birth
    //Select day from dropdown
    Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
    selectDay.selectByVisibleText("18");

    // Select month from dropdown
    Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
    selectMonth.selectByValue("9");

    // Select year
    Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
    selectYear.selectByVisibleText("1993");

    // Type Email
    typeText(By.id("Email"), "pratikpatel12+" + currentTimeStamp() + "@gmail.com");

    // Type password
    typeText(By.id("Password"), "2345677");

    // Type confirm password
    typeText(By.id("ConfirmPassword"), "2345677");

    // Click on Register button
    clickOnElement(By.xpath("//button[@name='register-button']"));

    // Registration success message result
    String actualRegisterSuccessMessage=getTextFromElement(By.xpath("//div[@class='result']"));
    String expectedRegisterSuccessMessage="Your registration completed";
    Assert.assertTrue(actualRegisterSuccessMessage.equals(expectedRegisterSuccessMessage),"Registration successfully");

    // Click on continue button
    clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));

    // Click on Apple MacBook Pro 13-inch
    clickOnElement(By.xpath("//h2/a[@href='/apple-macbook-pro-13-inch']"));

    // Click on button of Email a friend
    clickOnElement(By.xpath("//button[@class='button-2 email-a-friend-button']"));

    // Type Friend's email
    typeText(By.id("FriendEmail"), "prpatel19@gmail.com");

    // Type Personal message
    typeText(By.id("PersonalMessage"), "This is an amazing product.");

    // Click on button of Send Email
    clickOnElement(By.xpath("//button[@class='button-1 send-email-a-friend-button']"));


    // Result message
    String actualResultSuccessMessage = getTextFromElement(By.xpath("//div[@class='result']"));
    String expectedResultSuccessMessage = "Your message has been sent.";
    Assert.assertTrue(actualResultSuccessMessage.equals(expectedResultSuccessMessage), "Friend reffered successfully");


    }
    @Test

    public void UserIsAbleToNavigateToDesktopPage(){

        // Click on Computers
        clickOnElement(By.xpath("//div[@class='header-menu']/ul[@class='top-menu notmobile']/li/a[@href='/computers']"));

        // Click on Desktops
        clickOnElement(By.xpath("//h2/a[@href='/desktops']"));
    }
}


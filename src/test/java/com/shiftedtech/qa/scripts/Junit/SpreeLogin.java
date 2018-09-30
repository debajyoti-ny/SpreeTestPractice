package com.shiftedtech.qa.scripts.Junit;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpreeLogin {

    protected static WebDriver driver;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("************** Starting Spree Test **************");

        String driverLocation = System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe";
        File file = new File(driverLocation);
        if(file.exists()){
            System.setProperty("webdriver.chrome.driver", driverLocation);
        }else {
            throw new RuntimeException("Unable to locate driver in " + driverLocation);
        }

    }

    @Before
    public void setUp(){
//        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, TimeUnit.SECONDS);

        driver.navigate().to("http://spree.shiftedtech.com/");
    }

    @Test
    public void validEmailValidPassword (){
        WebElement loginBtn = driver.findElement(By.xpath("//nav[@id='top-nav-bar']//a"));
        loginBtn.click();

        WebElement emailBox = driver.findElement(By.id("spree_user_email"));
        emailBox.sendKeys("deba007190@gmail.com");

        WebElement passwordBox = driver.findElement(By.id("spree_user_password"));
        passwordBox.sendKeys("deb0119");

        WebElement login = driver.findElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        login.click();

        WebElement signInSuccess = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String actualText = signInSuccess.getText();
        String expectedText = "Logged in successfully";
        Assert.assertEquals(expectedText,actualText);
    }

    @Test
    public void validEmailInvalidPassword(){
        WebElement loginBtn = driver.findElement(By.xpath("//nav[@id='top-nav-bar']//a"));
        loginBtn.click();

        WebElement emailBox = driver.findElement(By.id("spree_user_email"));
        emailBox.sendKeys("deba007190@gmail.com");

        WebElement passwordBox = driver.findElement(By.id("spree_user_password"));
        passwordBox.sendKeys("invalid");

        WebElement login = driver.findElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        login.click();

        WebElement signInFailure = driver.findElement(By.xpath("//div[@class='alert alert-error']"));
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }

    @Test
    public void invalidEmailValidPassword(){
        WebElement loginBtn = driver.findElement(By.xpath("//nav[@id='top-nav-bar']//a"));
        loginBtn.click();

        WebElement emailBox = driver.findElement(By.id("spree_user_email"));
        emailBox.sendKeys("deba007190xx@gmail.com");

        WebElement passwordBox = driver.findElement(By.id("spree_user_password"));
        passwordBox.sendKeys("deb0119");

        WebElement login = driver.findElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        login.click();

        WebElement signInFailure = driver.findElement(By.xpath("//div[@class='alert alert-error']"));
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }

    @Test
    public void invalidEmailInvalidPassword(){
        WebElement loginBtn = driver.findElement(By.xpath("//nav[@id='top-nav-bar']//a"));
        loginBtn.click();

        WebElement emailBox = driver.findElement(By.id("spree_user_email"));
        emailBox.sendKeys("deba007190xx@gmail.com");

        WebElement passwordBox = driver.findElement(By.id("spree_user_password"));
        passwordBox.sendKeys("invalid");

        WebElement login = driver.findElement(By.xpath("//input[@class='btn btn-lg btn-success btn-block']"));
        login.click();

        WebElement signInFailure = driver.findElement(By.xpath("//div[@class='alert alert-error']"));
        String actualText = signInFailure.getText();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(expectedText,actualText);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }

    public void delay(int timeInMilliSeconds){
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void highlight(WebElement element){
        for(int i=0; i<2; i++){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 3px solid red");
            delay(300);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            delay(300);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var el=arguments[0]; setTimeout(function() {e1.click();}, 100);", element);
    }

    public void actionClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

}

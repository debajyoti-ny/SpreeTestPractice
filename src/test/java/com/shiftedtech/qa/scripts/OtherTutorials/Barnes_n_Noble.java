package com.shiftedtech.qa.scripts.OtherTutorials;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Barnes_n_Noble {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        System.out.println("**************** Starting Test ****************");

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

        driver.navigate().to("https://www.barnesandnoble.com/");
    }

    @Test
    public void createAccount(){
        closePopUp();
        clickSignIn();

        switchToIFrame("https://www.barnesandnoble.com/account/login-frame-ra");
        clickCreateAccount();
        switchToTopWindow();

        switchToIFrame("https://www.barnesandnoble.com/account/register-frame-ra");

        createUserAccount("Debajyoti", "Paul", "deb011900@gmail.com",
                            "XYZdeba0119", "What is your favorite car?",
                            "Laborghini");

        verifyAccountCreation_SignIn_Success("Debajyoti");

    }

    @Test
    public void signIn(){
        closePopUp();
        clickSignIn();
        switchToIFrame("https://www.barnesandnoble.com/account/login-frame-ra");
        signInCredentials("deb011900@gmail.com", "XYZdeba0119");
        verifyAccountCreation_SignIn_Success("Debajyoti");
    }

    @AfterClass
    public static void tearDown(){
//        driver.close();
        driver.quit();
    }

    /**
     * Methods used in the above tests are written below...
     */

    public void closePopUp() {
        WebElement popUpClose = driver.findElement(By.xpath("//div/a[@class='icon-close-modal']"));
        popUpClose.click();
        delay(2000);
    }

    public void clickSignIn() {
        WebElement signInButton = driver.findElement(By.xpath("//div/a[@id='signInLink']"));
        signInButton.click();
        delay(2000);
    }

    public void clickCreateAccount() {
        WebElement createAccount = driver.findElement(By.xpath("//div[@class='col-lg-12 text--center']//a[@id='createAccountBtn']"));
        highlight(createAccount);
        createAccount.click();
        delay(2000);
    }

    public void switchToIFrame(String srcURL) {
        List<WebElement> iFrameList = driver.findElements(By.tagName("iframe"));
        System.out.println("Number of Iframes is: " + iFrameList.size());
        for(WebElement iFrame : iFrameList){
            String src = iFrame.getAttribute("src"); //.getAttribute method is used to retrieve the text from assigned to each attributes like "src", "id", "name", "value", etc.
            System.out.println("Src: " + src);
            if(src.contains(srcURL)){
                driver.switchTo().frame(iFrame);
                break;
            }
        }
        delay(2000);
    }

    public void waitAndFindFrameByURL(WebElement iFrame) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iFrame));
    }

    public void switchToTopWindow() {
        driver.switchTo().defaultContent();
    }

    public void createUserAccount(String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer) {
        WebElement fName = driver.findElement(By.cssSelector("#fName"));
        fName.sendKeys(firstName);

        WebElement lName = driver.findElement(By.cssSelector("#lName"));
        lName.sendKeys(lastName);

        WebElement emailBox = driver.findElement(By.cssSelector("#email"));
        emailBox.sendKeys(email);

        WebElement confirmEmail = driver.findElement(By.cssSelector("#confirmEmail"));
        confirmEmail.sendKeys(email);

        WebElement passwordBox = driver.findElement(By.cssSelector("#password"));
        passwordBox.sendKeys(password);

        WebElement confirmPassword = driver.findElement(By.cssSelector("#confPassword"));
        confirmPassword.sendKeys(password);

        WebElement securityQstn = driver.findElement(By.cssSelector("#securityQuestion-replacement"));
        securityQstn.click();
        List<WebElement> options = driver.findElements(By.xpath("//ul[@id='securityQuestion-option-list']/li/a"));
        int count = 1;
        for(WebElement option: options){
            String questions = option.getText();
            System.out.println("Security Question #" + count + " : " + questions);
            if(questions.trim().equalsIgnoreCase(securityQuestion)){
                option.click();
                break;
            }
            count++;
        }

        WebElement securityAns = driver.findElement(By.cssSelector("#securityAnswer"));
        securityAns.sendKeys(securityAnswer);

        WebElement createAccBtn = driver.findElement(By.cssSelector("#btnCreateAccount"));
        createAccBtn.click();

        delay(3000);
    }


    public void verifyAccountCreation_SignIn_Success(String firstName) {
        WebElement welcomeMsg = driver.findElement(By.cssSelector("#userLinks>a"));
        String actualText = welcomeMsg.getText().trim();
        String expectedText = "Welcome, " + firstName;
        Assert.assertEquals(expectedText, actualText);
    }


    public void signInCredentials(String email, String password) {
        WebElement emailBox = driver.findElement(By.cssSelector("#email"));
        emailBox.sendKeys(email);

        WebElement passwordBox = driver.findElement(By.cssSelector("#password"));
        passwordBox.sendKeys(password);

        WebElement signIn = driver.findElement(By.cssSelector(".btn.btn--large"));
        highlight(signIn);
        signIn.click();

        delay(5000);
    }

    public void delay(int timeInMilli){
        try {
            Thread.sleep(timeInMilli);
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

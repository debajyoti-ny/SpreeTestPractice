package com.shiftedtech.qa.scripts.OtherTutorials;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Go to "http://commons.apache.org/proper/commons-email/userguide.html" to find the sample codes
 * for each of the different types of emails.
 */
public class Send_Emails{

    @BeforeMethod
    public static void beforeClass(){
        System.out.println("********** Starting Test **********");
    }

    @Test
    public void test1() throws EmailException {
        int x = 90;
        int y = 90;
        Assert.assertEquals(x,y);       //Email won't be sent because test won't fail
        System.out.println("*********** Test Finished ***********");

//        sendEmail("deb2018paul@gmail.com",
//                  "Second Practice Test Mail",
//                  "This is a test mail from Debajyoti Paul... :-)");
    }

    @Test
    public void test2() throws EmailException {
        int x = 90;
        int y = 100;
        Assert.assertEquals(x, y);      //Email will be sent because test will fail
        System.out.println("*********** Test Finished ***********");
    }

    @AfterMethod
    public static void afterMethod(ITestResult result) throws EmailException{
        if(result.getStatus() == ITestResult.FAILURE){
            sendEmail("deb2018paul@gmail.com",
                      "Failed test Report",
                      "This test failed because the values didn't match..!!");
        }
    }


    public static void sendEmail(String sendTo, String subject, String message) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("selenium2018paul@gmail.com", "seleniumdebajyoti"));
        email.setSSLOnConnect(true);
        email.setFrom("debajyoti007@gmail.com"); //Not sure why it's required
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(sendTo);
        email.send();
    }

}

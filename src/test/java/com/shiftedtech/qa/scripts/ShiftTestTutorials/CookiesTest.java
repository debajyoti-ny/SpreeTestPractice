package com.shiftedtech.qa.scripts.ShiftTestTutorials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookiesTest extends BaseClass {
    @Before
    public void setUpLocalPage(){
        String url = "http://shifttest.shiftedtech.com";
        driver.navigate().to(url);
    }


    @Test
    public void bakeCookies(){
        Set<Cookie> boxOfCookies = driver.manage().getCookies();
        Assert.assertFalse(boxOfCookies.isEmpty());

        Cookie myCookie = driver.manage().getCookieNamed("_shift_selenium_test_app_session");

        System.out.println("domain for this cookie is " + myCookie.getDomain());
        System.out.println("name for this cookie is " + myCookie.getName());
        System.out.println("path for this cookie is " + myCookie.getPath());
        System.out.println("value for this cookie is " + myCookie.getValue());
        System.out.println("expiration for this cookie is " + myCookie.getExpiry());

        Cookie cookie1 = new Cookie("cookie1", "chocolate chip", "/", null);
        Cookie cookie2 = new Cookie("cookie2", "macadamia nut", "/", null);

        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);

        Set<Cookie> newBoxOfCookies = driver.manage().getCookies();

        int newNumberOfCookies = newBoxOfCookies.size();

        Assert.assertEquals(3, newNumberOfCookies);

    }

}

package com.shiftedtech.qa.framework.composite_functions;

import org.openqa.selenium.By;

public class ObjectRepo {
    public static String SPREE_WEBSITE_URL = "http://spree.shiftedtech.com/";
    public static By HOME_LOGIN_LINK = By.xpath("//nav[@id='top-nav-bar']//a");
    public static By EMAIL_BOX = By.id("spree_user_email");
    public static By PASSWORD_BOX = By.id("spree_user_password");
    public static By LOGIN_BUTTON = By.xpath("//input[@class='btn btn-lg btn-success btn-block']");
    public static By LOGIN_SUCCESS_MSG = By.xpath("//div[@class='alert alert-success']");
    public static By LOGIN_FAILURE_MSG = By.xpath("//div[@class='alert alert-error']");
}

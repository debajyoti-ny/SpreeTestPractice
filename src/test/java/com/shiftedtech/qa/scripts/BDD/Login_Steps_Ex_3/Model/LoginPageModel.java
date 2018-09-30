package com.shiftedtech.qa.scripts.BDD.Login_Steps_Ex_3.Model;

import org.openqa.selenium.By;

public class LoginPageModel {
    public static By LOGIN_LINK = By.linkText("Login");
    public static By EMAIL_BOX = By.id("spree_user_email");
    public static By PASSWORD_BOX = By.id("spree_user_password");
    public static By LOGIN_BUTTON = By.xpath("//input[@name='commit']");
    public static By SUCCESS_MSG = By.xpath("//div[@id='content']/div[contains(@class,'alert-success')]");
    public static By ERROR_MSG = By.xpath("//div[@class='alert alert-error']");

}

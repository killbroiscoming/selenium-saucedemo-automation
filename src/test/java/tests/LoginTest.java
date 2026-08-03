package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {


    @Test
    public void shouldLoginSuccessfully() {

        LoginPage loginPage = new LoginPage(driver);
       ProductsPage productsPage=  loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

    Assert.assertFalse(productsPage.isProductPageDisplayed());
    }

    @Test
    public void shouldInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("username1"),
                ConfigReader.getProperty("password1"));



        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );

    }
}

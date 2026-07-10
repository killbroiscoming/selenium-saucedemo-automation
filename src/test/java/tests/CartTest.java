package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    @Test
    public void shouldProductAppearInCart(){
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        productsPage.addProductToCart("Sauce Labs Bike Light");

        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count should be 1 after adding a product."
        );

        CartPage cartPage = productsPage.openCart();

        Assert.assertTrue(
                cartPage.isProductDisplayed("Sauce Labs Bike Light"));
    }

    @Test
    public void shouldContinueShopping(){
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        productsPage.addProductToCart("Sauce Labs Bike Light");
        CartPage cartPage = productsPage.openCart();
        cartPage.continueShopping();

        Assert.assertTrue(
                productsPage.isProductPageDisplayed(),
                "Product page should be displayed"
        );
    }

}

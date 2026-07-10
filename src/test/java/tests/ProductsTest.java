package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class ProductsTest extends BaseTest {

    @Test
    public void shouldAddProductToCart()
    {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        ).waitForPageToLoad();

        String productName ="Sauce Labs Bike Light";

        productsPage.addProductToCart(productName);

        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count should be 1 after adding a product."
        );
    }

    @Test
    public void shouldRemoveProductFromCart(){
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        String productName = "Sauce Labs Fleece Jacket";
        productsPage.addProductToCart(productName);

        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count should be 1 after adding a product."
        );
        productsPage.removeProductFromCart(productName);

        Assert.assertEquals(
                productsPage.getCartCount(),
                0,
                "Cart count should be 0 after removing a product.");

    }

    @Test
    public void shouldAddAndRemoveMultipleProducts()
    {
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");

        Assert.assertEquals(
                productsPage.getCartCount(),
                2,
                "Cart count should be 2 after adding a product."
        );

        productsPage.removeProductFromCart("Sauce Labs Backpack");

        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count should be 1 after removing a product."
        );

    }
}

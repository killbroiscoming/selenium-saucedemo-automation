package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;


public class CheckoutTest extends BaseTest {

    @Test
    public void shouldCompleteCheckout(){

        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        String productName="Sauce Labs Bike Light";

        productsPage.addProductToCart(productName);
        Assert.assertEquals(
                productsPage.getCartCount(),
                1,
                "Cart count should be 1 after adding a product."
        );

        CartPage cartPage = productsPage.openCart();

        CheckoutPage checkoutPage = cartPage.checkout();

        CheckoutOverviewPage overviewPage =
                checkoutPage.continueCheckout(
                        ConfigReader.getProperty("firstname"),
                        ConfigReader.getProperty("lastname"),
                        ConfigReader.getProperty("postalCode")
                );

        CheckoutCompletePage completePage = overviewPage.finishOrder();

        Assert.assertEquals(
                completePage.getCompleteHeaderText(),
                "Thank you for your order!");

    }

    @Test
    public void shouldReturnToCartWhenCancelCheckoutInformation(){
        LoginPage loginPage = new LoginPage(driver);

        ProductsPage productsPage = loginPage.loginSuccessfully(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        String productName = "Sauce Labs Bike Light";

        productsPage.addProductToCart(productName);

        CartPage cartPage = productsPage.openCart();

        Assert.assertTrue(
                cartPage.isProductDisplayed(productName),
                "Selected product should be displayed in the cart before checkout."
        );

        CheckoutPage checkoutPage = cartPage.checkout();

        CartPage returnedCartPage = checkoutPage.cancelCheckout();

        Assert.assertTrue(
                returnedCartPage.isProductDisplayed(productName),
                "User should return to the cart and the selected product should remain in the cart."
        );
    }
}

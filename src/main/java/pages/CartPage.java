package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By cartTitle = By.cssSelector("[data-test='title']");
    private final By checkoutBtn =
            By.id("checkout");
    private final By continueShoppingBtn = By.cssSelector("[data-test='continue-shopping']");

    public CartPage waitForPageToLoad(){
        waitForUrlContains("cart.html");
        waitForVisible(cartTitle);
        return this;
    }
    public boolean isProductDisplayed(String productName) {

        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));

        for (WebElement product : products) {
            if(product.getText().equals(productName)){
                return true;
            }
        }
        return false;
    }

    public CheckoutPage checkout(){
        click(checkoutBtn);
        return new CheckoutPage(driver).waitForPageToLoad();
    }

    public ProductsPage continueShopping(){
        click(continueShoppingBtn);
        return new ProductsPage(driver).waitForPageToLoad();
    }
}

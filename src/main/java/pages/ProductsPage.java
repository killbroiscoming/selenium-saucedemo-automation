package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By cartIcon =
            By.cssSelector("[data-test='shopping-cart-link']");

    private final By secondaryHeader =
            By.className("title");

    public ProductsPage waitForPageToLoad() {
       waitForUrlContains("inventory.html");
       waitForTextToBe(secondaryHeader,"Products");
        return this;
    }

    //Actions
    public By addToCartButton(String productName){
        String productId = "add-to-cart-" +productName.toLowerCase().replace(" ", "-");
        return By.id(productId);
    }

    public By removeButton(String productName){
        String productId = "remove-" + productName.toLowerCase().replace(" ", "-");
        return By.id(productId);
    }

    public ProductsPage addProductToCart(String productName){
        By addBtn = addToCartButton(productName);
        By removeBtn = removeButton(productName);

        click(addBtn);
        // Final explicit wait anchor to guarantee stability
        waitForVisible(removeBtn);
        return this;
    }

    public ProductsPage removeProductFromCart(String productName){
        By addBtn = addToCartButton(productName);
        By removeBtn = removeButton(productName);

        click(removeBtn);
        waitForVisible(addBtn);
        return this;
    }

    public int getCartCount() {
       if (!exists(cartBadge)) {
           return 0;
       }
       return Integer.parseInt(getText(cartBadge));
    }

    public CartPage openCart(){
        click(cartIcon);
        return new CartPage(driver).waitForPageToLoad();
    }

    public String getSecondaryHeader(){
        return getText(secondaryHeader);
    }

    public boolean isProductPageDisplayed(){
       return getSecondaryHeader().equals("Products");
    }
}

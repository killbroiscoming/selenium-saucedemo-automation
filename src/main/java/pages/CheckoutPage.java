package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutTitle = By.cssSelector("[data-test='title']");
    private final By firstname = By.id("first-name");
    private final By lastname = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By cancelBtn = By.cssSelector("[data-test='cancel']");

    public void enterFirstName(String user) {
        type(firstname, user);
    }

    public void enterLastName(String user) {
        type(lastname, user);
    }

    public void enterPostalCode(String zip) {
        type(postalCode, zip);
    }
    public CheckoutOverviewPage continueCheckout(String first, String last, String zip){
        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(zip);
       click(continueBtn);

        return new CheckoutOverviewPage(driver).waitForPageToLoad();
    }

    public CheckoutPage waitForPageToLoad(){
       waitForUrlContains("checkout-step-one.html");
       waitForVisible(checkoutTitle);
        return this;
    }

    public CartPage cancelCheckout(){
        click(cancelBtn);
        return new CartPage(driver).waitForPageToLoad();
    }
}

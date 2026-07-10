package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutOverviewPage extends BasePage {
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutOverviewTitle = By.cssSelector("[data-test='title']");
    private final By finishBtn = By.id("finish");

    public CheckoutCompletePage finishOrder(){
        click(finishBtn);
        return new CheckoutCompletePage(driver).waitForPageToLoad();
    }


    public CheckoutOverviewPage waitForPageToLoad(){
        waitForUrlContains("checkout-step-two.html");
        waitForVisible(checkoutOverviewTitle);
        return this;
    }

}

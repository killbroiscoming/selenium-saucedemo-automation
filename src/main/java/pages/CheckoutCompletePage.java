package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    private final By checkoutCompleteTitle = By.cssSelector("[data-test='title']");
    private final By completeHeader =
            By.xpath("//h2[@class='complete-header']");

    public String getCompleteHeaderText() {
        return getText(completeHeader);
    }

    public CheckoutCompletePage waitForPageToLoad(){
      waitForUrlContains("checkout-complete.html");
      waitForVisible(checkoutCompleteTitle);
        return this;
    }
}

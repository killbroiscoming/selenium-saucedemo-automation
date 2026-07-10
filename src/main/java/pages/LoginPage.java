package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.cssSelector("[data-test='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public  LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user) {
        type(username,user);
    }

    public void enterPassword(String pwd) {
        type(password,pwd);
    }


    public void  login(String username, String pwd) {
        enterUsername(username);
        enterPassword(pwd);
       click(loginBtn);
    }

    public ProductsPage loginSuccessfully(String username, String pwd) {
        login(username,pwd);
        return new ProductsPage(driver).waitForPageToLoad();
    }


    public String getLoginErrorMessage() {
        return getText(errorMessage);
    }

}
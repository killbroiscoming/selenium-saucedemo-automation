package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod()
    public void setup(){
        // 1. Initialize ChromeOptions configuration parameters
        ChromeOptions options = new ChromeOptions();

        // CRITICAL FIX: Turn off Chrome's compromised password warning popups entirely
        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // 2. Proactively toggle headless configurations exclusively on the GitHub Cloud CI/CD environment
        if(System.getenv("GITHUB_ACTIONS") != null){
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        // 3. Pass the options directly to your driver instance instantiation statement
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @AfterMethod()
    public void teardown(){
        if(driver != null){
            driver.quit();
        }

    }

    public  WebDriver getDriver(){
        return driver;
    }
}

package listeners;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

import java.io.ByteArrayInputStream;

public class ScreenshotListener  implements ITestListener {

    @Override
    public void onTestFailure(ITestResult tr) {

        Object currentTestClass = tr.getInstance();

        if(!(currentTestClass instanceof BaseTest)){
            System.out.printf("Test class does not extend BaseTest");
            return;
        }

        BaseTest baseTest = (BaseTest) currentTestClass;

        WebDriver driver = baseTest.getDriver();

            String testName = tr.getMethod().getMethodName();

            byte[] screenshot = ScreenshotUtil.takeScreenshot(driver,testName);

            if(screenshot != null){
                Allure.addAttachment(
                        "Failure Screenshot - " + tr.getName(),
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );
            }

    }

}

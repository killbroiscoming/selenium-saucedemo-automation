package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ScreenshotUtil {

    public static byte[] takeScreenshot(WebDriver driver, String testName) {

        if (driver == null) {
            System.out.println("Driver is null. Screenshot cannot be taken.");
            return null;
        }

        byte[] screenshotBytes =
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        saveScreenshotToFolder(screenshotBytes, testName);

        return screenshotBytes;
    }

    private static void saveScreenshotToFolder(byte[] screenshotBytes, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        String fileName = testName + "_" + timestamp + ".png";

        Path folderpath = Paths.get("screenshots");
        Path filePath = folderpath.resolve(fileName);

        try {
            Files.createDirectories(folderpath);

            Files.write(filePath,screenshotBytes);

            System.out.printf("Screenshot saved: " +filePath.toAbsolutePath());

        } catch (IOException e) {
            System.out.printf("Failed to save screenshot: ", e.getMessage());
        }




    }

}


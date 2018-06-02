package com.aerokube.selenoid;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.UUID;

public class DemoTest {

    private RemoteWebDriver driver;

    @Before
    public void openDriver() throws Exception {
        final DesiredCapabilities browser = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL(
                "http://selenoid.example.com:4444/wd/hub" //Replace with correct host and port
        ), browser);
    }

    @Test
    public void browserTest() throws Exception {
        try {
            driver.get("http://duckduckgo.com/");
            WebElement input = driver.findElement(By.cssSelector("input#search_form_input_homepage"));
            input.sendKeys(Keys.chord("selenium", Keys.ENTER));
        } finally {
            takeScreenshot(driver);
        }

    }

    static void takeScreenshot(RemoteWebDriver driver) throws Exception {
        byte[] screen = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.BYTES);
        FileUtils.writeByteArrayToFile(new File(driver.getSessionId() + ".png"), screen);
    }

    @After
    public void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}

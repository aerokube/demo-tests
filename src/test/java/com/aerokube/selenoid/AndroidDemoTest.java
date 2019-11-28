package com.aerokube.selenoid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.aerokube.selenoid.DemoTest.takeScreenshot;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AndroidDemoTest {

    private static final By BUTTON_2 = By.id("com.android.calculator2:id/digit_2");
    private static final By BUTTON_PLUS = By.id("com.android.calculator2:id/op_add");
    private static final By BUTTON_7 = By.id("com.android.calculator2:id/digit_7");
    private static final By BUTTON_EQUALS = By.id("com.android.calculator2:id/eq");
    private static final By RESULT_FIELD = By.id("com.android.calculator2:id/formula");
    
    private RemoteWebDriver driver;

    @Before
    public void openDriver() throws Exception {
        final DesiredCapabilities device = new DesiredCapabilities();
        device.setCapability("deviceName", "android");
        device.setCapability("version", "6.0");
//        device.setCapability("app", "http://download.example.com/test.apk"); // Uncomment this to download your own APK
        device.setCapability("appPackage", "com.android.calculator2"); // Set this to your APK package
        device.setCapability("appActivity", "com.android.calculator2.Calculator");  // Set this to your APK main activity
        device.setCapability("enableVNC", true); // Uncomment this to record 
//        device.setCapability("enableVideo", true); // Uncomment this to record video

        driver = new RemoteWebDriver(new URL(
                "http://selenoid.example.com:4444/wd/hub" //Replace with correct host and port
        ), device);
    }

    @Test
    public void browserTest() throws Exception {
        try {
            driver.findElement(BUTTON_2).click();
            driver.findElement(BUTTON_PLUS).click();
            driver.findElement(BUTTON_7).click();
            driver.findElement(BUTTON_EQUALS).click();
            assertThat(driver.findElement(RESULT_FIELD).getText(), equalTo("9"));
        } finally {
            takeScreenshot(driver);
        }
    }

    @After
    public void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}

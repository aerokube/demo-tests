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

public class AndroidRemoteApkTest {

    private RemoteWebDriver driver;

    @Before
    public void openDriver() throws Exception {
        final DesiredCapabilities device = new DesiredCapabilities();
        device.setCapability("deviceName", "android");
        device.setCapability("version", "6.0");
        device.setCapability("app", "http://ci.example.com/game2048.apk"); //APK from https://www.apkmirror.com/apk/androbaby/2048/2048-2-1-release/2048-2-1-android-apk-download/download/
        device.setCapability("appPackage", "com.androbaby.game2048");
        device.setCapability("appActivity", "com.androbaby.game2048.MainActivity");
        device.setCapability("enableVNC", true);
//        device.setCapability("enableVideo", true); // Uncomment this to record video

        driver = new RemoteWebDriver(new URL(
                "http://selenium.example.com:4444/wd/hub" //Replace with correct host and port
        ), device);
    }

    @Test
    public void browserTest() throws Exception {
        try {
            driver.findElement(By.xpath("//*[@text=\"Start Game\"]")).click();
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

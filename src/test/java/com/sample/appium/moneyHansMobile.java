package com.sample.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class moneyHansMobile {

    public AndroidDriver<MobileElement> driver;
    public static DesiredCapabilities cap;
    public File app;
    public String apkpath = "/Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/resources/com.android.chrome-54.apk";

    @Test
    public void setUp() throws MalformedURLException {

	    app = new File (apkpath);

        cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "192.168.57.103:5555");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "4.4.4");
        cap.setCapability("app", apkpath);
        //com.android.chrome,org.chromium.chrome.browser.LauncherShortcutActivity
        cap.setCapability("appPackage", "com.android.chrome");
        cap.setCapability("appActivity", "org.chromium.chrome.browser.LauncherShortcutActivity");
        //cap.setCapability("browserName", MobileBrowserType.CHROME);

        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:"+4723+"/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/* @Test
 public void mobileTest() throws InterruptedException {
	 
	System.out.println("AAAA"); 
  // Click on DELETE/CLR button to clear result text box before running test.
  driver.get("http://www.themoneyhans.com");
  System.out.println("BBBB");
  String expTitle = "-The Money Hans";
  String actTitle = driver.getTitle();
  System.out.println(actTitle);
  //Assert.assertEquals(expTitle,actTitle);	
  Thread.sleep(3000);
  System.out.println("CCCC");
  driver.get("http://www.themoneyhans.com/category/fun-money-roof");
  Thread.sleep(3000);
  driver.get("http://www.themoneyhans.com/articles/fun-money-roof/put-your-eggs-in-one-basket...");
  System.out.println("Mobile App Tested");
  driver.close();
  
 }*/

}



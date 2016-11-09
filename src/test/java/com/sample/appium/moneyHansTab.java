package com.sample.appium;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class moneyHansTab {
    public RemoteWebDriver driver;
    public static DesiredCapabilities cap;

	 @BeforeTest
     @Parameters({"port", "device", "platform_name", "platform_version"})
	 public void setUp(String port, String device, String platform_name, String platform_version) throws MalformedURLException {

         cap = new DesiredCapabilities();
         cap.setCapability(MobileCapabilityType.PLATFORM_NAME , platform_name);
         cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platform_version);
         cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
         //cap.setCapability(MobileCapabilityType.APP, file);
         cap.setCapability("appPackage", "com.android.chrome-1");
	  driver = new RemoteWebDriver(new URL("http://localhost:"+port+"/wd/hub"), cap);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 }

	 @Test
	 public void tabletTest() throws InterruptedException {
	  // Click on DELETE/CLR button to clear result text box before running test.
	  driver.get("http://www.themoneyhans.com");

	  String expTitle = "-The Money Hans";
	  String actTitle = driver.getTitle();
	  System.out.println(actTitle);
	  //Assert.assertEquals(expTitle,actTitle);	
	  Thread.sleep(3000);
	  driver.get("http://www.themoneyhans.com/category/fun-money-roof");
	  Thread.sleep(3000);
	  driver.get("http://www.themoneyhans.com/articles/fun-money-roof/put-your-eggs-in-one-basket...");
	  System.out.println("Mobile App Tested");
	  driver.close();
	  
	 }

	@AfterTest
	 public void End() {
	  driver.quit();
	 }

}

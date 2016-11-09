package com.sample.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * node_config_1.json
 * node_config_2.json
 * node_config_3.json
 * 
 * @author Khaja
 *
 * Grid: java -jar /Users/Khaja/Documents/jars/selenium-server-standalone-2.53.0.jar -role huhttp://127.0.0.1:4444/grid
 * Node 1: appium -a 127.0.0.1 -p 4723 --no-reset --bootstrap-port 4728 -U 192.168.57.102:5555 --nodeconfig /Users/Khaja/Documents/jars/node_config_1.json
 * Node 2: appium -a 127.0.0.1 -p 4725 --no-reset --bootstrap-port 4729 -U 192.168.57.101:5555 --nodeconfig /Users/Khaja/Documents/jars/node_config_2.json
 * Node 3: appium -a 127.0.0.1 -p 4727 --no-reset --bootstrap-port 4730 -U 192.168.0.20:5555 --nodeconfig /Users/Khaja/Documents/jars/node_config_3.json
 *
 * Updated Grid Settings:
 *
 * <b>Grid</b>: java -jar /Users/Khaja/Documents/SpotQA/SampleAppmParallelTests/src/resources/selenium-server-standalone-2.53.1.jar -role hub http://127.0.0.1:4444/grid
 * <b>Node 1:</b> appium -a 127.0.0.1 -p 4723 --no-reset --bootstrap-port 4728 -U 192.168.57.101:5555 --nodeconfig /Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/resources/node_config_1.json
 * <b>Node 2:</b> appium -a 127.0.0.1 -p 4725 --no-reset --bootstrap-port 4729 -U 192.168.57.103:5555 --nodeconfig /Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/resources/node_config_2.json
 * <b>Node 3:</b> appium -a 127.0.0.1 -p 4727 --no-reset --bootstrap-port 4730 -U 5b9da517 --nodeconfig /Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/resources/node_config_3.json
 */

public class AppiumParallelTests {

	public AndroidDriver<MobileElement> driver;
	public static DesiredCapabilities cap;
	
	@BeforeTest()
	@Parameters({"port", "device", "platform_name", "platform_version"})
	public void setUp(String port, String device, String platform_name, String platform_version) throws MalformedURLException{
		File file = new File("/Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/resources/selendroid-test-app-0.17.0.apk");
		
		cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME , platform_name);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platform_version);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.APP, file);
		cap.setCapability("appPackage", "io.selendroid.testapp");
		cap.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
		
		URL url = new URL("http://localhost:"+port+"/wd/hub");
		
		driver = new AndroidDriver<MobileElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void loginTest() throws IOException{
		driver.findElementById("io.selendroid.testapp:id/my_text_field").sendKeys("Hello World!");

		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(file, new File("/Users/Khaja/Documents/SpotQA/SampleAppiumParallelTests/src/screenshots"));
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}

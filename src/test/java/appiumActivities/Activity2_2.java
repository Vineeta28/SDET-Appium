package appiumActivities;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Activity2_2 {
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;

	@BeforeClass
	public void GoogletaskPrep() throws InterruptedException, IOException {
	// Set the Desired Capabilities
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		 desiredCapabilities.setCapability("deviceId", "emulator-5554");
	     desiredCapabilities.setCapability("platformName", "android");
	     desiredCapabilities.setCapability("automationName", "UiAutomator2");
	     desiredCapabilities.setCapability("appPackage", "com.android.chrome");
	     desiredCapabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
	     desiredCapabilities.setCapability("noReset", true);
	     URL remoteUrl = new URL("http://localhost:4723/wd/hub");
	     driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test (priority = 0)

	public void ValidLogin() throws InterruptedException 
	{
	driver.get("https://www.training-support.net/selenium");
	driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Login Form\"))"));
	driver.findElementByXPath("//android.widget.TextView[@text='Login Form']").click();
	Thread.sleep(2000);
	driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin");
	driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password");
	driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
	String msg = driver.findElementByXPath("//android.view.View[@resource-id='action-confirmation']").getText();
	System.out.println(msg);
	Assert.assertEquals(msg, "Welcome Back, admin");
	}

	@Test (priority = 1)

	public void InValidLogin() throws InterruptedException 
	{
	driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").clear();
	driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("adminuser");
	driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").clear();
	driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password");
	driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
	String msgtwo = driver.findElementByXPath("//android.view.View[@resource-id='action-confirmation']").getText();
	System.out.println(msgtwo);
	Assert.assertEquals(msgtwo, "Invalid Credentials");
	}

	@AfterClass

	public void afterClass() {
	driver.quit();
	}

}

package appiumActivities;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Activity2_1 {
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
     desiredCapabilities.setCapability("noReset", "true");
     URL remoteUrl = new URL("http://localhost:4723/wd/hub");
     driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void GoogleChrome() throws InterruptedException 
	{
	driver.get("https://www.training-support.net/selenium");
	driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"To-Do List\"))"));
	driver.findElementByAccessibilityId("To-Do List Elements get added at run time").click();
	Thread.sleep(500);
	driver.findElementByClassName("android.widget.EditText").sendKeys("Task1");
	driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();
	driver.findElementByClassName("android.widget.EditText").sendKeys("Task2");
	driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();
	driver.findElementByClassName("android.widget.EditText").sendKeys("Task3");
	driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();
	driver.findElementByClassName("android.widget.EditText").sendKeys("Task4");
	driver.findElementByXPath("//android.widget.Button[@text='Add Task']").click();
	List<MobileElement> ListofTasks = driver.findElementsByXPath("//android.view.View/android.view.View/android.view.View/android.view.View");
	System.out.println("Number of tasks: "+ListofTasks.size());
	for (int i = 0; i < ListofTasks.size(); i++) 
	{
	ListofTasks.get(i).click();
	}
	String title = driver.findElementByClassName("android.widget.TextView").getText();
	System.out.println("Title: "+title);
	Assert.assertEquals(title, "To-Do List");
	}

}

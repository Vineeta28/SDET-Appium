package appiumActivities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1_1 {

	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceId", "emulator-5554");
		caps.setCapability("platformName", "android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage", "com.google.android.apps.tasks");
		caps.setCapability("appActivity", ".ui.TaskListsActivity");
		caps.setCapability("noReset", true);

		// Instantiate Appium Driver
		URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(appServer, caps);
	}

	@Test
	public void createTasks() {
		ArrayList<String> taskLists = new ArrayList<String>();
		taskLists.add("Complete Activity with Google Tasks");
		taskLists.add("Complete Activity with Google Keep");
		taskLists.add("Complete the second Activity Google Keep");
		for (String taskList : taskLists) {
			driver.findElementByAccessibilityId("Create new task").click();
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("add_task_title")));
			driver.findElementById("add_task_title").sendKeys(taskList);
			driver.findElementById("add_task_done").click();
			List<MobileElement> taskNames = driver.findElementsByXPath("//android.widget.TextView[@text='" + taskList + "']");
			for (MobileElement taskName : taskNames) {
				Assert.assertEquals(taskName.getText(), taskList);
			}
		}
	}

	@Test
	public void deleteTasks() {
		List<MobileElement> checkboxes = driver.findElementsById("tasks_item_completed_check");
		for (MobileElement checkbox : checkboxes) {
			checkbox.click();
		}
		driver.findElementById("action_more_options").click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("delete_all_completed_tasks_option")));
		driver.findElementById("delete_all_completed_tasks_option").click();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[@text='Delete']")));
		driver.findElementByXPath("//android.widget.Button[@text='Delete']").click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
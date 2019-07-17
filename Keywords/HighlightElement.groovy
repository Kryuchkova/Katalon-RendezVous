import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.keyword.internal.KeywordExecutor
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

public class HighlightElement {
	@Keyword
	public static void on(TestObject testObject, String path, String name) {
		influence(testObject, path, name)
	}

	private static void influence(TestObject testObject, String path, String name) {
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> elements = WebUiCommonHelper.findWebElements(testObject, 20);
			for (WebElement element : elements) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"arguments[0].setAttribute('style','outline: dashed red;');",
						element);
				def mydate = new Date()
				String fullPath =  path + name + '_' + mydate.format("dd_MM_yyyy") + '_' + mydate.format("HH_mm_ss") + ".png"
				println WebUI.takeScreenshot(fullPath)
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	private static List<String> influencedKeywords = ['click', 'sendKeys', 'getText', 'getAttribute']

	@Keyword
	public static void pandemic(String path, String type) {
		WebUI.metaClass.'static'.invokeMethod = { String name, args ->
			if (name in influencedKeywords) {
				TestObject to = (TestObject)args[0]
				HighlightElement.on(to, path, type)
			}
			def result
			try {
				result = delegate.metaClass.getMetaMethod(name, args).invoke(delegate, args)
			} catch(Exception e) {
				System.out.println("Handling exception for method $name")
			}
			return result
		}
	}
}

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('https://www.rendez-vous.ru/')

WebUI.maximizeWindow()

WebUI.waitForPageLoad(30)

WebUI.click(findTestObject('MainPage/btn_login'))

WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

WebUI.click(findTestObject('LoginPage/btn_entry'))

WebUI.waitForPageLoad(10)

WebUI.focus(findTestObject('UserPage/icon_user'))

email_user = WebUI.getText(findTestObject('UserPage/txt_email'))

email_form = WebUI.getText(findTestObject('UserPage/form_email'))

WebUI.verifyMatch(email_user, email_form, false)

WebUI.verifyElementVisible(findTestObject('UserPage/lbl_user'))

WebUI.click(findTestObject('UserPage/btn_exit'))

WebUI.closeBrowser()


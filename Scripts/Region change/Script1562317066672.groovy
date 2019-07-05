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

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран\r\n'
WebUI.maximizeWindow()

'Получение текущего региона'
old_city = WebUI.getText(findTestObject('MainPage/btn_region'))

'Нажатие на кнопку "Ваш регион"'
WebUI.click(findTestObject('MainPage/btn_apply_city'))

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Нажатие на кнопку "Ваш регион"'
WebUI.click(findTestObject('MainPage/btn_region'))

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Нажатие на поле выбора региона'
WebUI.click(findTestObject('RegionPage/btn_select_region'))

'Ввод названия города'
WebUI.sendKeys(findTestObject('RegionPage/input_region'), 'Самара')

'Подтверждение ввода'
WebUI.sendKeys(findTestObject('RegionPage/input_region'), Keys.chord(Keys.ENTER))

'Получение текущего региона'
new_city = WebUI.getText(findTestObject('MainPage/btn_region'))

'Сравнение прошлого и нового региона'
WebUI.verifyNotMatch(old_city, new_city, false)

'Нажатие на кнопку "Магазины"'
WebUI.click(findTestObject('MainPage/btn_shops'))

'Получение названия города'
shop_city = WebUI.getText(findTestObject('ShopPage/select_shop'))

'Сравнение текущего города и города, для которого составлен список магазинов'
WebUI.verifyMatch(new_city, shop_city, false)

'Закрытие браузера'
WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)


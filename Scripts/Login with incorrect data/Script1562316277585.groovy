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

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Вход в личный кабинет"'
WebUI.click(findTestObject('MainPage/btn_login'))

'Ввод неверного логина'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'tast_cesa')

'Ввод корректного пароля'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Нажатие на кнопку "Вход"'
WebUI.click(findTestObject('LoginPage/btn_entry'))

'Получение текст сообщения об ошибке'
error_msg = WebUI.getText(findTestObject('LoginPage/error-msg'))

'Проверка сообщения об ошибке'
WebUI.verifyMatch(error_msg, 'Аккаунт не существует', false)


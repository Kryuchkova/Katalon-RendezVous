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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Вход в личный кабинет"'
WebUI.click(findTestObject('MainPage/btn_login'))

'Заполнение поля логин'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Заполнение поля пароль'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Нажатие на кнопку вход'
WebUI.click(findTestObject('LoginPage/btn_entry'))

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Наведение курсора мыши на иконку личного кабинета'
WebUI.focus(findTestObject('UserPage/icon_user'))

'Получение логина пользователя'
email_user = WebUI.getText(findTestObject('UserPage/txt_email'))

WebUI.delay(2)

'Получение e-mail пользователя'
email_form = WebUI.getText(findTestObject('UserPage/form_email'))

'Сравнение логина и e-mail'
if (!WebUI.verifyMatch(email_user, email_form, false)){
	KeywordUtil.markFailed('ERROR: The Actual Login Does NOT Match the User Email')
}

'Проверка изменения иконки личного кабинета'
WebUI.verifyElementVisible(findTestObject('UserPage/lbl_user'))

'Выход из личного кабинета'
WebUI.click(findTestObject('UserPage/btn_exit'))

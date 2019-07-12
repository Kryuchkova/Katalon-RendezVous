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

'Переход к низу страницы'
WebUI.scrollToElement(findTestObject('MainPage/btn_gift_certificate'), 5)

'Переход в GooglePlay'
WebUI.click(findTestObject('MainPage/btn_googleplay'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_googleplay')).split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)

'Смена активной страницы'
WebUI.switchToWindowIndex(0)

'Переход в AppStore'
WebUI.click(findTestObject('MainPage/btn_appstore'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_appstore'))
text = text.split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)

'Смена активной страницы'
WebUI.switchToWindowIndex(0)

'Переход в Facebook'
WebUI.click(findTestObject('MainPage/btn_facebook'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_facebook'))
text = text.split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)

'Смена активной страницы'
WebUI.switchToWindowIndex(0)

'Переход в Vkontakte'
WebUI.click(findTestObject('MainPage/btn_vkontakte'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_vkontakte'))
text = text.split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)

'Смена активной страницы'
WebUI.switchToWindowIndex(0)

'Переход в Instagram'
WebUI.click(findTestObject('MainPage/btn_instagram'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_instagram'))
text = text.split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)

'Смена активной страницы'
WebUI.switchToWindowIndex(0)

'Переход в Youtube'
WebUI.click(findTestObject('MainPage/btn_youtube'))

'Смена активной страницы'
WebUI.switchToWindowIndex(1)

'Получение заголовка приложения'
text = WebUI.getText(findTestObject('LinksPage/lbl_youtube'))
text = text.split(' ')

'Соответствие приложения ссылке'
WebUI.verifyMatch(text[0], 'Rendez-Vous', false)

'Закрытие побочной страницы'
WebUI.closeWindowIndex(1)


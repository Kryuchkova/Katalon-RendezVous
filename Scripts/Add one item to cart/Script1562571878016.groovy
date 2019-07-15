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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_goods'))

'Наименование выбранного товара'
name_goods = WebUI.getText(findTestObject('GoodsPage/lbl_name'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Размер выбранной пары обуви'
size_goods = WebUI.getText(findTestObject('GoodsPage/lbl_size'))
WebUI.delay(3)

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))
WebUI.click(findTestObject('MainPage/btn_cart'))

'Количество товара в корзине'
count = WebUI.getAttribute(findTestObject('CartPage/lbl_count'), 'value')

'Наименование выбранного товара'
name = WebUI.getText(findTestObject('CartPage/lbl_name'))

'Размер выбранной пары обуви'
size = WebUI.getText(findTestObject('CartPage/lbl_size'))

'Соответствие выбранного товара и товара в корзине по названию'
if (!WebUI.verifyMatch(name, name_goods, false)){
	KeywordUtil.markFailed('ERROR: The Actual Name Does NOT Match the Expected Name')
}

'Соответствие выбранного товара и товара в корзине по размеру'
if (!WebUI.verifyMatch(size, size_goods, false)){
	KeywordUtil.markFailed('ERROR: The Actual Size Does NOT Match the Expected Size')
}

'Соответствие выбранного товара и товара в корзине по количеству'
if (!WebUI.verifyMatch(count, '1', false)){
	KeywordUtil.markFailed('ERROR: The Actual Count Does NOT Match the Expected Count')
}
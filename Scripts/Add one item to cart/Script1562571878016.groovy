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

'Разворачивание окна браузера на всь экран\r\n'
WebUI.maximizeWindow()

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_goods'))

'Наименование выбранного товара'
name_goods = WebUI.getText(findTestObject('GoodsPage/lbl_name'))

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/option_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Размер выбранной пары обуви'
size_goods = WebUI.getText(findTestObject('GoodsPage/lbl_size'))

'Наведение курсора на кнопку "Товар в корзине"'
WebUI.focus(findTestObject('GoodsPage/btn_add_to_cart'), FailureHandling.STOP_ON_FAILURE)

'Нажатие на кнопку "Товар в корзине"'
WebUI.doubleClick(findTestObject('GoodsPage/btn_add_to_cart'))

'Количество товара в корзине'
count = WebUI.getAttribute(findTestObject('CartPage/lbl_count'), 'value')

'Наименование товара в корзине'
name = WebUI.getText(findTestObject('CartPage/lbl_name'))

'Размер товара в корзине'
size = WebUI.getText(findTestObject('CartPage/lbl_size'))

'Соответствие выбранного товара и товара в корзине по названию'
WebUI.verifyMatch(name, name_goods, false)

'Соответствие выбранного товара и товара в корзине по размеру'
WebUI.verifyMatch(size, size_goods, false)

'Соответствие выбранного товара и товара в корзине по количеству'
WebUI.verifyMatch(count, '1', false)


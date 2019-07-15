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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на всь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Мужчинам"'
WebUI.click(findTestObject('MainPage/btn_male'))

'Нажатие на кнопку "Акссессуары"'
WebUI.click(findTestObject('MainPage/btn_accessories'))

'Выбор "тип"'
WebUI.click(findTestObject('GoodsListPage/btn_option_type'))

'Список "типов"'
List<WebElement> Types = WebUI.findWebElements(findTestObject('GoodsListPage/list_types'), 5)

for (i = 0; i < Types.size(); i++) {
    if (Types.get(i).getText().indexOf('Кошелёк') != -1) {
        'Выбор тип "Кошелёк"'
        Types.get(i).click()
        break
    }
}

'Выбор ценового диапазона'
WebUI.click(findTestObject('GoodsListPage/btn_option_price'))

'Установка нижней границы'
WebUI.sendKeys(findTestObject('GoodsListPage/input_from_price'), '3000')

'Установка верхней границы'
WebUI.sendKeys(findTestObject('GoodsListPage/input_to_price'), '13000')

'Применение фильтров'
WebUI.click(findTestObject('GoodsListPage/btn_apply'))
WebUI.delay(3)

'Сбор всех цен, названий и фильтров в массив'
'Цены товаров'
List<WebElement> Prices = WebUI.findWebElements(findTestObject('GoodsListPage/lbl_prices'), 5)
'Названия'
List<WebElement> Names = WebUI.findWebElements(findTestObject('GoodsListPage/lbl_names'), 5)
'Фильтры'
List<WebElement> Filters = WebUI.findWebElements(findTestObject('GoodsListPage/lbl_filters'), 5)
s = Filters.get(1).getText().replaceAll('\\s','').split("-")
println(s)
f_price = s[0].toInteger()
t_price = s[1].toInteger()
'Проверяем товары на соответствие фильтрам'
for (i = 0; i < Prices.size(); i++) {
	name = Names.get(i).getText().toUpperCase().split(" ")
	price = Prices.get(i).getText().replaceAll('\\s','').toInteger()
	
	if (!WebUI.verifyMatch(name[0], Filters.get(0).getText().toUpperCase(), false)){
		KeywordUtil.markFailed('ERROR: The Actual Type Does NOT MATCH the Expected Type')
	}
	if (!WebUI.verifyGreaterThanOrEqual(t_price, price)){
		KeywordUtil.markFailed('ERROR: The Actual Price Does NOT LESS THAN Or EQUAL the Upper Price Limit')
	}
	if (!WebUI.verifyLessThanOrEqual(f_price, price)){
		KeywordUtil.markFailed('ERROR: The Actual Price Does NOT GREATER THAN Or EQUAL the Lower Price Limit')
	}	
}



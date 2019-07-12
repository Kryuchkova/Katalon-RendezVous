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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement

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

'Нажатие на список возможных сортировок товаров'
WebUI.click(findTestObject('GoodsListPage/btn_sorting'))

'Выбор сортировки по скидке'
WebUI.click(findTestObject('GoodsListPage/option_sort_by_sale'), FailureHandling.STOP_ON_FAILURE)

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Количество страницы результата поиска'
num_pages = 20
// num_pages = WebUI.getText(findTestObject('SalePage/num_pages')).toInteger()

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Сбор всех скидок в массив'
List<WebElement> Sales = []
actual_sales = []
i = 0
while (i < num_pages) {
    'Скидки'
    Sales = WebUI.findWebElements(findTestObject('SalePage/lbl_sales'), 5)
	for (j = 0; j < Sales.size() - 1; j++) {
		num = Sales.get(j).getText().replaceAll('-', '')
		num = num.replaceAll('%', '').toInteger()		
		actual_sales.add(num)		
	}
	'Ожидание загрузки страницы'
	WebUI.delay(3)	
    WebUI.click(findTestObject('SalePage/btn_next'))
	i++
}

'Проверяем сортировку на корректность'
for (i = 0; i < actual_sales.size() - 1; i++) {
	WebUI.verifyGreaterThanOrEqual(actual_sales.get(i), actual_sales.get(i + 1))
}




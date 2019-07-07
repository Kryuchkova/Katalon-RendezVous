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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Нажатие на кнопку "Sale"'
WebUI.click(findTestObject('MainPage/btn_sale'))

'Выбор позиций с видеообзором'
WebUI.click(findTestObject('SalePage/checkbox_videoview'))

'Ожидание загрузки страницы'
WebUI.waitForPageLoad(10)

'Количество страницы результата поиска'
num_pages = WebUI.getText(findTestObject('SalePage/num_pages')).toInteger()

'Сбор всех цен и скидок в массив'
List<WebElement> Prices = []

List<WebElement> Sales = []

for (i = 0; i < num_pages; i++) {
    'Ожидание загрузки страницы'
    WebUI.waitForPageLoad(10)

    'Цены товаров'
    Prices = WebUI.findWebElements(findTestObject('SalePage/lbl_prices'), 5)

    'Скидки'
    Sales = WebUI.findWebElements(findTestObject('SalePage/lbl_sales'), 5)

    'Проверяем цены на корректность'
    for (i = 0; i < Sales.size(); i++) {
        sale = Sales.get(i).getText().replaceAll('-', '')

        sale = sale.replaceAll('%', '').toInteger()

        p_after = Prices.get(i).getText().replaceAll('\\s', '')

        p_before = Prices.get(i + 1).getText().replaceAll('\\s', '').toInteger()

        print('sale = ')

        println(sale)

        print('p_before = ')

        println(p_before)

        print('p_after = ')

        println(p_after)

        WebUI.verifyMatch(p_before * (1 - (sale / 100)).toInteger().toString(), p_after, false)
    }
    
    if (WebUI.waitForElementVisible(findTestObject('SalePage/btn_next'), 10)) {
        WebUI.click(findTestObject('SalePage/btn_next'))
    }
}

'Закрытие браузера'
WebUI.closeBrowser()


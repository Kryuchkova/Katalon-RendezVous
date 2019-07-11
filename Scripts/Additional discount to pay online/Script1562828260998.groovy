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

WebUI.openBrowser('https://www.rendez-vous.ru/')

WebUI.maximizeWindow()

WebUI.click(findTestObject('MainPage/btn_female'))

WebUI.click(findTestObject('MainPage/btn_shoes'))

WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

WebUI.click(findTestObject('GoodsPage/opt_size'))

WebUI.click(findTestObject('GoodsPage/option_first_size'))

WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

WebUI.click(findTestObject('MainPage/btn_female'))

WebUI.click(findTestObject('MainPage/btn_clothing'))

WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

WebUI.click(findTestObject('GoodsPage/opt_size'))

WebUI.click(findTestObject('GoodsPage/option_first_size'))

WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

WebUI.click(findTestObject('MainPage/btn_login'))

WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

WebUI.click(findTestObject('LoginPage/btn_entry'))

WebUI.delay(3)

WebUI.click(findTestObject('MainPage/btn_cart'))

WebUI.click(findTestObject('CartPage/btn_checkout'))

WebUI.click(findTestObject('CheckoutPage/btn_delivery_shop'))

WebUI.click(findTestObject('CheckoutPage/drop_list_cities'))

'Список магазинов'
List<WebElement> Shops = WebUI.findWebElements(findTestObject('CheckoutPage/list_cities'), 5)

for (i = 0; i < Shops.size(); i++) {
    if (Shops.get(i).getText().indexOf('Саратов') != -1) {
        'Выбор доставки в магазин'
        Shops.get(i).click()

        break
    }
}

WebUI.delay(3)

WebUI.click(findTestObject('CheckoutPage/btn_payment'))

cash_fprice = WebUI.getText(findTestObject('CheckoutPage/lbl_first_price')).replaceAll('\\s', '')

cash_disc = WebUI.getText(findTestObject('CheckoutPage/lbl_discount_amount')).replaceAll('\\s', '').toInteger()

cash_tprice = WebUI.getText(findTestObject('CheckoutPage/lbl_total_price')).replaceAll('\\s', '').toInteger()

WebUI.click(findTestObject('CheckoutPage/btn_cloudpay'))

cloud_fprice = WebUI.getText(findTestObject('CheckoutPage/lbl_first_price')).replaceAll('\\s', '')

cloud_disc = WebUI.getText(findTestObject('CheckoutPage/lbl_discount_amount')).replaceAll('\\s', '').toInteger()

cloud_tprice = WebUI.getText(findTestObject('CheckoutPage/lbl_total_price')).replaceAll('\\s', '').toInteger()

WebUI.verifyMatch(cash_fprice, cloud_fprice, false)

WebUI.verifyGreaterThan(cloud_disc, cash_disc)

WebUI.verifyGreaterThan(cash_tprice, cloud_tprice)

WebUI.verifyLessThan(Math.abs((cloud_disc - cash_disc) - Math.round(cash_tprice * 0.05)), 10 )


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

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Одежда"'
WebUI.click(findTestObject('MainPage/btn_clothing'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Вход в личный кабинет'
WebUI.click(findTestObject('MainPage/btn_login'))

'Ввод логина'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Ввод пароля'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Вход в личный кабинет'
WebUI.click(findTestObject('LoginPage/btn_entry'))
WebUI.delay(3)

'Переход в корзину'
WebUI.click(findTestObject('MainPage/btn_cart'))

'Нажатие на кнопку "Оформить заказ"'
WebUI.click(findTestObject('CartPage/btn_checkout'))

'Выбор доставки в магазин'
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

'Выбор оплаты "при получении"'
WebUI.click(findTestObject('CheckoutPage/btn_payment'))

'Получение первоначальной стоимости товара'
cash_fprice = WebUI.getText(findTestObject('CheckoutPage/lbl_first_price')).replaceAll('\\s', '')

'Получение скидки'
cash_disc = WebUI.getText(findTestObject('CheckoutPage/lbl_discount_amount')).replaceAll('\\s', '').toInteger()

'Получение итоговой стомости товара'
cash_tprice = WebUI.getText(findTestObject('CheckoutPage/lbl_total_price')).replaceAll('\\s', '').toInteger()

'Выбор оплаты "банковской картой"'
WebUI.click(findTestObject('CheckoutPage/btn_cloudpay'))

'Получение первоначальной стоимости товара'
cloud_fprice = WebUI.getText(findTestObject('CheckoutPage/lbl_first_price')).replaceAll('\\s', '')

'Получение скидки'
cloud_disc = WebUI.getText(findTestObject('CheckoutPage/lbl_discount_amount')).replaceAll('\\s', '').toInteger()

'Получение итоговой стомости товара'
cloud_tprice = WebUI.getText(findTestObject('CheckoutPage/lbl_total_price')).replaceAll('\\s', '').toInteger()

'Сравнение первоначальной стоимости товара'
if (!WebUI.verifyMatch(cash_fprice, cloud_fprice, false)){
	KeywordUtil.markFailed('ERROR: The Actual Price Does NOT Match the Expected Price')
}

'Сравнение скидок на товар'
if (!WebUI.verifyGreaterThan(cloud_disc, cash_disc)){
	KeywordUtil.markFailed('ERROR: The Actual Card Sale Does NOT GREATER THAN the Expected Cash Sale')
}

'Сравнение итоговой стоимости товара'
if (!WebUI.verifyGreaterThan(cash_tprice, cloud_tprice)){
	KeywordUtil.markFailed('ERROR: The Actual Total Price Does NOT Match the Expected Total Price')
}

'Проверка размера скидки при оплате онлайн'
if (!WebUI.verifyLessThan(Math.abs((cloud_disc - cash_disc) - Math.round(cash_tprice * 0.05)), 10)){
	KeywordUtil.markFailed('ERROR: The Actual Sale Does NOT Match the Expected 5% Sale')
}


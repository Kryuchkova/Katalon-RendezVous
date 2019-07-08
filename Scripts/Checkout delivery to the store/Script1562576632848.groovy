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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на всь экран'
WebUI.maximizeWindow()

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('FemaleShoesPage/first_goods'))

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/option_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Наведение курсора на кнопку "Товар в корзине"'
WebUI.focus(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на кнопку "Товар в корзине"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на кнопку "Оформить заказ"'
WebUI.click(findTestObject('CartPage/btn_checkout'))

'Ввод логина'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Ввод пароля'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Вход в личный кабинет'
WebUI.click(findTestObject('LoginPage/btn_entry'))

WebUI.waitForPageLoad(2)

WebUI.focus(findTestObject('CartPage/btn_checkout'))

WebUI.doubleClick(findTestObject('CartPage/btn_checkout'))

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

'Выбор оплаты при получении'
WebUI.click(findTestObject('CheckoutPage/btn_payment'))

'Кнопка оформить заказ доступна для нажатия'
WebUI.verifyElementClickable(findTestObject('CheckoutPage/btn_order_confirm'))


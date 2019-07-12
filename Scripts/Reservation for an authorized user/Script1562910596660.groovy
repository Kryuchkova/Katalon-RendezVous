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

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Вход в личный кабинет'
WebUI.click(findTestObject('MainPage/btn_login'))

'Ввод логина'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Ввод пароля'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Нажатие на кнопку "Вход"'
WebUI.click(findTestObject('LoginPage/btn_entry'))

WebUI.delay(5)

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Установка фильра по гроду "Саратов"'
WebUI.click(findTestObject('GoodsListPage/btn_opiton_city'))

WebUI.click(findTestObject('GoodsListPage/drop_list_cities'))

'Список городов'
List<WebElement> Cities = WebUI.findWebElements(findTestObject('GoodsListPage/list_cities'), 5)

print(Cities)

for (i = 0; i < Cities.size(); i++) {
    if (Cities.get(i).getText().indexOf('Челябинск') != -1) {
        'Выбор города Челябинска'
        Cities.get(i).click()
        break
    }
}

'Установка фильтра магазинов'
WebUI.click(findTestObject('GoodsListPage/btn_option_shop'))

WebUI.click(findTestObject('GoodsListPage/checkbox_all_shops'))

'Применение выставленных фильтров к поиску'
WebUI.click(findTestObject('GoodsListPage/btn_apply'))

WebUI.delay(3)

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Нажатие на кнопку "Забронировать в магазие"'
WebUI.click(findTestObject('GoodsPage/btn_booking'))

'Выбор магазина'
WebUI.click(findTestObject('BookingPage/radiobtn_shop_option'))

'Кнопка "Отправить бронь" доступна'
WebUI.verifyElementClickable(findTestObject('BookingPage/btn_submit'))


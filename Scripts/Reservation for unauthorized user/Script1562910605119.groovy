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
    if (Cities.get(i).getText().indexOf('Саратов') != -1) {
        'Выбор города Саратов'
        Cities.get(i).click()
        break
    }
}

'Установка фильтра магазинов'
WebUI.click(findTestObject('GoodsListPage/btn_option_shop'))

WebUI.click(findTestObject('GoodsListPage/checkbox_all_shops'))

'Применение выставленных фильтров к поиску'
WebUI.click(findTestObject('GoodsListPage/btn_apply'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

'Нажатие на кнопку "Забронировать в магазине"'
WebUI.click(findTestObject('GoodsPage/btn_booking'))

'Получение сообщения пользователю'
message = WebUI.getText(findTestObject('GoodsPage/lbl_note'))

'Сравнение сообщений'
WebUI.verifyMatch(message, 'Авторизуйтесь, пожалуйста, на сайте для бронирования товара.', false)

'Закрытие сообщения'
WebUI.click(findTestObject('GoodsPage/btn_exit'))


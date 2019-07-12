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

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Мужчинам"'
WebUI.click(findTestObject('MainPage/btn_male'))

'Нажатие на кнопку "Сумки"'
WebUI.click(findTestObject('MainPage/btn_bags'))

'Выбор "тип"'
WebUI.click(findTestObject('GoodsListPage/btn_option_type'))

'Список "типов"'
List<WebElement> Types = WebUI.findWebElements(findTestObject('GoodsListPage/list_types'), 5)
for (i = 0; i < Types.size(); i++) {
    if (Types.get(i).getText().indexOf('Портфель') != -1) {
        'Выбор тип "Портфель"'
        Types.get(i).click()
        break
    }
}

'Выбор "стиля"'
WebUI.click(findTestObject('GoodsListPage/btn_option_style'))

'Список "стилей"'
List<WebElement> Styles = WebUI.findWebElements(findTestObject('GoodsListPage/list_types'), 5)
for (i = 0; i < Styles.size(); i++) {
    if (Styles.get(i).getText().indexOf('Casual') != -1) {
        'Выбор стиля "Casual"'
        Styles.get(i).click()
        break
    }
}

WebUI.delay(3)

'Применение фильтров'
WebUI.click(findTestObject('GoodsListPage/btn_apply'))
WebUI.delay(3)

'Фильтры'
List<WebElement> Filters = WebUI.findWebElements(findTestObject('GoodsListPage/lbl_men_filters'), 5)
type = Filters.get(0).getText().toUpperCase()
option_style = Filters.get(1).getText().toUpperCase()

'Выбор первого товара из ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))
WebUI.delay(3)

'Проверяем товар на соответствие выбранному типу и стилю'
name = WebUI.getText(findTestObject('GoodsPage/lbl_name')).toUpperCase().split(' ')

'Соответствие типа товара фильтру'
WebUI.verifyMatch(name[1], type, false)

'Получение "стиля" товара'
style = WebUI.getText(findTestObject('GoodsPage/lbl_style')).toUpperCase()

'Соответствие стиля товара фильтру'
WebUI.verifyMatch(style, option_style, false)

'Нажатие на опцию "Заказ в один клик"'
WebUI.click(findTestObject('GoodsPage/btn_order_by_click'))

'Ввод имени'
WebUI.sendKeys(findTestObject('OneClickPage/input_name'), 'qwerty')

'Ввод телефона'
WebUI.sendKeys(findTestObject('OneClickPage/input_phone'), '1234567890')

'Нажатие на список магазинов'
WebUI.click(findTestObject('OneClickPage/drop_shop_list'))
WebUI.delay(2)

'Выбор первого доступного магазина'
WebUI.click(findTestObject('OneClickPage/first_shop'))

'Доступность кнопки "Заказать" '
WebUI.verifyElementClickable(findTestObject('OneClickPage/btn_order'))


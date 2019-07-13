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
import org.apache.commons.lang.ArrayUtils as ArrayUtils
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Вход в личный кабинет"'
WebUI.click(findTestObject('MainPage/btn_login'))

'Заполнение поля логин'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Заполнение поля пароль'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Нажатие на кнопку вход'
WebUI.click(findTestObject('LoginPage/btn_entry'))

WebUI.delay(3)

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_result_goods'))

'Нажатие на кнопку "Добавить в избранное"'
WebUI.click(findTestObject('GoodsPage/btn_favorite'))

'Получение текста сообщения'
msg = WebUI.getText(findTestObject('GoodsPage/lbl_note'))

'Корректность сообщения'
WebUI.verifyMatch(msg, 'Выберите, пожалуйста, размер для добавления товара в "Избранное".', false)

'Закрытие сообщения'
WebUI.click(findTestObject('GoodsPage/btn_exit'))

WebUI.delay(3)

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

WebUI.scrollToPosition(0, 0)

'Нажатие на кнопку "Добавить в избранное"'
WebUI.click(findTestObject('GoodsPage/btn_favorite'))

'Получение кода модели'
code = WebUI.getText(findTestObject('GoodsPage/lbl_code')).toUpperCase().replaceAll('\\s', '')

items = code.split(',')

'Нажатие на кнопку "Избранное"'
WebUI.click(findTestObject('MainPage/btn_favorite'))

'Получение кода модели'
model = WebUI.getText(findTestObject('FavoritePage/lbl_code')).toUpperCase().replaceAll('\\s', '')

words = model.split(',')

println(words)

ArrayUtils.reverse(words)

println(items)

println(words)

for (i = 0; i < words.size(); i++) {
    'Соответствие моделей в "Избранном"'
    WebUI.verifyMatch(items[i], words[i], false)
}

'Удаление товара из раздела "Избранное"'
WebUI.click(findTestObject('FavoritePage/btn_delete'))

'Получение сообщения о пустом разделе "Избранное"'
msg = WebUI.getText(findTestObject('FavoritePage/lbl_note'))

'Корректность сообщения'
if (!WebUI.verifyMatch(msg, 'У Вас нет товаров в Избранном', false)){
	KeywordUtil.markFailed('ERROR: The Messege Result Does NOT Match the Expected MSG Results')
}


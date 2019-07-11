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

'Открытие браузера'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'Разворачивание окна браузера на всь экран'
WebUI.maximizeWindow()

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

'Получение текста сообщения'
msg = WebUI.getText(findTestObject('GoodsPage/lbl_note'))

'Корректность сообщения'
WebUI.verifyMatch(msg, 'Авторизуйтесь, чтобы добавить товар в Избранное', false)

'Закрытие сообщения'
WebUI.click(findTestObject('GoodsPage/btn_exit'))


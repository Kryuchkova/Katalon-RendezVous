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

'Разворачивание окна браузера на весь экран'
WebUI.maximizeWindow()

'Добавление скриншотов и подсветки элементов'
CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'Нажатие на кнопку "Женщинам"'
WebUI.click(findTestObject('MainPage/btn_female'))

'Нажатие на кнопку "Обувь"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'Нажатие на первый товар ленты'
WebUI.click(findTestObject('GoodsListPage/first_goods'))

'Нажатие на список размеров'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'Выбор первого из списка размера'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'Нажатие на кнопку "Добавить в корзину"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

'Нажатие на иконку "Корзины"'
WebUI.click(findTestObject('MainPage/btn_cart'))

'Нажатие на кнопку "Вход в личный кабинет"'
WebUI.click(findTestObject('MainPage/btn_login'))

'Заполнение поля логин'
WebUI.sendKeys(findTestObject('LoginPage/input_email'), 'test-case@inbox.ru')

'Заполнение поля пароль'
WebUI.sendKeys(findTestObject('LoginPage/input_password'), 'test-case')

'Нажатие на кнопку вход'
WebUI.click(findTestObject('LoginPage/btn_entry'))

'Получение текущей цены товара'
goods_new_price = WebUI.getText(findTestObject('CartPage/lbl_new_price')).replaceAll('\\s', '')

'Получение цены товара без скидки'
goods_old_price = WebUI.getText(findTestObject('CartPage/lbl_old_price')).replaceAll('\\s', '')
WebUI.delay(3)

'Получение скидки на товар'
goods_sale = WebUI.getText(findTestObject('CartPage/lbl_sale')).replaceAll('-', '')
goods_sale = goods_sale.replace('(', '')
goods_sale = goods_sale.replace(')', '')
goods_sale = goods_sale.replace('%', '').toInteger()

'Итоговая стоимость товара в чеке'
check_new_price = WebUI.getText(findTestObject('CartPage/lbl_check_new_price')).replaceAll('\\s', '')

'Изначальная стоимость товара в чеке'
check_old_price = WebUI.getText(findTestObject('CartPage/lbl_check_old_price')).replaceAll('\\s', '')

'Скидка клиента на товар в рублях'
check_sale = WebUI.getText(findTestObject('CartPage/lbl_check_sale_amount')).replaceAll('\\s', '')

'Сравнение итоговой стоимости товара и его цены'
WebUI.verifyMatch(check_new_price, goods_new_price, false)

'Сравнение итоговой стоимости товара без скидки и его цены до скидки'
WebUI.verifyMatch(check_old_price, goods_old_price, false)

'Сравнение скидок на товар в рублях'
WebUI.verifyMatch((goods_old_price.toInteger() - Math.round(goods_old_price.toInteger() * (1 - goods_sale / 100))).toString(), check_sale, false)


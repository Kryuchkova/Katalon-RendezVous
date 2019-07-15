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

'Переход к низу страницы'
WebUI.scrollToElement(findTestObject('MainPage/btn_gift_certificate'), 5)

'Переход в раздел "Электронный сертификат"'
WebUI.click(findTestObject('MainPage/btn_gift_certificate'))
WebUI.delay(3)

'Выбор "Номинала"'
WebUI.click(findTestObject('GiftCertificatePage/drop_nominal'))

'Список "Номиналов"'
List<WebElement> Nominals = WebUI.findWebElements(findTestObject('GiftCertificatePage/list_nominal'), 5)

for (i = 0; i < Nominals.size(); i++) {
    if (Nominals.get(i).getText().indexOf('5 000 руб.') != -1) {
        'Выбор номинала "5 000 руб."'
        Nominals.get(i).click()
        break
    }
}

'Ввод имени'
WebUI.sendKeys(findTestObject('GiftCertificatePage/input_name'), 'qwerty')

'Ввод email'
WebUI.sendKeys(findTestObject('GiftCertificatePage/input_email'), 'qwerty@list.ru')

'Согласие с правилами'
WebUI.click(findTestObject('GiftCertificatePage/checkbox_rule'))

'Кнопка "Оплатить" доступна'
if (!WebUI.verifyElementClickable(findTestObject('GiftCertificatePage/btn_pay'))){
	KeywordUtil.markFailed('ERROR: The Button "Оплатить" Does NOT CLICKABLE')
}

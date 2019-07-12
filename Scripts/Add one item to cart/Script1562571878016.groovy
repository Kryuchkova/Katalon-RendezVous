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

'�������� ��������'
WebUI.openBrowser('https://www.rendez-vous.ru/')

'�������������� ���� �������� �� ��� �����\r\n'
WebUI.maximizeWindow()

CustomKeywords.'HighlightElement.pandemic'(GlobalVariable.G_Path, GlobalVariable.G_Name)

'������� �� ������ "��������"'
WebUI.click(findTestObject('MainPage/btn_female'))

'������� �� ������ "�����"'
WebUI.click(findTestObject('MainPage/btn_shoes'))

'������� �� ������ ����� �����'
WebUI.click(findTestObject('GoodsListPage/first_goods'))

'������������ ���������� ������'
name_goods = WebUI.getText(findTestObject('GoodsPage/lbl_name'))

'������� �� ������ ��������'
WebUI.click(findTestObject('GoodsPage/opt_size'))

'����� ������� �� ������ �������'
WebUI.click(findTestObject('GoodsPage/option_first_size'))

'������ ��������� ���� �����'
size_goods = WebUI.getText(findTestObject('GoodsPage/lbl_size'))

WebUI.delay(3)

'������� �� ������ "����� � �������"'
WebUI.click(findTestObject('GoodsPage/btn_add_to_cart'))

WebUI.click(findTestObject('MainPage/btn_cart'))

'���������� ������ � �������'
count = WebUI.getAttribute(findTestObject('CartPage/lbl_count'), 'value')

'������������ ������ � �������'
name = WebUI.getText(findTestObject('CartPage/lbl_name'))

'������ ������ � �������'
size = WebUI.getText(findTestObject('CartPage/lbl_size'))

'������������ ���������� ������ � ������ � ������� �� ��������'
WebUI.verifyMatch(name, name_goods, false)

'������������ ���������� ������ � ������ � ������� �� �������'
WebUI.verifyMatch(size, size_goods, false)

'������������ ���������� ������ � ������ � ������� �� ����������'
WebUI.verifyMatch(count, '1', false)

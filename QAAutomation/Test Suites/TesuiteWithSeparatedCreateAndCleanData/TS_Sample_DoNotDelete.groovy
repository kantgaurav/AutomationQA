import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase

import configs.PageLocatorName
import core.Browser
import internal.GlobalVariable
import pages.LoginPageSteps
import utils.DataUtil as DataUtil

// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Clean test suites environment.
 */
// Please change skipped to be false to activate this method.
/**
 * Run before each test case starts.
 */
// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Run after each test case ends.
 */
// Please change skipped to be false to activate this method.
// Put your code here.

@SetUp(skipped = false)
def setUp() {
    'Step 1:'
    'Enter the Application URL in browser.'
    Browser.start(GlobalVariable.URL)
	LoginPageSteps.getBaseSteps().waitForControlDisplay("Email_Textbox", PageLocatorName.LOGIN_PAGE)

    'Step 2:'
    'Enter the Username and Password and Click on Login button.'
    LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, 'Automation', 'System Administrator')
	LoginPageSteps.getBaseSteps().waitForControlDisplay("User_Image", PageLocatorName.HEADER_PAGE);
	
	'Step 3: Prepared data'
	DataUtil.createBasedData()
	
	'Step 4 :Log out:'
	Browser.quitDriver()
}

@TearDown(skipped = false)
def tearDown() {
    'Step 1:'
    'Enter the Application URL in browser.'
    Browser.start(GlobalVariable.URL)
	LoginPageSteps.getBaseSteps().waitForControlDisplay("Email_Textbox", PageLocatorName.LOGIN_PAGE)

    'Step 2:'
    'Enter the Username and Password and Click on Login button.'
    LoginPageSteps.login(GlobalVariable.SUPER_USER, GlobalVariable.SUPER_PW, 'Automation', 'System Administrator')
	LoginPageSteps.getBaseSteps().waitForControlDisplay("User_Image", PageLocatorName.HEADER_PAGE);

    'Step 3: Prepared data'
    DataUtil.deleteBasedData()

    'Step 4 :Log out:'
    Browser.quitDriver()
}

@SetupTestCase(skipped = true)
def setupTestCase() {
}

@TearDownTestCase(skipped = true)
def tearDownTestCase() {
}

/**
 * References:
 * Groovy tutorial page: http://docs.groovy-lang.org/next/html/documentation/
 */
package configs

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CMSStrings {
	public static final String NOT_DATA_AVAILABLE_IN_TABLE = "No data available in table";

	//table
	public static final String DELETE_BUTTON_IN_TABLE = "btn-outline-danger";
	public static final String EDIT_BUTTON_IN_TABLE = "editbutton";



	//Page Name
	//Org Admin
	public static final String REGION_PAGE_TITLE = "Region"
	public static final String LOCATION_PAGE_TITLE = "Location"
	public static final String COST_CENTER_PAGE_TITLE = "Cost Center"
	public static final String SPECIALTY_PAGE_TITLE = "Specialty"
	public static final String POSITION_PAGE_TITLE = "Position"
	public static final String PROVIDER_PAGE_TITLE = "Providers"
	public static final String PAY_ELEMENTS_PAGE_TITLE = "Pay Elements"

	//CAR
	public static final String CAR_APPROVAL_SETUP_PAGE_TITLE = "Configured Approvals"
	public static final String CAR_TEMPLATES_PAGE_TITLE = "CAR Templates"

	//Contract Document
	public static final String EXECUTE_CONTRACT_PAGE_TITLE = "Execute Contract"

	//REPORT
	public static final String CAR_STATUS_REPORT_PAGE_TITLE = "CAR Status Report"
	public static final String CONTRACT_STATUS_PAGE_TITLE = "Contract Status"
	public static final String ACTIVITY_LOG_PAGE_TITLE = "My Activity Log"
	public static final String UPCOMING_RENEWALS_PAGE_TITLE = "Upcoming Renewals"
	public static final String CAR_DATA_REPORT_PAGE_TITLE = "CAR Data Report"
}

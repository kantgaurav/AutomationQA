package pages

import com.google.common.base.Strings

import configs.CMSStrings
import configs.PageLocatorName
import core.AssertSteps
import utils.ExcelUtil
import utils.Utilities
import utils.DateTimeUtil

public class CARTemplatesPageSteps extends CommonPageSteps{

	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyPageTitleIsCorrect(CMSStrings.CAR_TEMPLATES_PAGE_TITLE)
	}


	public static String createCarTemplate(String regionName, String locationName, String costCenter, String specialtyName, String positionsName) {

		String number = Utilities.generateRandomNumber()
		String templateName = String.format("template_%s",number)

		getBaseSteps().clickToControl("Create_CAR_Template_Button",PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateName,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialtyName,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		addFieldTemplate("User Info")

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARTEMPLATES_PAGE)

		return templateName
	}

	public static String createCarTemplateWithTemplateMedical1(String regionName, String locationName, String costCenter, String specialtyName, String positionsName) {

		String number = Utilities.generateRandomNumber()
		String templateName = String.format("template_%s",number)

		getBaseSteps().clickToControl("Create_CAR_Template_Button",PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateName,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialtyName,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		addFieldTemplateMedical1("User Info")

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARTEMPLATES_PAGE)

		return templateName
	}

	public static String createCarTemplateWithTemplateMedical2(String regionName, String locationName, String costCenter, String specialtyName, String positionsName) {

		String number = Utilities.generateRandomNumber()
		String templateName = String.format("template_%s",number)

		getBaseSteps().clickToControl("Create_CAR_Template_Button",PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateName,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialtyName,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		addFieldTemplateMedical2("User Info")

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARTEMPLATES_PAGE)

		return templateName
	}

	public static String createCarTemplateForCAR(String regionName, String locationName, String costCenter, String specialtyName, String positionsName) {

		String number = Utilities.generateRandomNumber()
		String templateName = String.format("Template_%s",number)

		getBaseSteps().clickToControl("Create_CAR_Template_Button",PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateName,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialtyName,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		addFieldTemplateForCAR("User Info")

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARTEMPLATES_PAGE)

		return templateName
	}

	//default template
	private static void addFieldTemplate(String sectionName) {
		int quantityControl = 8;
		int i = 1;
		getBaseSteps().clickToControl("Add_Section_Button", PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Section_Name_Textbox",sectionName,PageLocatorName.CARTEMPLATES_PAGE)

		while(i<=quantityControl) {
			getBaseSteps().clickToControl("Add_Field_Button", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByIndexInComboBoxUsingAction("Edit_Field_Select_Field_Type_Combobox", i, PageLocatorName.CARTEMPLATES_PAGE)
			String fieldType = getBaseSteps().getSelectedOptionInComboBox("Edit_Field_Select_Field_Type_Combobox", true,true,PageLocatorName.CARTEMPLATES_PAGE)


			getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)

			if(fieldType=="Dropdown") {
				getBaseSteps().clickToControlByText("System List")
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Subsidy Type", PageLocatorName.CARTEMPLATES_PAGE)
			}
			if(fieldType=="Radio" || fieldType=="Checkbox") {
				getBaseSteps().setTextToControl("Edit_Field_First_Option",String.format("%s_Opt1",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_Last_Option",String.format("%s_Opt2",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
			}

			getBaseSteps().clickToControl("Modal_Content_Save_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			i++;
		}
	}

	//Baburao template 1
	//All Fields with Required: True
	//Include for all control
	private static void addFieldTemplateMedical1(String sectionName) {
		int quantityControl = 8; //option in the dropdown = 8
		int i = 1;
		int count = 1;

		getBaseSteps().clickToControl("Add_Section_Button", PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Section_Name_Textbox",sectionName,PageLocatorName.CARTEMPLATES_PAGE)

		while(i<=quantityControl) {

			getBaseSteps().clickToControl("Add_Field_Button", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByIndexInComboBoxUsingAction("Edit_Field_Select_Field_Type_Combobox", i, PageLocatorName.CARTEMPLATES_PAGE)
			String fieldType = getBaseSteps().getSelectedOptionInComboBox("Edit_Field_Select_Field_Type_Combobox", true,true,PageLocatorName.CARTEMPLATES_PAGE)

			if(fieldType=="Dropdown") {
				int number = i + (count-1);
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,number.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Help_Text_Textbox","Help Text",PageLocatorName.CARTEMPLATES_PAGE)

				getBaseSteps().clickToControlByText("System List")
				if(count == 1) {
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Compensation Models", PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count == 2) {
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Subsidy Type", PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count ==3){
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "US States", PageLocatorName.CARTEMPLATES_PAGE)
				}

				if(count <=3) {
					count++;
				}

				if(count == 4) {
					i=i
				}else {
					i--;
				}
			}else if(fieldType=="Radio" || fieldType=="Checkbox") {
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Help_Text_Textbox","Help Text",PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_First_Option",String.format("%s_Opt1",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_Last_Option",String.format("%s_Opt2",fieldType),PageLocatorName.CARTEMPLATES_PAGE)

			}else if(fieldType=="Date Picker"){
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Help_Text_Textbox","Help Text",PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Default_Date_Textbox","6/25/1986",PageLocatorName.CARTEMPLATES_PAGE)

			}else if(fieldType=="Currency"){
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Help_Text_Textbox","Help Text",PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Default_Value_Currency_Textbox","100,000",PageLocatorName.CARTEMPLATES_PAGE)

			}else{
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Help_Text_Textbox","Help Text",PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Default_Value_Textbox","Default Value",PageLocatorName.CARTEMPLATES_PAGE)
			}

			getBaseSteps().clickToControl("Modal_Content_Save_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			i++;
		}
	}

	//Baburao template 2
	//All Fields with Required: false, except the second Dropdown = true
	//Include for all control
	private static void addFieldTemplateMedical2(String sectionName) {
		int quantityControl = 8; //option in the dropdown = 8
		int i = 1;
		int count = 1;

		getBaseSteps().clickToControl("Add_Section_Button", PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Section_Name_Textbox",sectionName,PageLocatorName.CARTEMPLATES_PAGE)

		while(i<=quantityControl) {
			getBaseSteps().clickToControl("Add_Field_Button", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByIndexInComboBoxUsingAction("Edit_Field_Select_Field_Type_Combobox", i, PageLocatorName.CARTEMPLATES_PAGE)
			String fieldType = getBaseSteps().getSelectedOptionInComboBox("Edit_Field_Select_Field_Type_Combobox", true,true,PageLocatorName.CARTEMPLATES_PAGE)

			if(fieldType=="Dropdown") {
				int number = i + (count-1);
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,number.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControlByText("System List")
				if(count == 1) {
					getBaseSteps().clickToControl("User_Defined_List_button", PageLocatorName.CARTEMPLATES_PAGE)
					getBaseSteps().setTextToControl("User_Define_Textbox","User defined 1",PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count == 2) {
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Compensation Models", PageLocatorName.CARTEMPLATES_PAGE)

				}else if(count == 3){
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Subsidy Type", PageLocatorName.CARTEMPLATES_PAGE)
					getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count == 4){
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "US States", PageLocatorName.CARTEMPLATES_PAGE)
				}

				if(count <=4) {
					count++;
				}

				if(count == 5) {
					i=i
				}else {
					i--;
				}
			}else {
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Radio" || fieldType=="Checkbox") {
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)

				getBaseSteps().setTextToControl("Edit_Field_First_Option",String.format("%s_Opt1",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_Last_Option",String.format("%s_Opt2",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
			}

			getBaseSteps().clickToControl("Modal_Content_Save_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			i++;
		}
	}

	//Main template with all fields
	//Missing Dropdown for 'Compensation Models', all Field_Required = True
	//Create for CreateCAR TC
	private static void addFieldTemplateForCAR(String sectionName) {
		int quantityControl = 8; //option in the dropdown = 8
		int i = 1;
		int count = 1;

		getBaseSteps().clickToControl("Add_Section_Button", PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Section_Name_Textbox",sectionName,PageLocatorName.CARTEMPLATES_PAGE)

		while(i<=quantityControl) {
			getBaseSteps().clickToControl("Add_Field_Button", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByIndexInComboBoxUsingAction("Edit_Field_Select_Field_Type_Combobox", i, PageLocatorName.CARTEMPLATES_PAGE)
			String fieldType = getBaseSteps().getSelectedOptionInComboBox("Edit_Field_Select_Field_Type_Combobox", true,true,PageLocatorName.CARTEMPLATES_PAGE)

			if(fieldType=="Dropdown") {
				int number = i + (count-1);
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,number.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControlByText("System List")
				if(count == 1) {
					getBaseSteps().clickToControl("User_Defined_List_button", PageLocatorName.CARTEMPLATES_PAGE)
					getBaseSteps().setTextToControl("User_Define_Textbox","User defined 1",PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count == 2){
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "Subsidy Type", PageLocatorName.CARTEMPLATES_PAGE)
				}else if(count == 3){
					getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "US States", PageLocatorName.CARTEMPLATES_PAGE)
				}

				if(count <=3) {
					count++;
				}

				if(count == 4) {
					i=i
				}else {
					i--;
				}
			}else {
				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Radio" || fieldType=="Checkbox") {
				//				getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
				//				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
				//				getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				//				getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_First_Option",String.format("%s_Opt1",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_Last_Option",String.format("%s_Opt2",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
			}

			getBaseSteps().clickToControl("Modal_Content_Save_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			i++;
		}
	}

	public static void verifyExcelFileContentOfCARTemplate(String filePath, String sheetIndex="0", String expectedContent){

		String excelContent = ExcelUtil.getContentFromFile(filePath, sheetIndex).trim().replaceAll("[\\t\\n\\r\\s]+"," ");
		expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The expected content is: %s. The excel file content is: %s", expectedContent, excelContent )
		if(Strings.isNullOrEmpty(expectedContent)) {
			expectedContent = '-'
			AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg);
		}else {
			AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg);
		}
	}


	//the method is used to add Field with Length Configuration
	//USING FOR TC: VerifyFieldConfigurationAndInformation
	private static void addFieldTemplateWithLengthOptions(String sectionName) {
		int quantityControl = 8;
		int i = 1;
		String minTextBox = "1"
		String maxTextBox = "10"
		String mintDate = DateTimeUtil.getDateTime();
		String maxDate = DateTimeUtil.nextDateTime(5);
		String minCurrency = "1"
		String maxCurrency = "1000"

		getBaseSteps().clickToControl("Add_Section_Button", PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Section_Name_Textbox",sectionName,PageLocatorName.CARTEMPLATES_PAGE)

		while(i<=quantityControl) {
			getBaseSteps().clickToControl("Add_Field_Button", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByIndexInComboBoxUsingAction("Edit_Field_Select_Field_Type_Combobox", i, PageLocatorName.CARTEMPLATES_PAGE)
			String fieldType = getBaseSteps().getSelectedOptionInComboBox("Edit_Field_Select_Field_Type_Combobox", true,true,PageLocatorName.CARTEMPLATES_PAGE)

			getBaseSteps().setTextToControl("Edit_Field_Name_Textbox",String.format("%s_0%s",fieldType,i.toString()),PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().clickToControl("Edit_Field_Required_Slider", PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Interface_Link_Combobox", "H2",true,true, PageLocatorName.CARTEMPLATES_PAGE)
			getBaseSteps().clickToControl("Edit_Field_Output_To_Term_Sheet_Report_Slider", PageLocatorName.CARTEMPLATES_PAGE)

			if(fieldType=="Dropdown") {
				getBaseSteps().clickToControlByText("System List")
				getBaseSteps().selectItemByTextInComboBoxUsingAction("Edit_Field_Data_Source_Combobox", "US States", PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Radio" || fieldType=="Checkbox") {
				getBaseSteps().setTextToControl("Edit_Field_First_Option",String.format("%s_Opt1",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Edit_Field_Last_Option",String.format("%s_Opt2",fieldType),PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Textbox (Text)" ||fieldType=="Textbox (Number)" || fieldType=="TextArea") {
				getBaseSteps().setTextToControl("Minimum_Length_Textbox",minTextBox,PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Maximum_Length_Textbox",maxTextBox,PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Date Picker") {
				getBaseSteps().setTextToControl("Default_Date_Textbox",mintDate,PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Minimum_Date_Textbox",mintDate,PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Maximum_Date_Textbox",maxDate,PageLocatorName.CARTEMPLATES_PAGE)
			}

			if(fieldType=="Currency") {
				getBaseSteps().setTextToControl("Minimum_Value_Currency_Textbox",minCurrency,PageLocatorName.CARTEMPLATES_PAGE)
				getBaseSteps().setTextToControl("Maximum_Value_Currency_Textbox",maxCurrency,PageLocatorName.CARTEMPLATES_PAGE)
			}

			getBaseSteps().clickToControl("Modal_Content_Save_Button", PageLocatorName.COMMONLOCATORS_PAGE)
			i++;
		}
	}

	//the CAR template with Length Field Configuration
	//USING FOR TC: VerifyFieldConfigurationAndInformation
	public static String createCarTemplateWithLengthOptions(String regionName, String locationName, String costCenter, String specialtyName, String positionsName) {

		String number = Utilities.generateRandomNumber()
		String templateName = String.format("template_%s",number)

		getBaseSteps().clickToControl("Create_CAR_Template_Button",PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().setTextToControl("Name_Textbox",templateName,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Region_Combobox", regionName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Location_Combobox", locationName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Cost_Center_Combobox", costCenter,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Specialty_Combobox", specialtyName,true,true, PageLocatorName.CARTEMPLATES_PAGE)
		getBaseSteps().selectItemByTextInComboBoxUsingAction("Position_Combobox", positionsName, true,true,PageLocatorName.CARTEMPLATES_PAGE)
		addFieldTemplateWithLengthOptions("User Info")

		getBaseSteps().clickToControl("Save_Button", PageLocatorName.CARTEMPLATES_PAGE)

		return templateName
	}



	//	public static String SearchCarTemplate(String CARTemplateName) {
	//		getBaseSteps().setTextToControl("CarTemplate_Search",CARTemplateName,PageLocatorName.CARTEMPLATES_PAGE)
	//		String columnText = getBaseSteps().getCellTableTextByRowAndColumnIndex("SearchTemplate_TableResult","1", "2", PageLocatorName.CARTEMPLATES_PAGE);
	//		AssertSteps.verifyExpectedResult(columnText.equals(CARTemplateName), "The '" + columnText + "' did not match expected value of '" + CARTemplateName + "'")
	//	}
	//
	//
	//	public static void DeleteCarTemplate(String CARTemplateName) {
	//		getBaseSteps().clickToControl("CarTemplate_Delete", PageLocatorName.CARTEMPLATES_PAGE)
	//		getBaseSteps().clickToControl("CarTemplate_DeleteConfirm", PageLocatorName.CARTEMPLATES_PAGE)
	//		getBaseSteps().waitForControlDisplay("NoRecordFound_Text", PageLocatorName.CARTEMPLATES_PAGE)
	//	}

}

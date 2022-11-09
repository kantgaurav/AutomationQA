package utils

import configs.Path
import core.Logger
import internal.GlobalVariable
import pages.CARApprovalSetupPageSteps
import pages.CostCenterPageSteps
import pages.LocationPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.ProvidersPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps

//this class supports for store data to properties file
public class TestSuiteDataManagementUtil {

	private static PropertyReader variable;
	private static String testsuiteName = GlobalVariable.TS_NAME

	public static PropertyReader getVariable() {
		if(variable!=null) {
			return variable;
		}else {
			def pathFile = PlatformUtil.createAFileInAFolder(Path.TESTSUITE_VARIABLE_PATH, testsuiteName.replace("Test Suites/", "").concat(".properties"))
			variable = new PropertyReader(pathFile)
			return variable
		}

	}

	public static void createBasedData() {
		def pathFile = PlatformUtil.createAFileInAFolder(Path.TESTSUITE_VARIABLE_PATH, testsuiteName.replace("Test Suites/", "").concat(".properties"))
		variable = new PropertyReader(pathFile)
		MenuPageSteps.selectOrganizationAdminMenu("Position")
		variable.position(PositionPageSteps.createPosition())
		MenuPageSteps.selectOrganizationAdminMenu("Specialty")
		variable.specialty(SpecialtyPageSteps.createSpecialty())
		MenuPageSteps.selectOrganizationAdminMenu("Region")
		variable.region(RegionPageSteps.createRegion())
		MenuPageSteps.selectOrganizationAdminMenu("Location")
		variable.location(LocationPageSteps.createLocation(variable.region))
		MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
		variable.costCenter(CostCenterPageSteps.createCostCenter(variable.region, variable.location))
		//		MenuPageSteps.selectOrganizationAdminMenu("Providers")
		//		variable.provider(ProvidersPageSteps.createProvider())
	}

	public static void deleteBasedData() {
		try {
			'Delete Cost Center'
			MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
			CostCenterPageSteps.searchAndDelete(variable.costCenter)
			'Delete Location'
			MenuPageSteps.selectOrganizationAdminMenu("Location")
			LocationPageSteps.searchAndDelete(variable.location)
			'Delete the created Position'
			MenuPageSteps.selectOrganizationAdminMenu("Position")
			PositionPageSteps.searchAndDelete(variable.position)
			'Delete the created Specialty'
			MenuPageSteps.selectOrganizationAdminMenu("Specialty")
			SpecialtyPageSteps.searchAndDelete(variable.specialty)
			'Delete the created region'
			MenuPageSteps.selectOrganizationAdminMenu("Region")
			RegionPageSteps.searchAndDelete(variable.region)

			//			'Delete the created providers'
			//			MenuPageSteps.selectOrganizationAdminMenu("Providers")
			//			ProvidersPageSteps.searchAndDelete(variable.provider)
		}catch(Exception e) {
			Logger.logDEBUG(String.format("Cannot delete data. Error %s",e.getMessage()))
			return ;
		}
	}

}

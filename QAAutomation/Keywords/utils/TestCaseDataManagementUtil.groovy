package utils

import core.Browser
import core.Logger
import internal.GlobalVariable
import pages.CostCenterPageSteps
import pages.LocationPageSteps
import pages.MenuPageSteps
import pages.PositionPageSteps
import pages.RegionPageSteps
import pages.SpecialtyPageSteps



public class TestCaseDataManagementUtil {
	
	public static cleanBaseData(String region, String specialty, String position, String location, String costCenter) {
		Browser.navigateToURL(GlobalVariable.URL)
		try {
			'Delete Cost Center'
			MenuPageSteps.selectOrganizationAdminMenu("Cost Center")
			CostCenterPageSteps.searchAndDelete(costCenter)
			'Delete Location'
			MenuPageSteps.selectOrganizationAdminMenu("Location")
			LocationPageSteps.searchAndDelete(location)
			'Delete the created Position'
			MenuPageSteps.selectOrganizationAdminMenu("Position")
			PositionPageSteps.searchAndDelete(position)
			'Delete the created Specialty'
			MenuPageSteps.selectOrganizationAdminMenu("Specialty")
			SpecialtyPageSteps.searchAndDelete(specialty)
			'Delete the created region'
			MenuPageSteps.selectOrganizationAdminMenu("Region")
			RegionPageSteps.searchAndDelete(region)

			//			'Delete the created providers'
			//			MenuPageSteps.selectOrganizationAdminMenu("Providers")
			//			ProvidersPageSteps.searchAndDelete(provider)
		}catch(Exception e) {
			Logger.logDEBUG(String.format("Cannot delete data. Error %s",e.getMessage()))
			return ;
		}

	}
	
}

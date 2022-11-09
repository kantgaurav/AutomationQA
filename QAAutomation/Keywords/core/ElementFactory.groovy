package core

import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.TrueFileFilter

import com.google.api.client.util.Strings

import configs.Path
import utils.PlatformUtil

public class ElementFactory {





	public static String loadObjectRepositoryFile(String controlName, String pageName) {

		if(!Strings.isNullOrEmpty(pageName)&&!Strings.isNullOrEmpty(controlName)){
			try {
				return 	PlatformUtil.getPropertiesValue(Path.LOCATOR_PATH, controlName, pageName);

			} catch(Throwable e){
				throw new HMException(String.format("Page Name is NOT founded: %s", pageName),e);
				return null;
			}
		}else{
			throw new HMException("PageName is null");
		}
	}

//	private static String loadFilesInAFolder(File folder, String pageName) {
//		String locatorFiles ="";
//		try {
//			Iterator it = FileUtils.iterateFilesAndDirs(folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//			while (it.hasNext()) {
//				locatorFiles = ((File) it.next()).getCanonicalPath();
//				if(locatorFiles.contains(pageName)){
//					return locatorFiles;
//				}
//			}
//		} catch (Throwable e) {
//			throw new HMException("PageName is null",e);
//		}
//	}
}
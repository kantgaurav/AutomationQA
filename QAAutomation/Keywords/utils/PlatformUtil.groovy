package utils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.TrueFileFilter
import org.openqa.selenium.Keys

import com.kms.katalon.core.configuration.RunConfiguration

import configs.FileExtension
import configs.Path
import configs.Timeout
import core.BaseControl
import core.HMException
import core.Logger
public class PlatformUtil {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static String getUserAccount(){
		return System.getProperty("user.name");
	}

	public static String getDownloadPath(){
		try{
			String user = getUserAccount();
			if(isMac()) {
				return "/Users/${user}/Downloads/";
			}
			if(isWindows()) {
				return "C:\\Users\\${user}\\Downloads\\";
			}
		}catch(HMException e){
			Logger.logERROR(String.format("Cannot get the Download path. Error: %s",e.getMessage()));
		}
	}
	public static String getFolderProjectPath(String folderName){
		if(isMac()) {
			return "${RunConfiguration.getProjectDir()}/".concat(folderName);
		}
		if(isWindows()) {
			return "${RunConfiguration.getProjectDir()}/".concat(folderName).replace('/', '\\')
		}
	}
	public static String getFileProjectPath(String fileName){
		return getFolderProjectPath(fileName);
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	/**
	 * This public method is used to check a file is download successfully or Not
	 * @param downloadPath: this is a directory path where store the download file
	 * @param fileName: this is a name of download file
	 * @return boolean: this returns a download status (true/false)
	 */
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		try{
			String fName = fileName.substring(0, fileName.indexOf('.'))
			File fullFilePath;
			Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_FEW_SECONDS);

			fullFilePath = new File(getFilePath(downloadPath, fileName));
			//			if(isMac()) {
			//				fullFilePath = new File(String.format("%s/%s",downloadPath,fileName));
			//			}else if(isWindows()) {
			//				fullFilePath = new File(String.format("%s\\%s",downloadPath,fileName));
			//			}


			//			if(!fullFilePath.exists()){
			//				Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_DOWNLOAD_FILE_SECONDS);
			//			}
			File directory = new File(downloadPath);
			Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_DOWNLOAD_FILE_SECONDS);
			File[] lstDirectoryFile = directory.listFiles();
			for(File file:lstDirectoryFile){
				if(file.getName().contains(fName)&& file.getName().matches(FileExtension.FILE_EXTENSION_PATTERN)){
					return true;
				}
			}
			return false;
		}catch(HMException e){
			Logger.logERROR(String.format("File %s is NOT found at %s. Error: %s",fileName,downloadPath, e.getMessage()));
		}
	}

	public static String getFullFileNameInFolderByAPartOfName(String folderPath, String aPartOfFileName) {
		try {
			aPartOfFileName = aPartOfFileName.substring(0, aPartOfFileName.indexOf('.'))
		}catch(StringIndexOutOfBoundsException e) {
			Logger.logERROR(String.format("File does not include extension. Err:%s",e.getMessage()))
		}
		File directory = new File(folderPath);
		File[] lstDirectoryFile = directory.listFiles();
		for(File file:lstDirectoryFile){
			if(file.getName().contains(aPartOfFileName)&& file.getName().matches(FileExtension.FILE_EXTENSION_PATTERN)){
				return file.getName();
			}
		}
		return "";
	}


	public static void deleteAllFilesWithPrefix(String folderPath, String prefixFileName){
		try{
			File directory = new File(folderPath);
			for (File f: directory.listFiles()) {
				if (f.getName().startsWith(prefixFileName)) {
					f.delete();
				}
			}
		}catch(HMException e){
			Logger.logERROR(String.format("Cannot delete files in %s: Error: %s",folderPath, e.getMessage()));
		}
	}

	public static String getFilePath(String folderPath, String fileName) {
		String filePath="";
		if(isMac()) {
			filePath = String.format("%s%s",folderPath,fileName);
		}else if(isWindows()) {
			filePath = String.format("%s%s",folderPath,fileName);
		}
		return filePath;
	}


	public static String loadFilesInAFolder(File folder, String pageName) {
		String locatorFiles ="";
		try {
			Iterator it = FileUtils.iterateFilesAndDirs(folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
			while (it.hasNext()) {
				locatorFiles = ((File) it.next()).getCanonicalPath();
				if(locatorFiles.contains(pageName)){
					return locatorFiles;
				}
			}
		} catch (Throwable e) {
			throw new HMException("PageName is null",e);
		}
	}

	public static String getPropertiesValue(String directoryPath, String key,String fileName) {
		try {
			String folderPath = PlatformUtil.getFolderProjectPath(directoryPath);
			File folder = new File(folderPath);
			String filePath = PlatformUtil.loadFilesInAFolder(folder, String.format("%s.properties",fileName.trim()));

			InputStream input = new FileInputStream(filePath);
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and return
			return prop.getProperty(key);
		} catch (Throwable e) {
			throw new HMException("Cannot read properties file",e);
		}
	}

	public static String createAFileInAFolder(String directoryPath, String fileNameWithExtension) {
		try {
			String filePath = PlatformUtil.getFolderProjectPath(String.format("%s%s",directoryPath,fileNameWithExtension));
			File folder = new File(filePath);
			if(folder.exists()) {
				folder.delete()
			}
			folder.createNewFile()
			return filePath
		}catch (Throwable e) {
			throw new HMException("Cannot create file",e);
		}
	}

	public static int getFileSize(String filePath){
		try{
			return new File(filePath).length()
		}
		catch(ex){
			throw new HMException("Cannot read the file", ex);
		}
		return 0
	}
}


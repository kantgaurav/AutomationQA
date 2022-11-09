package utils

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
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import internal.GlobalVariable

public class WordUtil {
	public static String getContentFromFile(String filePath){
		
				String fileName = filePath.substring(filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)
				String folderPath = filePath.substring(0,filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)
		
		
				File file = new File(folderPath);
				String fullFileName = PlatformUtil.getFullFileNameInFolderByAPartOfName(folderPath, fileName)
				filePath = PlatformUtil.getFilePath(folderPath, fullFileName)
		
				String docText = "";
				
				FileInputStream fis = new FileInputStream(filePath);
				XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
				List<XWPFParagraph> paragraphList = document.getParagraphs();
				for (XWPFParagraph paragraph : paragraphList) {
					docText += paragraph.getText().concat(" ");
				}
				
				return docText;
			}
}

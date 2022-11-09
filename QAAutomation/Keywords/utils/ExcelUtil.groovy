package utils

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.util.CellAddress
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import core.Logger




public class ExcelUtil {

	public static String getContentFromFile(String filePath, String sheetIndex="0"){

		String fileName = filePath.substring(filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)
		String folderPath = filePath.substring(0,filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)


		File file = new File(folderPath);
		String fullFileName = PlatformUtil.getFullFileNameInFolderByAPartOfName(folderPath, fileName)
		filePath = PlatformUtil.getFilePath(folderPath, fullFileName)

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetIndex));
		Iterator<Row> itr = sheet.iterator();
		String result = "";
		while (itr.hasNext()){
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
			while (cellIterator.hasNext()){
				Cell cell = cellIterator.next();

				if ((cell != null) || (!cell.equals("")) || (cell.getCellTypeEnum()!= CellType.BLANK)) {
					cell.setCellType(CellType.STRING);
					result += cell.getStringCellValue().concat(" ");
				}
				//				switch (cell.getCellTypeEnum()){
				//					case CellType.STRING:
				//						result += cell.getStringCellValue().concat(" ");
				//						break;
				//					case CellType.NUMERIC:
				//						result += cell.getNumericCellValue().toString().concat(" ");
				//						break;
				//					case CellType.BOOLEAN:
				//						result += cell.getBooleanCellValue().toString().concat(" ");
				//						break;
				//					case CellType.:
				//						result += cell.getBooleanCellValue().toString().concat(" ");
				//						break;
				//					default:
				//						result += cell.getStringCellValue().toString().concat(" ");
				//						break;

			}

		}
		return result;
	}


	public static void writeContentToFile(String filePath, int rowNum, int colNum, String value){

		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet workSheet = workBook.getSheetAt(0);
			Row row  = workSheet.getRow(rowNum);
			Cell cell = row.getCell(colNum,org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);


			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			workBook.write(fileOut);
			workBook.close();
			fileOut.flush();
			fileOut.close();
		}catch (Exception e) {
			Logger.logDEBUG(String.format("Cannot write file with null data. Error:%s",e.getMessage()))
		}
	}

	public static int getRowCountFromFile(String filePath, String sheetIndex="0"){

		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet workSheet = workBook.getSheetAt(Integer.parseInt(sheetIndex));
		return workSheet.getLastRowNum();
	}

	public static String getAllSheetNameFromFile(String filePath){

		FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		String sheetName = "";
		for (int i=0; i<workBook.getNumberOfSheets(); i++) {
			sheetName = sheetName + " " + workBook.getSheetName(i);
		}
		return sheetName.trim();
	}

	public static String getCommentFromFile(String filePath, String sheetIndex="0", String cellName){

		FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(Integer.parseInt(sheetIndex));
		CellAddress cellAddress = new CellAddress(new CellReference(cellName));
		return sheet.getCellComment(cellAddress).getString();
	}
}

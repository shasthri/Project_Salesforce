package util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	public static Object[][] mReadFromExcel(String sExcelFileName, String sSheetName) {
		Object[][] data = null; // Why Object[][], we don't know what type of data (String, numeric) we are
								// gonna fetch so object

		String sFilePath = "./data/" + sExcelFileName + ".xlsx"; // File path of Excel file
		File oFile = new File(sFilePath); // creating a file object and passing File path to it
		try {
			FileInputStream fis = new FileInputStream(oFile); // Creating a reading pipeline and passing File object to
																// it
			XSSFWorkbook oBook = new XSSFWorkbook(fis); // Creating object for XSSFBook and passing read pipeline to it
			XSSFSheet oSheet = oBook.getSheet(sSheetName); // Assigning Sheet name to XSSFSheet object
			int iTotalRowCount = oSheet.getLastRowNum(); // Getting the last row count
			int iTotalCellCount = oSheet.getRow(0).getLastCellNum(); // getting the last cell count

			data = new Object[iTotalRowCount][iTotalCellCount]; // assigning size for object array.

			for (int iRow = 1; iRow <= iTotalRowCount; iRow++) {
				XSSFRow oRow = oSheet.getRow(iRow); //Assigning row number to XSSFROW object
				for (int iCell = 0; iCell < iTotalCellCount; iCell++) {
					Object cellValue = null; //Creating a variable called cellValue with datatype object 
					XSSFCell oCell = oRow.getCell(iCell); //Assigning cell number to XSSFCell's object
					CellType cellType = oCell.getCellType(); //Identifying the type of value we are getting from cellValue 

					switch (cellType) {
					case NUMERIC:
						long numeric = (long) oCell.getNumericCellValue();
						String valueOfNumeric = String.valueOf(numeric); //The valueOf() method of the String class in Java helps to convert various data types like integers, floats, booleans, and objects into their string representations.
						cellValue = valueOfNumeric; //Assigning the value to cellValue
						break;

					case STRING:
						cellValue = oCell.getStringCellValue();
						break;

					case BOOLEAN:
						System.out.print(oCell.getBooleanCellValue() + "\t");
						break;
					case ERROR:
						System.out.print("It is an Empty Row!!!");
						break;
					default:
						System.out.print("Error in Reading the Data ");
						break;
					}
					data[iRow - 1][iCell] = cellValue; //Why "iRow-1", cell value starts from 0. Since we started iRow as 1, we are decreasing 1 to it.
				}
			}
			oBook.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}

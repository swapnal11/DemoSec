package com.capco;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


public class ExcelReader {
	static XSSFSheet excelSheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;


	@DataProvider
	public static Object[][] readExcel(String filePath) throws InvalidFormatException, IOException {

		FileInputStream fis = new FileInputStream(filePath);   //obtaining bytes from the file  
		XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis);
		XSSFSheet  excelSheet = excelWorkbook.getSheet("Sheet1"); //Your sheet name
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum()+1;
		int colCount = excelSheet.getRow(0).getLastCellNum();
		Object data[][] = new Object[rowCount-1][colCount];
		for (int rNum = 2; rNum <= rowCount; rNum++) 
		{
			for (int cNum = 0; cNum < colCount; cNum++) 
			{
				data[rNum - 2][cNum] = getCellData("Sheet1", cNum, rNum,excelWorkbook); 
			}
			System.out.println();
		}
		return data;

	}

	public static String getCellData(String sheetName, int colNum, int rowNum,XSSFWorkbook excelWorkbook) 
	{
		try
		{
			if (rowNum <= 0)
				return "";
			int index = excelWorkbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			excelSheet = excelWorkbook.getSheetAt(index);
			row = excelSheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA)
			{
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e)
		{
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist in xls";
		}
	}
}



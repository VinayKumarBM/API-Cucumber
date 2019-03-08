package com.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet excelSheet;
	private static XSSFWorkbook excelWorkbook;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static String sheetPath = System.getProperty("user.dir") + "/src/test/resources/data/testData.xlsx";
	private static String sheetName = "testData";

	private static void setExcelFile() throws Exception {
		try{
			System.out.println("Getting sheets from the workbook.");
			FileInputStream excelFile = new FileInputStream(sheetPath);
			excelWorkbook = new XSSFWorkbook(excelFile);
			excelSheet = excelWorkbook.getSheet(sheetName);
		}catch(Exception exp){
			System.out.println("Exception occured in setExcelFile: "+exp.getMessage());
			throw(exp);
		}
	}

	private static int getDataRow(String dataKey, int dataColumn) throws Exception{
		int row;
		try{
			int rowCount = excelSheet.getLastRowNum();
			for(row=0; row< rowCount; row++){
				if(ExcelUtils.getCellData(row, dataColumn).equalsIgnoreCase(dataKey)){
					System.out.println("Test Data Found in Row: "+row);
					break;
				}
			}
		}
		catch(Exception exp){
			System.out.println("Exception occured in getTestCaseRow: "+exp.getMessage());
			throw(exp);
		}
		return row;
	}

	private static String getCellData(int rowNumb, int colNumb) throws Exception{
		try{
			cell = excelSheet.getRow(rowNumb).getCell(colNumb);
			//System.out.println("Getting cell data.");
			if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			}
			String cellData = cell.getStringCellValue();
			return cellData;
		}
		catch(Exception exp){
			return "";
		}
	}

	public static void setCellData(String result, int rowNumb, int colNumb, String sheetPath,String sheetName) throws Exception{
		try{
			row = excelSheet.getRow(rowNumb);
			cell = row.getCell(colNumb);
			System.out.println("Setting results into the excel sheet.");
			if(cell==null){
				cell = row.createCell(colNumb);
				cell.setCellValue(result);
			}
			else{
				cell.setCellValue(result);
			}

			System.out.println("Creating file output stream.");
			FileOutputStream fileOut = new FileOutputStream(sheetPath + sheetName);
			excelWorkbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}catch(Exception exp){
			System.out.println("Exception occured in setCellData: "+exp.getMessage());
			throw (exp);
		}
	}

	public static Map getData(String dataKey) {
		Map dataMap = new HashMap<String, String>();
		try {
			setExcelFile();
			int dataRow = getDataRow(dataKey.trim(), 0);
			int columnCount = excelSheet.getRow(dataRow).getLastCellNum();
			for(int i=0;i<columnCount;i++) {
				cell = excelSheet.getRow(dataRow).getCell(i);
				if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				}
				String cellData = cell.getStringCellValue();
				dataMap.put(excelSheet.getRow(0).getCell(i).getStringCellValue(), cellData);
			}
		}catch (Exception e) {
			System.out.println("Exeception Occured while adding data to Map:\n");
			e.printStackTrace();
		}
		return dataMap;
	}

	public static void main(String []args) {
		Map<String,String> dataMap = new HashMap<String, String>();
		dataMap = getData("postAuthors");
		for(Map.Entry<String, String> data: dataMap.entrySet()) {
			System.out.println(data.getKey()+ " ==> " + data.getValue());
		}
	}
}

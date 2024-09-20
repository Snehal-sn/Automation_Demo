package com.sogeti.dia.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;

/**
 * Class for Excel handling methods
 * @author Savita Tambe
 *
 */
public class ExcelUtil {
	private static ThreadLocal<FileInputStream> fileInputStream = new ThreadLocal<FileInputStream>();
	private static ThreadLocal<XSSFWorkbook> workBook = new ThreadLocal<XSSFWorkbook>();
	private static ThreadLocal<XSSFSheet> workSheet = new ThreadLocal<XSSFSheet>();	
	
	public static void setFileInputStream(FileInputStream fileInputStream) {
		ExcelUtil.fileInputStream.set(fileInputStream);
    }
    
    public static FileInputStream getFileInputStream() {
        return ExcelUtil.fileInputStream.get();
    }
	
	public static void setWorkBook(XSSFWorkbook workBook) {
		ExcelUtil.workBook.set(workBook);
    }
    
    public static XSSFWorkbook getWorkBook() {
        return ExcelUtil.workBook.get();
    }
    
    public static void setWorkSheet(XSSFSheet workSheet) {
		ExcelUtil.workSheet.set(workSheet);
    }
    
    public static XSSFSheet getWorkSheet() {
        return ExcelUtil.workSheet.get();
    }
    
    /**********************************************************************************************
	 * To set the File path and to open the Excel file
	 *   
	 * @param filePath {@link String} - TestData file name	
	 * @param sheetName {@link String} - Sheet name
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void setWorkSheet(String filePath, String sheetName) {		
		try {
				setFileInputStream(new FileInputStream(filePath));
				setWorkBook(new XSSFWorkbook(getFileInputStream()));
				setWorkSheet(getWorkBook().getSheet(sheetName));
				
		} catch (Exception e){
			LoggerUtil.logErrorMessage("Error while accessing the Worksheet: " + sheetName + e);
		}
	   
	}
	
	/**********************************************************************************************
	 * To read the test data from the Excel cell
	 *   
	 * @param rowNo {@link int} - Row number
	 * @param colNo {@link int} - Column number
	 * @return cellData {@link String} - Cell value
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static String getCellData(int rowNo, int colNo) {
		 String cellData = "";
			
		try {	
				XSSFFormulaEvaluator.evaluateAllFormulaCells(getWorkBook());			
				FormulaEvaluator evaluator = getWorkBook().getCreationHelper().createFormulaEvaluator();				
				XSSFCell cell = getWorkSheet().getRow(rowNo).getCell(colNo);
				cell.setCellType(CellType.STRING);
				CellValue cellValue = evaluator.evaluate(cell);
				cellData = cellValue.getStringValue();
				
				if (cellData.isEmpty())
					cellData = ""; 
				 
		} catch (Exception e){
			//LoggerUtil.logErrorMessage("Error while fetching the data from the Worksheet: " + e);
		}
				
		return cellData;
	}
	
	/**********************************************************************************************
	 * To set the test data in the Excel cell
	 *   
	 * @param rowNo {@link int} - Row number
	 * @param colNo {@link int} - Column number
	 * @param obj {@link Object} - Value to be updated in Excel
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/	
	public static void setCellData(int rowNo, int colNo, Object obj) {
		Cell cell = getWorkSheet().getRow(rowNo).createCell(colNo);
		
		if(obj instanceof Date) 
			cell.setCellValue((Date)obj);
		else if(obj instanceof Boolean)
			cell.setCellValue((Boolean)obj);
		else if(obj instanceof String)		        
			cell.setCellValue((String)obj);		          				        		         
		else if(obj instanceof Integer)
			cell.setCellValue((Integer)obj);
		else if(obj instanceof Double)
		 	cell.setCellValue((Double)obj);		      					        	
	}
		 
	/**********************************************************************************************
	* To get the module name
	*   	
	* @param className {@link String} - Class name
	* @return moduleName {@link String} - Module number
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/		
	public static String getModuleName(String className) {
		return className.split("_")[0];
	}
	
	/**********************************************************************************************
	* To get the Test Case Id
	*   	
	* @param className {@link String} - Class name
	* @return testCaseID {@link String} - Test Case Id
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static String getTestCaseId(String className) {
		return (className.split("_")[1]);
	}
	
	/**********************************************************************************************
	* Get first row no of TestCased Id
	*   
	* @param testCaseId {@link int} - TestCase Id
	* @param colNo {@link int} - Column number
	* @return rowNo {@link int} - Row number
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static int getFirstRowContainsTestCaseId(String testCaseId, int colNo){
		int rowNo;
		int rowCount = getWorkSheet().getLastRowNum();
		
		for (rowNo=1;rowNo<=rowCount;rowNo++) {
			if (getCellData(rowNo, colNo).equalsIgnoreCase(testCaseId))
				break;
		}
		
		return rowNo;
	 }

	/**********************************************************************************************
	*  Get last row no of TestCased Id
	*   
	* @param testCaseId {@link String} - TestCase Id	
	* @param colNo {@link int} - Column number	
	* @return rowNo {@link int} - Row number
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static int getLastRowContainsTestCaseId(String testCaseId, int colNo){	
		int rowCount = getWorkSheet().getLastRowNum();
		int startIteration = getFirstRowContainsTestCaseId(testCaseId, colNo);
		int endIteration = startIteration;
		
		for (int rowNo=startIteration;rowNo<=rowCount;rowNo++) {
			if (getCellData(rowNo, colNo).equalsIgnoreCase(testCaseId)) 
				endIteration = rowNo;
			else
				break;
		}
		
		return endIteration;	
	 }
	
	/**********************************************************************************************
	* To fetch colno based on TestCase Name existance
	*   	
	* @param colName {@link String} - Column name
	* @return colNum {@link int} - Column number
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/
	public static int getColContains(String colName){
		int colNo;
		int colCount = getWorkSheet().getRow(0).getLastCellNum();
		
		for (colNo=0;colNo<colCount;colNo++){
			if(getCellData(0, colNo).equals(colName)){
				break;
			}
		}
	
		return colNo;
	}
	
	/**********************************************************************************************
	* To get the application details from Config sheet
	*   	
	* @param appEnv {@link String} - Application environment
	* @return appConfig {@link Map} - App details
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static HashMap<String, String> getConfigDetails(String appEnv) {			
		HashMap <String, String> appConfig = new HashMap <String, String>();
				
		setWorkSheet(Config.TEST_DATA_FILE_PATH, "Config");
		int configRow = getRowContainsQueryName(appEnv, 0);				
		appConfig.put("URL", getCellData(configRow, 1));
		appConfig.put("UserName", getCellData(configRow, 2));
		appConfig.put("Password", getCellData(configRow, 3));
		closeWorkBook();
		
		return appConfig;
	}
		
	/**********************************************************************************************
	* To fetch the test data
	*   	
	* @param className {@link String} - Class name
	* @return testData {@link Map} - Hash map
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static HashMap<Integer, HashMap<String, String>> getTestData(String className) {	
		int iteration = 1;
		HashMap<String, String> testData = null;
		HashMap<Integer, HashMap<String, String>> testDataIterations = new HashMap<Integer, HashMap<String, String>>();
		
		//Get Config details	
		HashMap <String, String> appConfig = getConfigDetails(TestRun.getAppEnvironment());

		//Get Test data
		setWorkSheet(Config.TEST_DATA_FILE_PATH, getModuleName(className));		
		String testCaseId = getTestCaseId(className);		
		int startIteration = getFirstRowContainsTestCaseId(testCaseId, 0);
		int endIteration = getLastRowContainsTestCaseId(testCaseId, 0);		
		int totalCols = getWorkSheet().getRow(startIteration-1).getLastCellNum();
		
		for (int i=startIteration;i<=endIteration;i++) {
			if (getCellData(i,2).equalsIgnoreCase("Y")) {
				testData = new HashMap<String, String>();
				for (int j=2;j<=totalCols;j++)				
				{				
					testData.put(getCellData(startIteration-1,j), getCellData(i,j));
			    }
				
				testData.put("URL", appConfig.get("URL"));		
				if (!testData.containsKey("UserName")) {
					testData.put("UserName", appConfig.get("UserName"));
					testData.put("Password", appConfig.get("Password"));
				}
				
				testDataIterations.put(iteration, testData);
				iteration = iteration + 1;
			}			
		}
			
		closeWorkBook();	
		return testDataIterations;
	  }
	
	/**********************************************************************************************
	* To update the test data file
	*   	
	* @param className {@link String} - Class name
	* @param colName {@link String} - Column name
	* @param valueToBeUpdated {@link Object} - Value to be updated in excel	
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static void setTestData(String className, String colName, Object valueToBeUpdated) {
		setWorkSheet(Config.TEST_DATA_FILE_PATH, getModuleName(className));
		int rowNo = getFirstRowContainsTestCaseId(getTestCaseId(className), 0);
		int colNo = getColContains(colName);
		setCellData(rowNo, colNo, valueToBeUpdated);
		
		try {
				FileOutputStream fout = new FileOutputStream(new File(Config.TEST_DATA_FILE_PATH));
				getWorkBook().write(fout);
				fout.close();
				
		} catch (Exception e) {		
			LoggerUtil.logErrorMessage("Error while updating the TestData file: " + e);
		}
		
		closeWorkBook();	
	}	
	
	/**********************************************************************************************
	* To get the row no of DB query
	*   	
	* @param queryId {@link String} - Query ID
	* @param colNo {@link int} - Column no
	* @return rowNo {@link int} - Row no
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static int getRowContainsQueryName(String queryId, int colNo){
		int rowNo;
		int rowCount = getWorkSheet().getLastRowNum();
		
		for (rowNo=1;rowNo<=rowCount;rowNo++)
			if (getCellData(rowNo, colNo).equalsIgnoreCase(queryId))
				break;
	
		return rowNo;
	}

	/**********************************************************************************************
	* To get the DB query from data DBQueries sheet
	*   	
	* @param queryId {@link String} - Query Id
	* @return query {@link String} - Query
	* @author Savita Tambe created May 21, 2018
	* @version 1.0 May 21, 2018
	***********************************************************************************************/	
	public static String getQuery(String queryId) {			
		setWorkSheet(Config.TEST_DATA_FILE_PATH, "DBQueries");
		int queryRow = getRowContainsQueryName(queryId, 0);				
		closeWorkBook();
				
		return getCellData(queryRow, 1);
	}
	
	/**********************************************************************************************
	 * To close the Test data file
	 *  
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void closeWorkBook() {			
		try {				
				getWorkBook().close();
				getFileInputStream().close();		
		} catch (Exception e){
			LoggerUtil.logErrorMessage("Error while closing TestData file: " + e);
		}
	}
}
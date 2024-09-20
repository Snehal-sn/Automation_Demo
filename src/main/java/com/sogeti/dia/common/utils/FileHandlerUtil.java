package com.sogeti.dia.common.utils;

import java.awt.Desktop;
import java.io.File;

/**
 * Class for Folder and File handling methods
 * @author Savita Tambe
 *
 */
public class FileHandlerUtil {
	/**********************************************************************************************
	 * To create file
	 *   
	 * @param filePath {@link String} - File path	
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 * @return status {@link boolean} - true/false
	 ***********************************************************************************************/
	public static boolean createFile(String filePath) {
		boolean status = false;
		
		File file = new File(filePath);
		if (!file.exists()) {
			try {
					return file.createNewFile();
			} catch (Exception e) {
				LoggerUtil.logErrorMessage("Failed to create file: " + filePath + e);
			}
		}
		
		return status;
	}
	
	/**********************************************************************************************
	 * To delete all files from directory
	 *   
	 * @param dirPath {@link String} - Directory path
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 * @return status {@link boolean} - true/false
	 ***********************************************************************************************/
	public static boolean deleteFiles(String dirPath)
	{
		boolean status = false;
		
		File file = new File(dirPath);
		File[] allFiles = file.listFiles();
		for (File file1 : allFiles) 
		{
			status = file1.delete();
		}
		
		return status;
	}
	
	/**********************************************************************************************
	 * To get the file path
	 *   
	 * @param dirPath {@link String} - Directory path
	 * @return filepath {@link String} - File path
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static String getFullFilePath(String dirPath)
	{
		String filepath;
		
		File file = new File(dirPath);
		filepath = file.listFiles()[0].getAbsolutePath();
		 
		return filepath;
	}

	/**********************************************************************************************
	 * To delete directory
	 *   
	 * @param dirPath {@link File} - Directory path
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static boolean deleteDirectory(File dirPath) {
		boolean status = false;
		
		if(dirPath.exists() ) {
			File[] files = dirPath.listFiles();
			for(int i=0; i<files.length; i++) 
			{
				if(files[i].isDirectory()) {
					deleteDirectory(files[i]);
			     }
			     else {
			    	  	files[i].delete();
			     }
			}
		}
		
		status = dirPath.delete();
		return status;
    }
    
	/**********************************************************************************************
	 * To open file
	 *   
	 * @param filePath {@link String} - File path
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void launchFile(String filePath)
	{
	    try
	    {   
	    	Desktop.getDesktop().open(new File(filePath));
	    }catch(Exception  e){  
	    	LoggerUtil.logErrorMessage("Failed to launch file: " + filePath + e);
	    }
	}	
}
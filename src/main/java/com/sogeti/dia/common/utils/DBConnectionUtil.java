package com.sogeti.dia.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sogeti.dia.common.config.Config;
/**
 * 
 * @author Savita Tambe
 *
 */
public class DBConnectionUtil{			
	/**********************************************************************************************
	 * Oracle DB connection
	 *
	 * @return con {@link Connection} - Connection object
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static Connection dbConnector() {	
		Connection con = null;				
		String dbUrl = "jdbc:sqlserver://" + Config.DB_HOST + ";databasename=" + Config.DB_SID;		
	
		try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");				
				con = DriverManager.getConnection(dbUrl, Config.DB_USER_ID, Config.DB_PASSWORD);
		    	LoggerUtil.logMessageNoScreenShot("Connection to SQL Server DB open.");
		    	
		} catch (Exception e) {
			 LoggerUtil.logMessageNoScreenShot("Unable to connect to SQL Server DB: " + e);
		}	
				
		return con;
	}
		
	/**********************************************************************************************
	 * Close Oracle DB connection
	 * 
	 * @param con {@link Connection} - Connection object
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void closeDB(Connection con) {		
		try {
				con.close();
				LoggerUtil.logMessageNoScreenShot("Conenction to SQL Server DB closed.");
		} catch (Exception e) {
			LoggerUtil.logMessageNoScreenShot("Unable to close conenction to SQL Server DB: " + e);		
		}	
	}
	
	/**********************************************************************************************
	 * Sample method to get City details from DB, create methods as per the requirement
	 * 
	 * @param query {@link String} - Query
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void getCityDetails(String query) {
		Statement stmt = null;
		ResultSet resultSet = null;
    	
		Connection con = DBConnectionUtil.dbConnector(); 	    
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(query);

	 		while (resultSet.next())
			{
				LoggerUtil.logMessageNoScreenShot("City Name: " + resultSet.getString("Name") + " Country Code: " +
					resultSet.getString("CountryCode") + " District Name: " + resultSet.getString("District") +
					" Population: " + resultSet.getString("Population"));
				break;
			}	
				
		} catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to get details from SQL Server DB: " + e );
		}
	}
}
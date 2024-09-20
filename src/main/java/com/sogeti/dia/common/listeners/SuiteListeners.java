package com.sogeti.dia.common.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.sogeti.dia.common.utils.LoggerUtil;
/**
 * Suite listener
 * @author Savita Tambe
 *
 */
public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {   
    	LoggerUtil.logConsoleMessage("========SUITE EXECUTION STARTED========");
    	
    	/*Database connection
		DBConnectionUtil.oracleDBConnector();*/
    }
    
    @Override
    public void onFinish(ISuite suite) {
    	LoggerUtil.logConsoleMessage("========SUITE EXECUTION FINISHED========");
    	//DBConnectionUtil.closeDB();	
    }
}
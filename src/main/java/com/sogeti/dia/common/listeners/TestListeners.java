package com.sogeti.dia.common.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.LoggerUtil;
/**
 * Suite listener to customize the testNG result
 * @author savtambe
 *
 */
public class TestListeners implements IRetryAnalyzer, ITestListener {
	int counter = 0;
	int retryLimit = 0;
	
	@Override
    public void onStart(final ITestContext context) {
		LoggerUtil.logConsoleMessage("========TEST EXECUTION STARTED========");
    }

    @Override
    public void onTestStart(final ITestResult result) {
		if (TestRun.isMobile()) 
    		LoggerUtil.logConsoleMessage("TEST SCRIPT: " + TestRun.getScriptName() + " EXECUTION STARTED ON: " + TestRun.getExecutionPlatform() + "- " + TestRun.getPlatformName() + "- " + TestRun.getDeviceName());
    	
    	else if (TestRun.isDesktop()) {
    		if (TestRun.getPlatformName() == null)
        		LoggerUtil.logConsoleMessage("TEST SCRIPT: " + TestRun.getScriptName() + " EXECUTION STARTED ON: " + TestRun.getExecutionPlatform() + " ON - " + TestRun.getBrowserName());
        	else
        		LoggerUtil.logConsoleMessage("TEST SCRIPT : " + TestRun.getScriptName() + " EXECUTION STARTED ON: " + TestRun.getExecutionPlatform() + " ON - " + TestRun.getPlatformName() + "- " + TestRun.getBrowserName());    	
    	}
    	
    	else if (TestRun.isWindows()) 
			LoggerUtil.logConsoleMessage("TEST SCRIPT : " + TestRun.getScriptName() + " EXECUTION STARTED ON: " + TestRun.getExecutionPlatform());
    	
    	else if (TestRun.isApi()) 
			LoggerUtil.logConsoleMessage("API TEST SCRIPT : " + TestRun.getScriptName() + " EXECUTION STARTED");
    }

    @Override
    public void onFinish(final ITestContext context) {        	
    	LoggerUtil.logConsoleMessage("========TEST EXECUTION FINISHED========");
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        teardownTest(result);
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    	teardownTest(result);
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        teardownTest(result);
    }

    private void teardownTest(ITestResult result) { 
    	String status = result.isSuccess() ? "PASSED" : "FAILED";
        LoggerUtil.logConsoleMessage("======TEST SCRIPT: " + result.getName() + ": " + status + "======");	          
    }

	@Override
	public boolean retry(ITestResult result) {
		if(counter < retryLimit)
		{
			counter++;
			return true;
		}		
		return false;		
	}
}
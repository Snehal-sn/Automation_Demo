package com.client.app.common.utils;

import com.client.app.pageobjects.CalculatorPage;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;


public class CalculatorUtil {
		protected CalculatorPage calculatorPage;

		public CalculatorUtil() {
			calculatorPage = new CalculatorPage();
		}
		
		public String combination()
	    {
			WebInteractUtil.click(calculatorPage.sevenBtn);
			WebInteractUtil.click(calculatorPage.multiplyByBtn);
			WebInteractUtil.click(calculatorPage.nineBtn);
			WebInteractUtil.click(calculatorPage.multiplyByBtn);
			WebInteractUtil.click(calculatorPage.plusBtn);
			WebInteractUtil.click(calculatorPage.oneBtn);				
			WebInteractUtil.click(calculatorPage.eualsBtn);
			WebInteractUtil.click(calculatorPage.divideByBtn);
			WebInteractUtil.click(calculatorPage.eightBtn);
			WebInteractUtil.click(calculatorPage.eualsBtn);
			LoggerUtil.logMessage(calculatorPage.resultTxa.getText());;
			
			return calculatorPage.resultTxa.getText();
	    }		
}
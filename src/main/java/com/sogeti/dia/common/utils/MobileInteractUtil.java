package com.sogeti.dia.common.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.HideKeyboardStrategy;

/**
 * Class having mobile specific reusable methods
 * @author Savita Tambe
 *
 */
public class MobileInteractUtil {	
	/**********************************************************************************************
	 * Switch to NativeView
	 *     
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static boolean switchToNativeView() {
		boolean status = false;
		
		try {
				Set<String> contextNames = DriverManagerUtil.getAppiumDriver().getContextHandles(); 
			    for (String contextName : contextNames) { 
			        if (contextName.contains("NATIVE_APP")) { 
			        	DriverManagerUtil.getAppiumDriver().context(contextName); 
			        	LoggerUtil.logMessageNoScreenShot("Switched to Native view.");
			        	status = true;
			        	break;
			        } 
			    }
		} catch (Exception e) {             
			LoggerUtil.logErrorMessage("Unable switch to webview.");  
		}
	    
	    return status;
	}
	
	/**********************************************************************************************
	 * Switch to WebView
	 *     
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created May 21, 2018
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static boolean switchToWebView() {
		boolean status = false;
		
		try {
			Set<String> contextNames = DriverManagerUtil.getAppiumDriver().getContextHandles(); 
		    for (String contextName : contextNames) { 
		        if (contextName.contains("WEBVIEW") || contextName.contains("WebView") || contextName.contains("CHROMIUM")) { 
		        	DriverManagerUtil.getAppiumDriver().context(contextName); 
		        	LoggerUtil.logMessageNoScreenShot("Switched to Web view.");
		        	status = true;
		        	break;
		        } 
		    }
		} catch (Exception e) {             
			LoggerUtil.logErrorMessage("Unable switch to webview.");  
		}
		
	    return status;
	}
	
	/**********************************************************************************************
	 * Scroll to element by object name and click in IOS
	 *     
	 * @param objectName {@link String} - Class name of the parent table	
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created April 18, 2018
	 * @version 1.0 April 18, 2018
	 ***********************************************************************************************/
	public static boolean scrollToElementByNameAndClickIOS(String objectName) {
		boolean status = false;

		try {
				if (scrollToElementByNameIOS(objectName)) {
					DriverManagerUtil.getIOSDriver().findElementByName(objectName).click();
					status = true;
				}				
			} catch (Exception e) {             
				LoggerUtil.logErrorMessage("Unable to scroll & click to iOS mobileElement: " + objectName);
		}
		return status;
	}
		
	/**********************************************************************************************
	 * Scroll to element by name in IOS
	 *     
	 * @param objectName {@link String} - Name of the object
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created April 18, 2018
	 * @version 1.0 April 18, 2018
	 ***********************************************************************************************/
	public static boolean scrollToElementByNameIOS(String objectName) {
		boolean status = false;
		
		try {
				RemoteWebElement element = (RemoteWebElement)DriverManagerUtil.getIOSDriver().findElement(By.name(objectName)); 
				String elementID = element.getId();
				
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", elementID);
				scrollObject.put("toVisible", "not an empty string");
				
				DriverManagerUtil.getIOSDriver().executeScript("mobile:scroll", scrollObject);
				status = true;
		} catch (Exception e) {             
			LoggerUtil.logErrorMessage("Unable to scroll to iOS mobileElement: " + objectName);
		}
		return status;
	}

	/**********************************************************************************************
	 * Scroll to element parent class name and object name in IOS
	 *     
	 * @param classNameOfScrollableList  {@link String} - Class name of the scrollable object
	 * @param objectName  {@link String} - Name of the object
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created April 18, 2018
	 * @version 1.0 April 18, 2018
	 ***********************************************************************************************/
	public static boolean scrollToElementByParentNameIOS(String classNameOfScrollableList, String objectName) {
		boolean status = false;
		
		try {
				RemoteWebElement parent = (RemoteWebElement)DriverManagerUtil.getIOSDriver().findElement(By.className(classNameOfScrollableList)); 
				//identifying the parent Table
				String parentID = parent.getId();
				
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", parentID);
				//Use name attribute to identify object              
				scrollObject.put("name", objectName);
				//Scroll to the target element
				DriverManagerUtil.getIOSDriver().executeScript("mobile:scroll", scrollObject); 

				status = true;
			} catch (Exception e) {             
				LoggerUtil.logErrorMessage("Unable to scroll to iOS mobileElement: " + objectName);
		}

		return status;
	}

	/**********************************************************************************************
	 * Scroll to element by parent name, object name and click in IOS
	 *     
	 * @param classNameOfScrollableList  {@link String} - Class name of the scrollable object
	 * @param objectName {@link String} - Name of the object
	 * @return status {@link boolean} - true/false
	 * @author Savita Tambe created April 18, 2018
	 * @version 1.0 April 18, 2018
	 ***********************************************************************************************/
	public static boolean scrollToElementByParentNameAndClickIOS(String classNameOfScrollableList, String objectName) {
		boolean status = false;

		try {
				if (scrollToElementByParentNameIOS(classNameOfScrollableList, objectName)) {
					DriverManagerUtil.getIOSDriver().findElementByName(objectName).click();
					status = true;
				}				
			} catch (Exception e) {             
				LoggerUtil.logErrorMessage("Unable to scroll & click to iOS mobileElement: " + objectName);
		}
		return status;
	}
		
    /**********************************************************************************************
     * Scroll to element by Text
     * 
     * @param locatorTypeOfScrollableList {@link String} - Locator type of the scrollable object (e.g.- className, resourceId) (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object  
     * @param objectClass {@link String} - Class name of the object you want to scroll
     * @param text  {@link String} - Text of the object
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByTextAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String objectClass, String text) {
		boolean status = false;
		
		try {
				DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).getChildByText("
				+ "new UiSelector().className(\"" + objectClass + "\"), \"" + text + "\")"));					
				
				status = true;
			} catch (Exception e) {
				LoggerUtil.logErrorMessage("Unable to scroll by text to Android mobileElement: " + text);
			}
			         
		return status;		
	}
	
	/**********************************************************************************************
     * Scroll to element by Content Description
     * 
     * @param locatorTypeOfScrollableList {@link String} - Locator type of the scrollable object (e.g.- className, resourceId)  
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object  
     * @param objectClass {@link String} - Class name of the object you want to scroll
     * @param contentDesc {@link String} - Content description of the object you want to scroll
     * @return status {@link boolean} - true/false    
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByContentDescAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String objectClass, String contentDesc) {		
		boolean status = false;
		
		try {
				DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).getChildByDescription("
				+ "new UiSelector().className(\"" + objectClass + "\"), \"" + contentDesc + "\")"));
				
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll by content description to Android mobileElement: " + contentDesc);
		}
		         
		return status;
	}
	
	/**********************************************************************************************
     * Scroll to element by Text and click
     * 
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param objectClass {@link String} - Class name of the object you want to scroll
     * @param text  {@link String} - Text of the object you want to scroll
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByTextAndClickAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String objectClass, String text) {
		boolean status = false;
		
		try {
				MobileElement element = DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).getChildByText("
				+ "new UiSelector().className(\"" + objectClass + "\"), \"" + text + "\")"));				
				
				element.click();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll & click by text to Android mobileElement: " + text);
		}
		         
		return status;
	}
	
	/**********************************************************************************************
     * Scroll to element by Content description and click
     * 
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param objectClass {@link String} - Class name of the object you want to scroll
     * @param contentDesc  {@link String} - Content description of the object you want to scroll
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByContentDescAndClickAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String objectClass, String contentDesc) {
		boolean status = false;
		
		try {
				MobileElement element = DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).getChildByDescription("
				+ "new UiSelector().className(\"" + objectClass + "\"), \"" + contentDesc + "\")"));
			
				element.click();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll & click by content description to Android mobileElement: " + contentDesc);
		}
	         
		return status;
	}
	
	/**********************************************************************************************
     * Scroll to element by text and instance (As of now it works only for 0 and 1 instance)
     * 
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param text  {@link String} - Text of the object you want to scroll
     * @param instance {@link int} - Occurrence of the string starts with 0
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByInstanceAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String text, int instance) {
		boolean status = false;
		
		try {
				DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).scrollIntoView("
				+ "new UiSelector().textContains(\"" + text + "\").instance(" + instance + "))"));		
			
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll by instance to Android mobileElement: " + text);
		}
         
		return status;
	}
	
	/**********************************************************************************************
     * Scroll to element by text, instance and click (As of now it works only for 0 and 1 instance)
     * 
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param text  {@link String} - Text of the object you want to scroll
     * @param instance {@link int} - Occurrence of the string starts with 0
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean scrollToElementByInstanceAndClickAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String text, int instance) {
		boolean status = false;
		
		try {
				MobileElement element = DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).scrollIntoView("
				+ "new UiSelector().textContains(\"" + text + "\").instance(" + instance + "))"));		
			
				element.click();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll & click by instance to Android mobileElement: " + text);
		}
         
		return status;
	}
	
	/**********************************************************************************************
     * Horizontal Scroll to element by Content description and click
     *    
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param contentDesc  {@link String} - Content description of the object you want to scroll
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean horizontanlScrollToElementByContentDescAndClickAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String contentDesc) {
		boolean status = false;
		
		try {
				MobileElement element = DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).setAsHorizontalList().scrollIntoView("
				+ "new UiSelector().descriptionContains(\"" + contentDesc + "\"))"));
				
				element.click();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll horizontally & click by content desc to Android mobileElement: " + contentDesc);
		}
	         
		return status;
	}
	
	/**********************************************************************************************
     * Horizontal sroll to element by Text and click
     * 
     * @param locatorTypeOfScrollableList {@link String} -Locator type of the scrollable object (e.g.- className, resourceId)
     * @param locatorValueOfScrollableList {@link String} - Locator value of the scrollable object
     * @param text  {@link String} - Text of the object you want to scroll
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created April 18, 2018 
     * @version 1.0 April 18, 2018
     ***********************************************************************************************/
	public static boolean horizontalScrollToElementByTextAndClickAndroid(String locatorTypeOfScrollableList, String locatorValueOfScrollableList, String text) {
		boolean status = false;
		
		try {
				MobileElement element = DriverManagerUtil.getAppiumDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()." + locatorTypeOfScrollableList + "(\"" + locatorValueOfScrollableList + "\")).setAsHorizontalList().scrollIntoView("
				+ "new UiSelector().text(\"" + text + "\"))"));
				
				element.click();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to scroll horizontally & click by text to Android mobileElement: " + text);
		}
		         
		return status;
	}
	
    /**********************************************************************************************
     * Waits for mobile element and clicks on it
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to click on
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 31, 2018
     * @version 1.0 March 31, 2018
     ***********************************************************************************************/
    public static boolean click(MobileElement mobileElement) {
    	boolean status = false;
    	
    	try {
    			waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);    	
    			mobileElement.click();
    			status = true;
    			
		} catch (StaleElementReferenceException e1) {
			for (int i = 0; i <= 10; ++i) {
				try {
						waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
						mobileElement.click();					
						break;
				} catch (Exception e) {
					continue;
				}	
			}
		} catch (Exception e) {
			//e.printStackTrace();		
		}
		
		return status;
	}

    
    /**********************************************************************************************
     * Waits for mobile element and set text in it
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to set text
     * @param text {@link String} - Text to set
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 31, 2018
     * @version 1.0 March 31, 2018
     ***********************************************************************************************/
    public static boolean sendKeys(MobileElement mobileElement, String text) {
    	boolean status = false;
    	
		try {
				waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
				mobileElement.clear();
				mobileElement.sendKeys(text);
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for mobile element and clears text in it
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to clear text
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 31, 2018
     * @version 1.0 March 31, 2018
     ***********************************************************************************************/
    public static boolean clear(MobileElement mobileElement) {
    	boolean status = false;
    	
		try {
				waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
				mobileElement.clear();
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}     
         
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByValue(MobileElement mobileElement, String value) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(mobileElement);
				listBox.selectByValue(value);			
				status = true;
				
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByVisibleText(MobileElement mobileElement, String value) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(mobileElement);
				listBox.selectByVisibleText(value);			
				status = true;
				
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to select value
     * @param index {@link int} - Value index to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByIndex(MobileElement mobileElement, int index) {
    	boolean status = false;
		try {
				waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);		
				Select listBox = new Select(mobileElement);
				listBox.selectByIndex(index);
				status = true;
				
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for mobile element visibility
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to wait for visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 31, 2018
     * @version 1.0 March 31, 2018
     ***********************************************************************************************/
    public static boolean waitForElementToBeVisible(MobileElement mobileElement, int timeOut) {
    	boolean status = false;
    	
		try {
				FluentWait<AppiumDriver<MobileElement>> fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver())    		     
					.withTimeout(Duration.ofSeconds(timeOut))
    		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))				
    		        .ignoring(NoSuchElementException.class);   
    			
				fluentWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for mobile element to be non visible
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to wait for non visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 31, 2018
     * @version 1.0 March 31, 2018
     ***********************************************************************************************/
    public static boolean waitForInvisibilityOfElement(MobileElement mobileElement, int timeOut) {
    	boolean status = false;
    	
    	try {	  	
    			FluentWait<AppiumDriver<MobileElement>> fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver())    		       
					.withTimeout(Duration.ofSeconds(timeOut))
    		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))					
    		        .ignoring(NoSuchElementException.class);
    			
    				fluentWait.until(ExpectedConditions.invisibilityOf(mobileElement));	
    				status = true;
							
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Verifies element is present
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to wait for visibility
     * @param timeOut - {@link Integer} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018   
     ***********************************************************************************************/    
   	public static boolean isPresent(MobileElement mobileElement, int timeOut) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(mobileElement, timeOut);   
	   	try {
	   			status = mobileElement.isDisplayed();
	   	} catch(Exception e) {
	   		//e.printStackTrace();
	   	}
		  
       return status;
   	} 
   	 
   	/**********************************************************************************************
     * Verifies element is enabled
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to verify if enabled
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/    
   	public static boolean isEnabled(MobileElement mobileElement) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);   
	   	try {
	   			status = mobileElement.isEnabled();				 		    
	   	} catch(Exception e) {
	   		//e.printStackTrace();
	   	}
		  
       return status;
   	} 
 
    /**********************************************************************************************
     * Fetch text of an element.
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to fetch text
     * @return text {@link String} - value
     * @author Savita Tambe created Maay 08, 2018 
     * @version 1.0 May 08, 2018
     ***********************************************************************************************/
   	public static String getText(MobileElement mobileElement) {    	    	
    	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
 	   	try { 	   		
 	   			return mobileElement.getText();
        } catch (Exception e) {
        	e.printStackTrace();
        	//e.printStackTrace();
        }
 	   	
		return null; 	   	       
     }
    /**********************************************************************************************
     * Determines if an element has a specific text value or not.
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to verify text
     * @param text - {@link String} - Text to evaluate
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created Maay 08, 2018 
     * @version 1.0 May 08, 2018
     ***********************************************************************************************/
   	public static boolean verifyText(MobileElement mobileElement, String text) {
    	boolean status = false;
    	
    	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
 	   	try { 	   		
 	   			status = mobileElement.getText().equalsIgnoreCase(text);  
        } catch (Exception e) {        	                
        	//e.printStackTrace();
        }
 	   	
         return status;
     }
      
    /**********************************************************************************************
     * Fetches elements specific attribute value
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to fetch the attribute
     * @param attribute - {@link String} - The specific attribute type to evaluate   
     * @return attributeValue
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018     
     ***********************************************************************************************/
    public static String getAttribute(MobileElement mobileElement, String attribute) {    	
    	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
 	   	try {
 	   			return mobileElement.getAttribute(attribute);
        } catch (Exception e) {
        	//e.printStackTrace();
        }
 	   	
 	   	return null;
     }
     
     /**********************************************************************************************
     * Determines if an element has a specific attribute value or not.
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to verify attribute
     * @param attribute - {@link String} - The specific attribute type to evaluate
     * @param attributeValue - {@link String} - The value of the attribute to evaluate
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/
    public static boolean verifyAttributeValue(MobileElement mobileElement, String attribute, String attributeValue) {
    	 boolean status = false;
      	
    	 waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);
   	   	 try { 	   		
   	   			status = mobileElement.getAttribute(attribute).equalsIgnoreCase(attributeValue);  
         } catch (Exception e) {        	                
        	//e.printStackTrace();
         }
   	   	
         return status;
     }
       
    /**********************************************************************************************
     * Determines if an element has a specific attribute value or not.
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to verify attribute
     * @param attribute {@link String} - The specific attribute type to evaluate
     * @param attributeValue - {@link String} - The value of the attribute to evaluate
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/
    public static boolean verifyAttributeContains(MobileElement mobileElement, String attribute, String attributeValue) {
	   	boolean status = false;   
	
	   	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);    	
	   	try {
	   			status = mobileElement.getAttribute(attribute).toUpperCase().contains(attributeValue.toUpperCase());  	               	   	 	
		} catch (Exception e) {  	   		  
			//e.printStackTrace();
		}
	   	
        return status;
    }
	    
    /**********************************************************************************************
	  * Waits for element to have specific attribute value.
	  * 
	  * @param mobileElement {@link MobileElement} - MobileElement to verify attribute
	  * @param attribute {@link String} - The specific attribute type to evaluate
	  * @param attributeValue {@link String} - The value of the attribute to evaluate
	  * @param timeOut {@link int} - The value of the timeout
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean waitForAttributeContains(MobileElement mobileElement, String attribute, String attributeValue, int timeOut) {
	    boolean status = false;   
		FluentWait<AppiumDriver<MobileElement>> fluentWait;   		     
   
	    try {
				fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver())
				.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))				
		        .ignoring(NoSuchElementException.class);
	         
	    		fluentWait.until(ExpectedConditions.attributeContains(mobileElement, attribute, attributeValue));                  
	    		status = true;              
             
	    } catch (Exception e) {                  
	    	//e.printStackTrace();
	    }
	    return status;
	} 
	 
    /**********************************************************************************************
     * Verifies element is selected
     * 
     * @param mobileElement {@link MobileElement} - MobileElement to verify if selected
     * @return status {@link boolean} - true/false
     * @author Deepak Rai created May 4, 2018 
     * @version 1.0 May 4, 2018
     ***********************************************************************************************/    
   	public static boolean isSelected(MobileElement mobileElement) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(mobileElement, Config.MEDIUM_PAUSE);   
	   	try {
	   			status = mobileElement.isSelected();				 		    
	   	} catch(Exception e) {
	   		//e.printStackTrace();
	   	}
		  
        return status;
   	} 
   	
    /**********************************************************************************************
     * Closes the soft keyboard.
     * 
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018  
     * @exception WebDriverException - Soft keyboard is not present.
     ***********************************************************************************************/
    public static void closeKeyboard() {
    	if (TestRun.isMobile()) {	  
		    if (TestRun.isAndroid()) {	        
			    try {
			    		DriverManagerUtil.getAndroidDriver().hideKeyboard();
			    } catch (Exception e) {}
			} else if (TestRun.isIos()) {	    
				try {
						DriverManagerUtil.getIOSDriver().hideKeyboard(HideKeyboardStrategy.PRESS_KEY, "Done");
				} catch (Exception e) {
						DriverManagerUtil.getIOSDriver().hideKeyboard();
				}
			}	
    	}
    }        

	/**********************************************************************************************
     * ANDROID ONLY - Taps the Android back button.
     * 
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     **********************************************************************************************/
    public static void goBackAndroid() {
        LoggerUtil.logMessage("Tap the back button");
        DriverManagerUtil.getAndroidDriver().pressKeyCode(AndroidKeyCode.BACK);    
    }
    
    /**********************************************************************************************
     * ANDROID ONLY Taps the submit/enter button.
     * 
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/
    public static void submitAndroid() {
        LoggerUtil.logMessage("Tap the submit button");
        DriverManagerUtil.getAndroidDriver().pressKeyCode(AndroidKeyCode.ENTER);
    }
    
    
    /**********************************************************************************************
     * ANDROID ONLY - Opens the Android menu.
     * 
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/
    public static void openMenuAndroid() {
        LoggerUtil.logMessage("Tap the menu button.");
        DriverManagerUtil.getAndroidDriver().pressKeyCode(AndroidKeyCode.MENU);      
    }
    
    /**********************************************************************************************
     * ANDROID ONLY - Taps the Android home button.
     * 
     * @author Savita Tambe created Mar 30, 2018 
     * @version 1.0 Mar 30, 2018
     ***********************************************************************************************/
    public static void tapHomeButtonAndroid() {
        LoggerUtil.logMessage("Tap the home button.");
        DriverManagerUtil.getAndroidDriver().pressKeyCode(AndroidKeyCode.HOME);
    }
    
    /**********************************************************************************************
     * Pauses the test action.
     * 
     * @param waitTime {@link Integer} - The amount of time in milliseconds to pause.
     * @author Savita Tambe created March 30, 2018 
     * @version 1.0 March 30, 2018
     ***********************************************************************************************/
	public static void pause(Integer waitTime) {
	    try {
	        	Thread.sleep(waitTime);
	    } catch (Exception e) {
	    	//e.printStackTrace();
	    }
	}
}
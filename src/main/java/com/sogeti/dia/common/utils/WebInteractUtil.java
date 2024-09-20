package com.sogeti.dia.common.utils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;

import io.github.sukgu.Shadow;


/**
 * Class having web web application specific reusable methods
 * @author Savita Tambe
 *
 */
public class WebInteractUtil {	    
	/**********************************************************************************************
	 * Waits for web element and clicks on it
	 * 
	 * @param url {@link String} - Url to launch
	 * @author Savita Tambe created May 31, 2018
	 * @version 1.0 May 31, 2018
	 ***********************************************************************************************/
	public static void launchWebApp(String url) {
		if (TestRun.isMobile())
			DriverManagerUtil.getAppiumDriver().navigate().to(url);
		else if (TestRun.isDesktop()) {
			DriverManagerUtil.getWebDriver().navigate().to(url);		
	    	try {
	    			DriverManagerUtil.getWebDriver().manage().window().maximize();
	    	} catch(Exception e) {}
		}
	    	
		LoggerUtil.logMessage("Launch application: " + url);
	}
	
	/**********************************************************************************************
	  * Switch to frame
	  * 
	  * @param frameIdOrName {@link String} - Id or Name of the frame
	  * @param timeOut {@link int} - Wait time out
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean switchToFrameByIdOrName(String frameIdOrName, int timeOut) {
		 boolean status = false;
		 FluentWait<RemoteWebDriver> fluentWait = null;

		 try {
			 	if (TestRun.isMobile())
			 		fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else if (TestRun.isDesktop())
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver());
			 	
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);				
				fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdOrName));
				
	            status = true;
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to frame: " + frameIdOrName);
		 }

		 return status;
	 }
	 
	 /**********************************************************************************************
	  * Switch to frame by index
	  * 
	  * @param frmaeIndex {@link int} - Index of the frame
	  * @param timeOut {@link int} - Wait time out
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean switchToFrameByIndex(int frmaeIndex, int timeOut) {
		 boolean status = false;
		 FluentWait<RemoteWebDriver> fluentWait = null;
		 
		 try {
			 	if (TestRun.isMobile())
			 		fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else if (TestRun.isDesktop())
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver());
			 	
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);				
				fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frmaeIndex));
				
	            status = true;
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to frame: " + frmaeIndex);
		 }
		 
		 return status;
	 }
	
	 /**********************************************************************************************
	  * Switch to default content
	  * 
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean switchToDefaultContent() {
		 boolean status = false;
		
		 try {
			 	if (TestRun.isMobile())
			 		DriverManagerUtil.getAppiumDriver().switchTo().defaultContent();				 		            
				else if (TestRun.isDesktop())
					DriverManagerUtil.getWebDriver().switchTo().defaultContent();			 		            
			
	             status = true;
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to default content.");
		 }

		 return status;
	 }
	 
	 /**********************************************************************************************
	  * Switch to child window
	  * 
	  * @return winHandleParent {@link String} - Parent window handle
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static int getCountOfWindowHandles() {
		Set<String> winHandles = null;
		int countOfWindows = 0;
	
		try {
				if (TestRun.isMobile())  {								 		          	
					winHandles = DriverManagerUtil.getAppiumDriver().getWindowHandles();							
					countOfWindows =  winHandles.size();
				}
				else if (TestRun.isDesktop()) {	
					winHandles = DriverManagerUtil.getWebDriver().getWindowHandles();							
					countOfWindows =  winHandles.size();				
				}
		} catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to child window.");
		}

		return countOfWindows;
	 }
	 
	 /**********************************************************************************************
	  * Switch to parent window
	  * 
	  * @param winHandleParent {@link String} - Parent window handle
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean switchToParentWindow(String winHandleParent) {
		 boolean status = false;
		 
		 try {
				 if (TestRun.isMobile()) {
					DriverManagerUtil.getAppiumDriver().switchTo().window(winHandleParent);
					status = true;
				 }
				 else if (TestRun.isDesktop()) {
					DriverManagerUtil.getWebDriver().switchTo().window(winHandleParent);
					status = true;
				 }
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to parent window.");
		 }
		 
		 return status;
	 }
	 
	 /**********************************************************************************************
	  * Switch to child window
	  * 
	  * @return winHandleParent {@link String} - Parent window handle
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static String switchToChildWindow() {
		String winHandleParent = null;
	
		try {
				if (TestRun.isMobile()) {
					winHandleParent  = DriverManagerUtil.getAppiumDriver().getWindowHandle();				 		            
		
					for(String winHandle : DriverManagerUtil.getAppiumDriver().getWindowHandles()){
						DriverManagerUtil.getAppiumDriver().switchTo().window(winHandle);
					}
				}
				
				else if (TestRun.isDesktop()) {
					winHandleParent  = DriverManagerUtil.getWebDriver().getWindowHandle();				 		            
		
					for(String winHandle : DriverManagerUtil.getWebDriver().getWindowHandles()){
						DriverManagerUtil.getWebDriver().switchTo().window(winHandle);
					}
				}
		} catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to child window.");
		}

		return winHandleParent;
	 }
	 
	 /**********************************************************************************************
	  * Close child window
	  * 
	  * @param winHandleParent {@link String} - Parent eindow handle
	  * @return status {@link boolean} - Status
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean closeAllChildWindows(String winHandleParent) {	
		 boolean status = false;

		 DriverManagerUtil.getWebDriver().switchTo().window(winHandleParent);		 
		 try {
				if (TestRun.isMobile()) {
					winHandleParent  = DriverManagerUtil.getAppiumDriver().getWindowHandle();				 		            
		
					for(String winHandle : DriverManagerUtil.getAppiumDriver().getWindowHandles()){
						DriverManagerUtil.getAppiumDriver().switchTo().window(winHandle).close();
						status = true;
					}
				}
				
				else if (TestRun.isDesktop()) {
					winHandleParent  = DriverManagerUtil.getWebDriver().getWindowHandle();				 		            
		
					for(String winHandle : DriverManagerUtil.getWebDriver().getWindowHandles()){
						DriverManagerUtil.getWebDriver().switchTo().window(winHandle).close();
						status = true;
					}
				}
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to close child window.");
		 }

		 return status;
	 }
	      
    /**********************************************************************************************
     * Waits for web element visibility
     * 
     * @param webElement {@link WebElement} - WebElement to wait for visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForElementToBeVisible(WebElement webElement, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	
    	try {				
	    		if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()); 				
					
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);			
				fluentWait.until(ExpectedConditions.visibilityOf(webElement));				
				scrollIntoView(webElement);
				
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    public static boolean waitForElementToBeVisible(By webElement, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	
    	try {				
	    		if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()); 				
					
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);			
				fluentWait.until(ExpectedConditions.visibilityOfElementLocated(webElement));				
				
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
   
    /**********************************************************************************************
     * Waits for web child elements visibility
     * 
     * @param webElement {@link WebElement} - Parent WebElement
     * @param childLocator {@link By} - Child WebElement locator
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForChildElementsToBeVisible(WebElement webElement, By childLocator, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	 
	    try {				
	    		if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()); 				
					
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);										
				fluentWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(webElement, childLocator));											
							
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}    
    
    /**********************************************************************************************
     * Waits for web element to be clickable
     * 
     * @param webElement {@link WebElement} - WebElement to be clickable
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForElementToBeClickable(WebElement webElement, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	     	
    	try {				
	    		if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()); 				
					
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);			
				fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));													
				scrollIntoView(webElement);
				
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element to be clickable by locator
     * 
     * @param locator {@link By} - Locator of the WebElement to wait for visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForElementToBeClickableByLocator(By locator, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	
    	try {				
	    		if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver());
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()); 				
					
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);			
				fluentWait.until(ExpectedConditions.elementToBeClickable(locator));					
				
				status = true;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
    }
    
    /**********************************************************************************************
     * Waits for web element to be non visible
     * 
     * @param webElement {@link WebElement} - WebElement to wait for non visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForInvisibilityOfElement(WebElement webElement, int timeOut) {
    	boolean status = false;
		FluentWait<RemoteWebDriver> fluentWait;
		
    	try {	  	
    			if (TestRun.isMobile())
    				fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver()); 
    			else
    				fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver());
    			
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);			
				fluentWait.until(ExpectedConditions.invisibilityOf(webElement));	
								
				status = true;							
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element to be non visible
     * 
     * @param locator {@link By} - Locator of the WebElement to wait for non visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForInvisibilityOfElementByLocator(By locator, int timeOut) {
    	boolean status = false;
		FluentWait<RemoteWebDriver> fluentWait;
		
    	try {	  	
    			if (TestRun.isMobile())
    				fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver()); 
    			else
    				fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver());
    			
				fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class);				
				fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));			
				
				status = true;						
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return status;
	}
    
    /**********************************************************************************************
	  * Waits for element to have specific attribute value.
	  * 
	  * @param webElement {@link WebElement} - WebElement to verify attribute
	  * @param attribute {@link String} - The specific attribute type to evaluate
	  * @param attributeValue {@link String} - The value of the attribute to evaluate
	  * @param timeOut {@link int} - The value of the timeout
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean waitForAttributeContains(WebElement webElement, String attribute, String attributeValue, int timeOut) {
	    boolean status = false;   
	    FluentWait<RemoteWebDriver> fluentWait;

	    try {	         
		    	if (TestRun.isMobile())
					fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver()); 
				else
					fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver()) ;				
					
	    		fluentWait.withTimeout(Duration.ofSeconds(timeOut))
		        .pollingEvery(Duration.ofMillis(Config.POLLING_TIME))
		        .ignoring(NoSuchElementException.class);	         
	    		fluentWait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));                  
	    		scrollIntoView(webElement);
	    		
	    		status = true;             
	    } catch (Exception e) {                  
             //e.printStackTrace();
	    }
	    return status;
	 } 
	 
     /**********************************************************************************************
	  * Find element by locator
	  * 
	  * @param locator {@link By} - WebElement locator	 
	  * @param timeOut {@link int} - The value of the timeout
	  * @return webElement {@link WebElement} - WebElement
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static WebElement findElementByLocator(By locator, int timeOut) {	
		 WebElement webElement = null;
			
		 WebInteractUtil.waitForElementToBeClickableByLocator(locator, timeOut);		
		 try {    
				if (TestRun.isMobile())
					webElement = DriverManagerUtil.getAppiumDriver().findElement(locator); 
				else
					webElement = DriverManagerUtil.getWebDriver().findElement(locator);
				
				scrollIntoView(webElement);
		  } catch (Exception e) {                  
			  //e.printStackTrace();
		  }		
						
		 return webElement;
	 }	
	 
	 /**********************************************************************************************
	  * Find elements by locator
	  * 
	  * @param locator {@link By} - WebElement locator	 
	  * @param timeOut {@link int} - The value of the timeout
	  * @return webElement {@link WebElement} - List of WebElements
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static List<WebElement> findElementsByLocator(By locator, int timeOut) {	
		List<WebElement> webElement = null;
			
		WebInteractUtil.waitForElementToBeClickableByLocator(locator, timeOut);		
		try {    				
				webElement = DriverManagerUtil.getWebDriver().findElements(locator);						
		} catch (Exception e) {                  
			  //e.printStackTrace();
		}		
						
		return webElement;
	 }
	 
	 /**********************************************************************************************
	  * Find child element by locator
	  * 
	  * @param parentWebElement {@link WebElement} - Parent element
	  * @param childLocator {@link By} - Locator of child element	 
	  * @param timeOut {@link int} - The value of the timeout
	  * @return webElement {@link WebElement} - Child webElement
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static WebElement findChildElementByLocator(WebElement parentWebElement, By childLocator, int timeOut) {	
		 WebElement childWebElement = null;
		
		 WebInteractUtil.waitForChildElementsToBeVisible(parentWebElement, childLocator, timeOut);
		 try {    
				childWebElement = parentWebElement.findElement(childLocator);
				scrollIntoView(childWebElement);
		 } catch (Exception e) {                  
             //e.printStackTrace();
		 }	
							
		 return childWebElement;
	 }
			 
	 /**********************************************************************************************
	  * Find child elements by locator
	  * 
	  * @param parentWebElement {@link WebElement} - Parent WebElement
	  * @param childLocator {@link By} - WebElement locator	 
	  * @param timeOut {@link int} - The value of the timeout
	  * @return webElement {@link WebElement} - Child WebElement
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static List<WebElement> findChildElementsByLocator(WebElement parentWebElement, By childLocator, int timeOut) {	
		 List<WebElement> childWebElements = null;
		
		 WebInteractUtil.waitForChildElementsToBeVisible(parentWebElement, childLocator, timeOut);
		 try {    
				childWebElements = parentWebElement.findElements(childLocator);
		 } catch (Exception e) {                  
            //e.printStackTrace();
		 }	
							
		 return childWebElements;
	 }
	 	 
	 /**********************************************************************************************
	  * Find element in shadow root
	  * 
	  * @param cssLocator {@link String} - CSS locator of the WebElement
	  * @return webElement {@link WebElement} - WebElement
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static WebElement findElementInShadowRoot(String cssLocator) {			
		 WebElement webElement = null; 
		 
		 try {    
			 	Shadow shadow = new Shadow(DriverManagerUtil.getWebDriver()); 
			 	webElement = shadow.findElement(cssLocator);
			 	scrollIntoView(webElement);
		 } catch (Exception e) {                  
            //e.printStackTrace();
		 }	
							
		 return webElement;
	 }
	 
	 /**********************************************************************************************
	  * Find elements in shadow root
	  * 
	  * @param cssLocator {@link String} - CSS locator of the WebElements
	  * @return webElement {@link WebElement} - List of WebElements
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static List<WebElement> findElementsInShadowRoot(String cssLocator) {			
		 List<WebElement> webElement = null; 
		 
		 try {    
			 	Shadow shadow = new Shadow(DriverManagerUtil.getWebDriver()); 
			 	webElement = shadow.findElements(cssLocator);
		 } catch (Exception e) {                  
            //e.printStackTrace();
		 }	
							
		 return webElement;
	 }
    
	 /**********************************************************************************************
	  * Scroll to element
	  * 
	  * @param webElement {@link WebElement} - WebElement to verify attribute
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean scrollIntoView(WebElement webElement) {
		 boolean status = false;
	    	
		 try {
				if (TestRun.isMobile())
				 	((JavascriptExecutor) DriverManagerUtil.getAppiumDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
				else if (TestRun.isDesktop())
				 	((JavascriptExecutor) DriverManagerUtil.getWebDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded();", webElement);
					
				status = true;				
		 } catch (Exception e) {
			//e.printStackTrace();
		 }
			
		 return status;
	 }
		
	 /**********************************************************************************************
	  * Scroll by pixel
	  * 
	  * @param pixel {@link int} - Pixel to scroll
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 30, 2018 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean scrollByPixel(int pixel) {
		 boolean status = false;
	    	
		 try {
				if (TestRun.isMobile())
				 	((JavascriptExecutor) DriverManagerUtil.getAppiumDriver()).executeScript("window.scrollBy(0, " + pixel + ")");
				else if (TestRun.isDesktop())
				 	((JavascriptExecutor) DriverManagerUtil.getWebDriver()).executeScript("window.scrollBy(0, " + pixel + ")");
					
				status = true;			
		 } catch (Exception e) {
			//e.printStackTrace();
		 }	
		 
		 return status;
	 }
	
	 /**********************************************************************************************
     * Verifies element is present
     * 
     * @param webElement {@link WebElement} - WebElement to wait for visibility
     * @param timeOut {@link Integer} - The amount of time in milliseconds to pause.
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 30, 2018 
     * @version 1.0 May 30, 2018   
     ***********************************************************************************************/    
   	public static boolean isPresent(WebElement webElement, int timeOut) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, timeOut);   
	   	try {
	   			status = webElement.isDisplayed();
	   	} catch (NoSuchElementException ne) {
			return false;
		} catch(Exception e) {
			e.printStackTrace();
	   	}
			  
	    return status;
   	} 
	      	
   	/**********************************************************************************************
     * Verifies element is enabled
     * 
     * @param webElement {@link WebElement} - WebElement to verify if enabled
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 30, 2018 
     * @version 1.0 May 30, 2018
     ***********************************************************************************************/    
   	public static boolean isEnabled(WebElement webElement) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);   
	   	try {
	   			status = webElement.isEnabled();				 		    
	   	} catch(Exception e) {
			//e.printStackTrace();
	   	}
	   	
	   	return status;
   	} 
   	
   	/**********************************************************************************************
     * Verifies element is selected
     * 
     * @param webElement {@link WebElement} - WebElement to verify if selected
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 30, 2018 
     * @version 1.0 May 30, 2018
     ***********************************************************************************************/    
   	public static boolean isSelected(WebElement webElement) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);   
	   	try {
	   			status = webElement.isSelected();				 		    
	   	} catch(Exception e) {
			//e.printStackTrace();
	   	}	
	   	
	   	return status;
   	} 
   	
   	/**********************************************************************************************
	  * Waits for web element and clicks on it
	  * 
	  * @param webElement {@link WebElement} - WebElement to click
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 31, 2018
	  * @version 1.0 May 31, 2018
	  ***********************************************************************************************/
	 public static boolean click(WebElement webElement) {
    	boolean status = false;
    	
    	try {
				waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
				webElement.click();
				status = true;    			
		} catch (StaleElementReferenceException e1) {
			for (int i = 0; i <= 10; ++i) {
				try {
						waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
		    			webElement.click();					
						break;
				} catch (Exception e) {
					continue;
				}	
			}
		} catch (Exception e2) {
			//e2.printStackTrace();		
		}
    	
		return status;
	 }
    
	 /**********************************************************************************************
	  * Clicks the identified web element by javascript.
	  * 
	  * @param webElement {@link WebElement} - WebElement to click
	  * @return status {@link boolean} - true/false
	  * @author Savita Tambe created May 11, 2018
	  * @version 1.0 May 11, 2018
	  ***********************************************************************************************/
     public static boolean clickByJS(WebElement webElement) {
    	 boolean status = false;     
    	 JavascriptExecutor js = null;
    	 
    	 try {
			 	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
			 
			 	if (TestRun.isMobile())
					js = (JavascriptExecutor) DriverManagerUtil.getAppiumDriver();
			 	else if (TestRun.isDesktop())
		    		js = (JavascriptExecutor) DriverManagerUtil.getWebDriver();    	    	
			 	
	 			js.executeScript("arguments[0].click();", webElement);
	 			status = true;     			
	     } catch (Exception e) {
	    	 //e.printStackTrace();
	     }	
    	 
		 return status;
     }
     
     /**********************************************************************************************
      * Waits for web element and clears text in it
      * 
      * @param webElement {@link WebElement} - WebElement to clear text
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 31, 2018
      * @version 1.0 May 31, 2018
      ***********************************************************************************************/
     public static boolean clear(WebElement webElement) {
     	boolean status = false;
 		
     	try {
				waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
				webElement.clear();
				status = true;
 		} catch (Exception e) {
 			//e.printStackTrace();
 		} 		
 		return status;
 	}
        
    /**********************************************************************************************
     * Waits for web element and set text in it
     * 
     * @param webElement {@link WebElement} - WebElement to enter text
     * @param text {@link String} - Text to enter
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean sendKeys(WebElement webElement, CharSequence... text) {
    	boolean status = false;
      	
    	try {
				waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
				webElement.clear();
				webElement.sendKeys(text);
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
    	
		return status;
	}
    
    /**********************************************************************************************
     * Sends text in element with javascript.
     * 
     * @param webElement {@link WebElement} - WebElement to click
     * @param value {@link String} - Value too be entered
     * @author Savita Tambe created May 11, 2018
     * @version 1.0 May 11, 2018
     ***********************************************************************************************/
    public static void sendKeysByJS(WebElement webElement, String value) {
    	JavascriptExecutor js = null;
   	
    	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
		if (TestRun.isMobile())
			js = (JavascriptExecutor) DriverManagerUtil.getAppiumDriver();
		else if (TestRun.isDesktop())
			js = (JavascriptExecutor) DriverManagerUtil.getWebDriver();

    	js.executeScript("arguments[0].value='"+ value +"';", webElement);
    }
      
    /**********************************************************************************************
     * Send keyboard events
     * 
     * @param webElement {@link WebElement} - WebElement to enter text
     * @param key {@link Keys} - Key to enter
     * @param timeout {@link int} - Timeout
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
     public static boolean sendKeyboardEvents(WebElement webElement, Keys key, int timeout) {
         boolean status = false;
          
         try {
                 waitForElementToBeClickable(webElement, timeout);                   
                 webElement.sendKeys(key);
                 status = true;
         } catch (Exception e) {
             //e.printStackTrace();
         }   
        
         return status;
     }
     
     /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param webElement {@link WebElement} - WebElement to select value
     * @param index {@link int} - Value index to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByIndex(WebElement webElement, int index) {
    	boolean status = false;
    	
    	try {
				waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);		
				Select listBox = new Select(webElement);
				listBox.selectByIndex(index);
				status = true;				
		} catch (Exception e) {
			//e.printStackTrace();
		}		
		
    	return status;
	}      
    
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param webElement {@link WebElement} - WebElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByValue(WebElement webElement, String value) {
    	boolean status = false;
      	    	 
    	try {
    			waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(webElement);
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
     * @param webElement {@link WebElement} - WebElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByVisibleText(WebElement webElement, String value) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(webElement);
				listBox.selectByVisibleText(value);			
				status = true;				
		} catch (Exception e) {
			//e.printStackTrace();
		}
    	
		return status;
	}

    /**********************************************************************************************
     * Fetches elements text value
     * 
     * @param webElement {@link WebElement} - WebElement to get attribute
     * @return text {@link String} - The value   
     * @author Savita Tambe created May 30, 2018 
     * @version 1.0 May 30, 2018     
     ***********************************************************************************************/
     public static String getText(WebElement webElement) {
    	String text = "";   
    	
    	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
 	   	try {
 	   			text = webElement.getText();
        } catch (Exception e) {
        	//e.printStackTrace();
        } 
 	   	
        return text;
     }
    
    /**********************************************************************************************
     * Determines if an element contains specific text value or not.
     * 
     * @param webElement {@link WebElement} - WebElement to verify text 
     * @param text {@link String} - Text to evaluate
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created Maay 08, 2018 
     * @version 1.0 May 08, 2018
     ***********************************************************************************************/
    public static boolean verifyText(WebElement webElement, String text) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
	   	try { 	   		
	   			status = webElement.getText().equalsIgnoreCase(text);  
        } catch (Exception e) {        	                
        	//e.printStackTrace();
        }
	   	
        return status;
    }
        
    /**********************************************************************************************
     * Determines if an element has partial match of text value or not.
     * 
     * @param webElement {@link WebElement} - WebElement to verify text 
     * @param text {@link String} - Text to evaluate
     * @return status {@link boolean} - true/false
     * @author Savita Tambe created Maay 08, 2018 
     * @version 1.0 May 08, 2018
     ***********************************************************************************************/
    public static boolean verifyTextContains(WebElement webElement, String text) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
	   	try { 	   		
	   			status = webElement.getText().contains(text);  
        } catch (Exception e) {        	                
        	//e.printStackTrace();
        }
	   	
        return status;
    }
    
    /**********************************************************************************************
     * Fetches elements specific attribute value
     * 
     * @param webElement {@link WebElement} - WebElement to get attribute
     * @param attribute {@link String} - The specific attribute type to fetch value   
     * @return attributeValue {@link String} - The specific attribute value   
     * @author Savita Tambe created May 30, 2018 
     * @version 1.0 May 30, 2018     
     ***********************************************************************************************/
     public static String getAttribute(WebElement webElement, String attribute) {
    	String attributeValue = "";   
    	
    	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
 	   	try {
 	   			attributeValue = webElement.getAttribute(attribute);
         } catch (Exception e) {
        	//e.printStackTrace();
         } 	 
 	   	
         return attributeValue;
     }
        
     /**********************************************************************************************
      * Determines if an element has a specific attribute value or not.
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param attribute {@link String} - The specific attribute type to evaluate
      * @param attributeValue {@link String} - The value of the attribute to evaluate
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 30, 2018 
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static boolean verifyAttributeValue(WebElement webElement, String attribute, String attributeValue) {
      	boolean status = false;
      	
      	waitForAttributeContains(webElement, attribute, attributeValue, Config.MEDIUM_PAUSE);
   	   	try { 	   		
   	   			status = webElement.getAttribute(attribute).equalsIgnoreCase(attributeValue);  
   	   	} catch (Exception e) {        	                
   	   		//e.printStackTrace();
   	   	} 
   	   	
        return status;
     }
       
     /**********************************************************************************************
      * Determines if an element has a specific attribute value or not.
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param attribute {@link String} - The specific attribute type to evaluate
      * @param attributeValue {@link String} - The value of the attribute to evaluate
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 30, 2018 
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static boolean verifyAttributeContains(WebElement webElement, String attribute, String attributeValue) {
    	 boolean status = false;   

    	 waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);    	
    	 try {
		  	status = webElement.getAttribute(attribute).toUpperCase().contains(attributeValue.toUpperCase());  	               	   	 	
	   	 } catch (Exception e) {  	   		  
	   		 //e.printStackTrace();
	   	 }
    	 
    	 return status;
     }
    
     /**********************************************************************************************
      * Get CSS attribute value
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param cssAttribute {@link String} - The specific CSS attribute type to fetch    
      * @return attributeValue {@link String} - CSS Attribute value
      * @author Savita Tambe created May 30, 2018 
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static String getCSSAttributeValue(WebElement webElement, String cssAttribute) {
    	 String attributeValue = ""; 
      	
      	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
   	   	try { 	   		
   	   			attributeValue = webElement.getCssValue(cssAttribute);  
   	   	} catch (Exception e) {        	                
   	   		//e.printStackTrace();
	    }  
   	   	
        return attributeValue;
      }
     
	 /**********************************************************************************************
      * Determines if an element has a specific CSS attribute value or not.
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param cssAttribute {@link String} - The specific CSS attribute type to evaluate
      * @param cssAttributeValue {@link String} - The value of the CSS attribute to evaluate
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 30, 2018 
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static boolean verifyCSSAttributeValue(WebElement webElement, String cssAttribute, String cssAttributeValue) {
      	boolean status = false;
      	
      	waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
   	   	try { 	   		
   	   			status = webElement.getCssValue(cssAttribute).equalsIgnoreCase(cssAttributeValue);  
   	   	} catch (Exception e) {        	                
   	   		//e.printStackTrace();
	    }  
   	   	
        return status;
      }
     
     /**********************************************************************************************
      * Waits for web element and context clicks on it
      *
      * @param webElement {@link WebElement} - WebElement to context click
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 31, 2018
      * @version 1.0 May 31, 2018
      ***********************************************************************************************/
     public static boolean contextClick(WebElement webElement) {
        boolean status = false;
        Actions action = null;
       
        try {
                waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
                action = new Actions(DriverManagerUtil.getWebDriver());
                action.contextClick(webElement).perform();
                status = true;                   
        } catch (Exception e2) {
            //e2.printStackTrace();       
        }
       
        return status;
     }
    
     /**********************************************************************************************
      * Waits for web element and double clicks on it
      *
      * @param webElement {@link WebElement} - WebElement to double click
      * @return status {@link boolean} - true/false
      * @author Savita Tambe created May 31, 2018
      * @version 1.0 May 31, 2018
      ***********************************************************************************************/
     public static boolean doubleClick(WebElement webElement) {
            boolean status = false;
            Actions action = null;
           
            try {
                    waitForElementToBeClickable(webElement, Config.MEDIUM_PAUSE);
                    action = new Actions(DriverManagerUtil.getWebDriver());
                    action.doubleClick(webElement).perform();
                    status = true;                          
            } catch (Exception e2) {
                //e2.printStackTrace();       
            }
           
            return status;
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
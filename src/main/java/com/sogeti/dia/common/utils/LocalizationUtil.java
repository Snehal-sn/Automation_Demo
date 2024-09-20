package com.sogeti.dia.common.utils;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sogeti.dia.common.config.Config;

/**
 * Class to support localization
 * @author Savita Tambe
 *
 */
public class LocalizationUtil {  
	protected static final HashMap<String, String> localization = new HashMap<String, String>();
	protected static final HashMap<String, String> localizationEng = new HashMap<String, String>();
	
	
	/**********************************************************************************************
     * Sets language specific localization key and values in hashmap
     * 
     * @param language {@link String} - The specific language value for localization      
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
	public static void setLocalizationDetails(String language) {	
		String filePath = "";
		filePath = (Config.LOCALIZATION_FOLDER_PATH + language + ".xml");
			
		try {
				 File strings = new File(filePath);
				 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				 Document doc = dBuilder.parse(strings);
				 doc.getDocumentElement().normalize();				
				 NodeList nodes = doc.getDocumentElement().getElementsByTagName("string");
				 
				 for (int i = 0; i < nodes.getLength(); i++) {
					 Node node = nodes.item(i);	
					 String key = ((Element) node).getAttributeNode("name").toString().split("name=\"")[1].replaceAll("\"","");
					 localization.put(key, node.getTextContent());				
				 }				     			 		
				  
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Exception while fetching localization details: " + e);
		}		
	}
	
	/**********************************************************************************************
     * Sets english specific localization key and values in hashmap
     * 
     * @author Savita Tambe created May 31, 2018
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
	public static void setLocalizationDetailsEng() {
		File strings;
		
		try {				
				strings = new File((Config.LOCALIZATION_FOLDER_PATH + "English.xml"));				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(strings);
				doc.getDocumentElement().normalize();				
				NodeList nodes = doc.getDocumentElement().getElementsByTagName("string");
			 
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);	
					String key = ((Element) node).getAttributeNode("name").toString().split("name=\"")[1].replaceAll("\"","");
					localizationEng.put(key, node.getTextContent());				
				}				     			 						 
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Exception while fetching localization details for English: " + e);
		}		
	}
}   
package com.sogeti.dia.common.utils;


import java.net.URL;
import org.apache.http.client.utils.URIBuilder;

import com.sogeti.dia.common.config.Config;

import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.Voice;

/**
 * Class having web web application specific reusable methods
 * @author Savita Tambe
 *
 */
public class TextToSpeechUtil {
	/**********************************************************************************************
	 * Play Internet Voice File
	 *   
	 * @param url {@link String} - url
	 * @author Savita Tambe created Mar 2, 2019
	 * @version 1.0 Mar 2, 2019
	 ***********************************************************************************************/
    public static void playInternetVoiceFile(String url){
        try {
	            URL voiceUrl = new URL(url);
	            Voice voice = new Voice(voiceUrl);
	            VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
	            voiceAutomationClient.load(voice);
	            voiceAutomationClient.play(voice);
        }catch (Exception e){
            LoggerUtil.logErrorMessage("Text to Speech coversion exception: " + e);
        }
    }
    
    /**********************************************************************************************
     * Get Voice Url
     *   
	 * @param text {@link String} - URL of the Audio file
	 * @param audioFormat {@link String} - Audio File Format
	 * @param language {@link String} - Preferred Language
	 * @return voiceUrl {@link String} - URL of audio file
	 * @author Savita Tambe created Mar 2, 2019
	 * @version 1.0 Mar 2, 2019
	 ***********************************************************************************************/
	public static String getVoiceUrl(String text, String audioFormat, String language) {
        String voiceUrl = null;
        try {
	            URIBuilder uriBuilder = new URIBuilder(Config.VOICERSS_BASEURL);
	            uriBuilder.addParameter("key", Config.VOICE_RSS_API_KEY);
	            uriBuilder.addParameter("src", text);
	            uriBuilder.addParameter("c", audioFormat);
	            uriBuilder.addParameter("hl", language);
	            
	            voiceUrl = uriBuilder.toString();
        } catch (Exception e) {
        	LoggerUtil.logErrorMessage("Text to Speech get URL exception: " + e);
        }
        return voiceUrl;
    }
}


/*Language code	Language name
	ca-es	Catalan
	zh-cn	Chinese (China)
	zh-hk	Chinese (Hong Kong)
	zh-tw	Chinese (Taiwan)
	da-dk	Danish
	nl-nl	Dutch
	en-au	English (Australia)
	en-ca	English (Canada)
	en-gb	English (Great Britain)
	en-in	English (India)
	en-us	English (United States)
	fi-fi	Finnish
	fr-ca	French (Canada)
	fr-fr	French (France)
	de-de	German
	it-it	Italian
	ja-jp	Japanese
	ko-kr	Korean
	nb-no	Norwegian
	pl-pl	Polish
	pt-br	Portuguese (Brazil)
	pt-pt	Portuguese (Portugal)
	ru-ru	Russian
	es-mx	Spanish (Mexico)
	es-es	Spanish (Spain)
	sv-se	Swedish (Sweden)
*/

/*Audio codec:
	MP3
	WAV
	AAC
	OGG
	CAF
*/
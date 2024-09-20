package com.sogeti.dia.common.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.testng.asserts.SoftAssert;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.sogeti.dia.common.config.TestRun;


public class GalenReporterUtil {
	public static SoftAssert softAssert;
    protected static List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

    public GalenReporterUtil() {
		softAssert = new SoftAssert();
	}

    /**********************************************************************************************
     * Check Layout of the page
     *
     * @param gspecFilePath {@link String} - gSpec File path
     * @param tags {@link List} - Tags
     * @param pageName {@link String} - Page Name
     * @author Savita Tambe created May 21, 2018
     * @version 1.0 May 21, 2018
     ***********************************************************************************************/
	public void checkLayout(String gspecFilePath, List<String> tags, String pageName) {
		LayoutReport layoutReport = null;

		try {
				if (TestRun.isMobile())
 					layoutReport = Galen.checkLayout(DriverManagerUtil.getAppiumDriver(), gspecFilePath, tags);
				else if (TestRun.isDesktop())
					layoutReport = Galen.checkLayout(DriverManagerUtil.getWebDriver(), gspecFilePath, tags);

		} catch (IOException e) {
			//TODO Auto-generated catch block
		}

		generateGalenTestReport(layoutReport, pageName);
	}


    /**********************************************************************************************
     * To generate Galen Test Report
     *
     * @param layoutReport {@link LayoutReport} - LayoutReport
     * @param pageName {@link String} - Page Name
     * @author Savita Tambe created May 21, 2018
     * @version 1.0 May 21, 2018
     ***********************************************************************************************/
	public static void generateGalenTestReport(LayoutReport layoutReport, String pageName) {
	    //Create a tests list
		try {
				GalenTestInfo test = null;

			    //Create a GalenTestInfo object
				if (TestRun.isMobile()) {
					if (TestRun.getDeviceName().contains("pad") || TestRun.getDeviceName().contains("tab"))
						test = GalenTestInfo.fromString(pageName + " on tablet "  + TestRun.getDeviceName());
					else
						test = GalenTestInfo.fromString(pageName + " on mobile "  + TestRun.getDeviceName());
				}
				else if (TestRun.isDesktop())
					test = GalenTestInfo.fromString(pageName + " on desktop "  + TestRun.getBrowserName());

			    //Get layoutReport and assign to test object
			    test.getReport().layout(layoutReport, "Check " + pageName + " layout");

			    //Add test object to the tests list
			    tests.add(test);

			    //If layoutReport has errors Assert Fail
			    if (layoutReport.errors() > 0)
			    {
			        softAssert.fail(pageName + " Layout test failed");
			    }
		} catch (Exception e) {
			//TODO Auto-generated catch block
		}
	}


    /**********************************************************************************************
     * Generate Galen Suite Report
     *
     * @author Savita Tambe created May 21, 2018
     * @version 1.0 May 21, 2018
     ***********************************************************************************************/
	public static void generateGalenSuiteReport() {
	    //Create a htmlReportBuilder object
	    HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();

	    //Create a report under /target folder based on tests list
	    try {
				htmlReportBuilder.build(tests, "target//galen-html-reports");
		} catch (IOException e) {
			//TODO Auto-generated catch block
		}
	}
}
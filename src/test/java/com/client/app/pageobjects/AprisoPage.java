package com.client.app.pageobjects;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class AprisoPage {
//***************************  Home page    ************************************************************
    @FindBy(xpath = "//input[@id='ctl04_LoginTextBox']")
    public WebElement Name;

    @FindBy(xpath = "//input[@id='ctl04_PasswordTextbox']")
    public WebElement Password;

    @FindBy(xpath = "//input[@id='ctl04_LogInButton']")
    public WebElement LogIn;

    //*************************   Oder CockPit Page    ********************************************************

    @FindBy(xpath = "//input[@class='search-box-input ng-pristine ng-untouched ng-valid']")
    public WebElement SearchBox;
    //div[@class='apr-icon-search']

    @FindBy(xpath = "//div[@class='apr-icon-search']")
    public WebElement Searchbar;
    @FindBy(xpath = "(//div[@class='content ng-scope active'] //div[contains(text(),'Order Cockpit')])[1]")
    public WebElement OrderCockpit;

    @FindBy(xpath = "//button[@class='ToolButton apr-button CREATE_NEW']")
    public WebElement CreateNew;

    @FindBy(xpath = "//div[contains(text(),'WIP Order No')]")
    public WebElement WIPOrderNo;

    @FindBy(xpath = "//span[contains(text(),'BDL0010-30-0-CM01')]")
    public WebElement SelectValue;

    @FindBy(xpath = "//span[contains(text(),'OK')]")
    public WebElement OkButton;

    @FindBy(xpath = "//button[@class='apr-button apr-primary']")
    public WebElement SaveButton;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-WipOrderNo']")
    public WebElement WipOrderNo;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ExternalOrderNo']")
    public WebElement ExternalOrderNo;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Project']")
    public WebElement Project;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-OrderType']")
    public WebElement OrderType;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Facility']")
    public WebElement Facility;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Process']")
    public WebElement Process;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ProcessRevision']")
    public WebElement ProcessRevision;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ExplosionType']")
    public WebElement ExplosionType;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ProductNo']")
    public WebElement ProductNo;
    @FindBy(xpath = "//button[@value='LINK_PRODUCT']")
    public WebElement Productbutton;

    @FindBy(xpath = "//input[@name='filter~BC__sf_panel_popup_data_flx_bc_Grid_flx_iterate_0_Control~productno']")
    public WebElement productFilter;
    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-Quantity']")
    public WebElement Quantity;

    @FindBy(xpath = "FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ProductRevision")
    public WebElement ProductRevision;

    @FindBy(xpath = "FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-UOM")
    public WebElement UOM;

    @FindBy(xpath = "//input[@class='FC2  binding_Screen_PANEL_Call_PortalGenerateForm_-ProductDesc']")
    public WebElement ProductDesc;

    @FindBy(xpath = "//textarea[@class='FC12  binding_Screen_PANEL_Call_PortalGenerateForm_-OrderDescription']")
    public WebElement OrderDescription;

    @FindBy(xpath = "//button[@class='apr-button apr-primary']")
    public WebElement Save;

    @FindBy(xpath = "//button[@value='ORD_SERIALS']")
    public WebElement serialbutton;

    @FindBy(xpath = "//button[@class='ToolButton apr-button ADDSERIAL']")
    public WebElement AddSerial;

    @FindBy(xpath = "//input[@class='tokenfield-input']")
    public WebElement AddSerialBox;

    @FindBy(xpath = "//button[@class='apr-button apr-primary']")
    public WebElement ClickOk;


    @FindBy(xpath = "(//div[@class='apr-la-space-me-left'])[1]")
    public WebElement ClickOnOrder;


    @FindBy(xpath = "//span[contains(text(),'Explode')]")
    public WebElement ClickOnExplode;

    @FindBy(xpath = "//button[@value='GENERAL']")
    public WebElement General;



    @FindBy(xpath = "//span[contains(text(),'Release Operations')]")
    public WebElement ReleaseOperations;

    @FindBy(xpath = "//button[@class='apr-button'] //span[contains(text(),'Start')]")
    public WebElement StartOrder;

    @FindBy(xpath = "//button[@class='apr-button'] //span[contains(text(),'Complete')]")
    public WebElement CompleteOrder;

    @FindBy(xpath = "(//div[contains(text(),'Completed')])[1]")
    public WebElement VerifyOrder;

//************************* operation Details Page *************************************************************************************************

    @FindBy(xpath = "//div[@class='apr-la-space-me-left'][contains(text(),'MONTAGE AV')]")
    public WebElement Operation1;

    @FindBy(xpath = "//div[@class='apr-la-space-me-left'][contains(text(),'MONTAGE AR')]")
    public WebElement Operation2;

    @FindBy(xpath = "//div[@class='apr-la-space-me-left'][contains(text(),'CONTROLE FINAL Rev2')]")
    public WebElement Operation3;

    @FindBy(xpath = "//span[contains(text(),'Release')]")
    public WebElement Release;



    @FindBy(xpath = "//span[contains(text(),'General')]")
    public WebElement VerifyGeneral;
    @FindBy(xpath = "//span[contains(text(),'Tools')]")
    public WebElement Tools;

    @FindBy(xpath = "//span[contains(text(),'Components')]")
    public WebElement Components;

    @FindBy(xpath = "//span[contains(text(),'Characteristics')]")
    public WebElement Characteristics;

    @FindBy(xpath = "//span[contains(text(),'Checklists')]")
    public WebElement Checklists;

    @FindBy(xpath = "//span[contains(text(),'Work Instructions')]")
    public WebElement WorkInstructions;

    @FindBy(xpath = "//span[contains(text(),'Non-Conformances')]")
    public WebElement NonConformances;

    @FindBy(xpath = "//span[contains(text(),'Issues')]")
    public WebElement Issues;

    @FindBy(xpath = "//span[contains(text(),'Documents')]")
    public WebElement Documents;

    @FindBy(xpath = "//span[contains(text(),'Attachments')]")
    public WebElement Attachments;

    @FindBy(xpath = "//span[contains(text(),'Comments')]")
    public WebElement Comments;

    @FindBy(xpath = "//span[@class='fa fa-download']")
    public WebElement Download;

    @FindBy(xpath = "//input[@id='dynGrid_option_1']")
    public WebElement pdf;

    @FindBy(xpath = "//input[@id='dynGrid_option_3']")
    public WebElement AllRecoed;


    @FindBy(xpath = "//button[contains(text(),'Export')]")
    public WebElement Export;

    @FindBy(xpath = "(//img[@src='api/badges/employee?employeeNo=shubhase'])[1]")
    public WebElement LogOutImg;

    @FindBy(xpath = "(//span[contains(text(),'Logout')])[2]")
    public WebElement Logout;

    @FindBy(xpath = "(//div[@class='apr-scroller'])[2]")
    public WebElement Arrow;

    @FindBy(xpath = "(//div[@class='apr-scroller'])[1]")
    public WebElement BackArrow;
//*********************************   maintenance cockpit  *************************************************************************

    @FindBy(xpath = "(//div[@class='th screen-item text-center'] //div[contains(text(),'Maintenance Cockpit')])[1]\n")
    public WebElement MentenanceCockpit;




//**********************************  order details   ****************************************************************************
    @FindBy(xpath = "//input[@name='filter~BC_SUB_PANEL_data_flx_bc_Grid_flx_iterate_0_Control~wiporderno']")
    public WebElement FilterOrder;

   @FindBy(xpath = "(//span[contains(text(),'1505')])[1]")
    public WebElement ClickOnOrder1;

    @FindBy(xpath = "//button[@class='ToolButton apr-button SELECT apr-primary T1 ']")
    public WebElement Details;
//
//    @FindBy(xpath = "")
//    public WebElement ;
































































    public AprisoPage(){
        if (TestRun.isMobile())
            PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
        else if (TestRun.isDesktop())
            PageFactory.initElements(DriverManagerUtil.getWebDriver(), this);

    }

}

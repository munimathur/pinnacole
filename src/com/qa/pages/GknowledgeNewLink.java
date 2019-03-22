package com.qa.pages;

import static org.testng.Assert.fail;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.testbase.TestBase;

public class GknowledgeNewLink extends TestBase{

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String sheetName = "contacts";

	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		LoginGknowledge();

	}

	@Test(priority = 1)
	/***************************************************************************************************************
	Test Case : TC-02
	To verify if User can view content on G_ Knowledge

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be redirected To G_ Knowledge page and  should be able to view  following content
	1) menu icon -
	2)contribute and post a query - 
	3)G_ Knowledge loGo - 
	4)share and follow - unable to find share
	5)search box  - 
	6)find content and find an internal  expert -
	7)search within -
	8) new releAse and most popular - 
	9)view by and view all - 
	10)horizontal slider 
	11)search tips icon -
	12)Book a demo -
	13)about G_ Knowledge - 
	14)technical  Assistance- 
	15)terms of use - 
	16)FAQ - 
	17)feedback - 
	 ***************************************************************************************************************/
	public void verifyGknowledgeElements() throws Exception {

		List<WebElement> MenuItems= driver.findElements(By.xpath("//div[@id='topnavigationDiv']//div//button"));
		System.out.println(MenuItems.size());


		for (int i = 0; i < MenuItems.size(); i++) {
			System.out.println(MenuItems.get(i).getText());
			Assert.assertEquals(MenuItems.get(0).getText(), "Industry ", "Industry");
		}

		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(1).getText(), "Service Lines ", "Service Lines ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(2).getText(), "Capabilities ", "Capabilities ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(3).getText(), "Growth Enablement ", "Growth Enablement ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(4).getText(), "Useful Links ", "Useful Links ");	
		}

		String Contribute=driver.findElement(By.xpath("//span[contains(text(),'Contribute')]")).getText();
		Assert.assertEquals(Contribute, " Contribute", " Contribute");

		String PostAQuery=driver.findElement(By.xpath("//span[contains(text(),'Post a Query')]")).getText();
		Assert.assertEquals(PostAQuery, " Post a Query", " Post a Query");

		boolean GknowledgeLogoimg=driver.findElement(By.xpath("//img[@class='logoImageClass']")).isDisplayed();
		Assert.assertEquals(GknowledgeLogoimg, true, "GknowledgeLogoimg");

		String Follow=driver.findElement(By.xpath("//span[@class='btnTxt dispNan'][contains(text(),'Follow')]")).getText();
		Assert.assertEquals(Follow, " Follow", "Follow");

		String SearchGknowledge=driver.findElement(By.xpath("//input[contains(@title, 'Search Gknowledge')]")).getAttribute("title");
		Assert.assertEquals(SearchGknowledge, "Search Gknowledge", "Search Gknowledge");

		String FindContains=driver.findElement(By.xpath("//a[@id='btnFndCntnt']")).getText();
		Assert.assertEquals(FindContains, "Find Content", "Find Content");

		String FindInternalExpert=driver.findElement(By.xpath("//a[@id='btnFndExprt']")).getText();
		Assert.assertEquals(FindInternalExpert, "Find an Internal Expert", "Find an Internal Expert");

		String SearchWithIn=driver.findElement(By.xpath("//label[@id='selectComLbl']")).getText();
		Assert.assertEquals(SearchWithIn, "Search within:", "SearchWithIn");

		boolean SearchWithInDropdown=driver.findElement(By.xpath("//select[@id='slct_Comm']")).isDisplayed();
		Assert.assertEquals(SearchWithInDropdown, true, "SearchWithInDropdown");

		String NewRealease=driver.findElement(By.xpath("//a[@id='lnkNew']")).getText();
		Assert.assertEquals(NewRealease, "New Releases", "New Releases");

		String MostPopular=driver.findElement(By.xpath("//a[@id='lnkPop']")).getText();
		Assert.assertEquals(MostPopular, "Most Popular", "Most Popular");

		boolean ViewByDropdown=driver.findElement(By.xpath("//select[@id='comm_MP']")).isDisplayed();
		Assert.assertEquals(ViewByDropdown, true, "ViewByDropdown");

		String ViewAll=driver.findElement(By.xpath("//a[contains(text(),'View all')]")).getText();
		Assert.assertEquals(ViewAll, "View all", "View all");

		String SearchTips=driver.findElement(By.xpath("//span[@id='txt_srchTips']")).getText();
		Assert.assertEquals(SearchTips, " Search Tips", "SearchTips");

		String BookADemo=driver.findElement(By.xpath("//span[contains(text(),'Book a Demo')]")).getText();
		Assert.assertEquals(BookADemo, " Book a Demo", "BookADemo");

		boolean AboutGknowledge=driver.findElement(By.xpath("//a[@href='https://genpactonline.sharepoint.com/sites/GKnowledge/Pages/About-Knowledge.aspx']")).isDisplayed();
		Assert.assertEquals(AboutGknowledge, true, "AboutGknowledge");

		String TechAssistance=driver.findElement(By.xpath("//a[contains(text(),'Technical Assistance')]")).getText();
		Assert.assertEquals(TechAssistance, "Technical Assistance", "TechAssistance");

		String TermOfUse=driver.findElement(By.xpath("//a[@id='itm_TermsofUse']")).getText();
		Assert.assertEquals(TermOfUse, "Terms of Use", "TermOfUse");

		String FAQ=driver.findElement(By.xpath("//a[contains(text(),'FAQ')]")).getText();
		Assert.assertEquals(FAQ, "FAQ", "FAQ");

		String Feedback=driver.findElement(By.xpath("//a[contains(text(),'Feedback')]")).getText();
		Assert.assertEquals(Feedback, "Feedback", "Feedback");

	}

	@Test(priority = 2)
	/***************************************************************************************************************
	Test Case : TC-03
	To verify if User can view widget in menu

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be redirected To G_ Knowledge page and  should be able to view  following content
	User should be able To view following options:
	1)industry
	2)service lines
	3)capabilities
	4)growth enablement
	5)useful links
	 ***************************************************************************************************************/
	public void verifyGknowledgeMenuElements() throws Exception {

		List<WebElement> MenuItems= driver.findElements(By.xpath("//div[@id='topnavigationDiv']//div//button"));
		System.out.println(MenuItems.size());


		for (int i = 0; i < MenuItems.size(); i++) {
			System.out.println(MenuItems.get(i).getText());
			Assert.assertEquals(MenuItems.get(0).getText(), "Industry ", "Industry");
		}

		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(1).getText(), "Service Lines ", "Service Lines ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(2).getText(), "Capabilities ", "Capabilities ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(3).getText(), "Growth Enablement ", "Growth Enablement ");	
		}
		for (int i = 0; i < MenuItems.size(); i++) {
			Assert.assertEquals(MenuItems.get(4).getText(), "Useful Links ", "Useful Links ");	
		}

	}

	@Test(priority = 3)
	/***************************************************************************************************************
	Test Case : TC-04
	To verify if User is able To view options in industry

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view following options in dropdown:
	1)Banking and financial services
	2)capital market
	3)CGRLH
	4)Insurance
	 ***************************************************************************************************************/
	public void verifyOptionInIndustry() throws Exception {

		driver.findElement(By.xpath("//button[@id='tbIndustry']")).click();

		List<WebElement> ElementsUnderIndustry=driver.findElements(By.xpath("//div[@id='Industry']//div//a"));
		System.out.println(ElementsUnderIndustry.size());

		for (int i = 0; i < ElementsUnderIndustry.size(); i++) {
			System.out.println(ElementsUnderIndustry.get(i).getText());
			Assert.assertEquals(ElementsUnderIndustry.get(0).getText(), "Banking and Financial Services", "Banking and Financial Services");
		}

		for (int i = 0; i < ElementsUnderIndustry.size(); i++) {
			Assert.assertEquals(ElementsUnderIndustry.get(1).getText(), "CGRLH", "CGRLH");
		}

		for (int i = 0; i < ElementsUnderIndustry.size(); i++) {
			Assert.assertEquals(ElementsUnderIndustry.get(2).getText(), "Capital Markets", "Capital Markets");
		}

		for (int i = 0; i < ElementsUnderIndustry.size(); i++) {
			Assert.assertEquals(ElementsUnderIndustry.get(3).getText(), "Insurance", "Insurance");
		}


	}

	@Test(priority = 4)
	/***************************************************************************************************************
	Test Case : TC-05
	To verify if User is able To view options in service lines

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view following options in dropdown:
	1)account payable
	2)enterprise performance management
	3)Human Resource outsourcing
	4)omni channel customeracquisition and servicing
	5)order To cAsh
	6)record To report
	7)sourcing and procurement
	 ***************************************************************************************************************/
	public void verifyOptionInServiceLine() throws Exception {

		driver.findElement(By.xpath("//button[@id='tbServiceLines']")).click();

		List<WebElement> ElementsUnderServiceLine=driver.findElements(By.xpath("//div[@id='ServiceLines']//div//a"));
		System.out.println(ElementsUnderServiceLine.size());

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			System.out.println(ElementsUnderServiceLine.get(i).getText());
			Assert.assertEquals(ElementsUnderServiceLine.get(0).getText(), "Accounts Payable", "Accounts Payable");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(1).getText(), "Enterprise Performance Management", "Enterprise Performance Management");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(2).getText(), "Human Resource Outsourcing", "Human Resource Outsourcing");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(3).getText(), "Omni-Channel Customer Acquisition & Servicing", "Omni-Channel Customer Acquisition & Servicing");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(4).getText(), "Order To Cash", "Order To Cash");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(5).getText(), "Record To Report", "Record To Report");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(6).getText(), "Sourcing & Procurement", "Sourcing & Procurement");
		}

	}

	@Test(priority = 5)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify if User is able To view options in capabilities

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view following options in dropdown:
	User should be able To view following options in dropdown:
	1)analytics
	2)consulting
	3)data engineering(MDM)
	4)digital
	5)genpact cora
	6)IT services
	7)robotics process auTomation
	 ***************************************************************************************************************/
	public void verifyOptionInCapabilities() throws Exception {

		driver.findElement(By.xpath("//button[contains(text(),'Capabilities')]")).click();

		List<WebElement> ElementsUnderServiceLine=driver.findElements(By.xpath("//div[@id='Capabilities']//div//a"));
		System.out.println(ElementsUnderServiceLine.size());

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			System.out.println(ElementsUnderServiceLine.get(i).getText());
			Assert.assertEquals(ElementsUnderServiceLine.get(0).getText(), "Analytics", "Analytics");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(1).getText(), "Consulting", "Consulting");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(2).getText(), "Data Engineering (MDM)", "Data Engineering (MDM)");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(3).getText(), "Digital", "Digital");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(4).getText(), "Genpact Cora", "Genpact Cora");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(5).getText(), "IT Services", "IT Services");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(6).getText(), "Robotics Process Automation", "Robotics Process Automation");
		}

	}

	@Test(priority = 6)
	/***************************************************************************************************************
	Test Case : TC-07
	To verify if User  is able To view options in growth enablement

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view following options in dropdown:
	User should be able To view following options in dropdown:
	1)CFO service communication
	2)commercial proposals
	3)growth communications
	4)growth intelligence unit
	5)innovation by design
	6)lean digital transformation
	7)marketing
	 ***************************************************************************************************************/
	public void verifyOptionInGrowthEnablement() throws Exception {

		driver.findElement(By.xpath("//button[@id='tbGrowthEnablement']")).click();

		List<WebElement> ElementsUnderServiceLine=driver.findElements(By.xpath("//div[@id='GrowthEnablement']//div//a"));
		System.out.println(ElementsUnderServiceLine.size());

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			System.out.println(ElementsUnderServiceLine.get(i).getText());
			Assert.assertEquals(ElementsUnderServiceLine.get(0).getText(), "CFO Services Communications", "CFO Services Communications");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(1).getText(), "Commercial Proposals", "Commercial Proposals");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(2).getText(), "Growth Communications", "Growth Communications");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(3).getText(), "Growth Intelligence Unit", "Growth Intelligence Unit");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(4).getText(), "Innovation by Design", "Innovation by Design");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(5).getText(), "Lean Digital Transformation", "Lean Digital Transformation");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(6).getText(), "Marketing", "Marketing");
		}

	}

	@Test(priority = 7)
	/***************************************************************************************************************
	Test Case : TC-08
	To verify if User is able To view options in useful links

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view following options in dropdown:
	User should be able To view following options in dropdown:
	1)about Gknowledge
	2)external experts finder
	3)internal experts finder
	4)genpace
	5)lean digital and L&D
	6)performance enrichment system
	7)salesforce chatter
	 ***************************************************************************************************************/
	public void verifyOptionInUsefulLinks() throws Exception {

		driver.findElement(By.xpath("//button[@id='tbUsefulLinks']")).click();

		List<WebElement> ElementsUnderServiceLine=driver.findElements(By.xpath("//div[@id='UsefulLinks']//div//a"));
		System.out.println(ElementsUnderServiceLine.size());

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			System.out.println(ElementsUnderServiceLine.get(i).getText());
			Assert.assertEquals(ElementsUnderServiceLine.get(0).getText(), "About Gknowledge", "About Gknowledge");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(1).getText(), "External Experts Finder", "External Experts Finder");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(2).getText(), "Internal Experts Finder", "Internal Experts Finder");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(3).getText(), "GenPACE", "GenPACE");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(4).getText(), "Lean Digital L&D", "Lean Digital L&D");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(5).getText(), "Performance Enrichment System", "Performance Enrichment System");
		}

		for (int i = 0; i < ElementsUnderServiceLine.size(); i++) {
			Assert.assertEquals(ElementsUnderServiceLine.get(6).getText(), "Salesforce Chatter", "Salesforce Chatter");
		}

	}

	@Test(priority = 8)
	/***************************************************************************************************************
	Test Case : TC-09
	To verify if User is able To search relevant data in search tab

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be able To view related words in the dropdown which are already available in databAse and can complete by selecting from one of them or can type manually and after clicking on search butTon he/she will be redirected To search result page with following :
	1)content 
	2)experts
	3)audio visual
	4)images and 
	vertically:
	1)industry
	2)service line
	3)nature of work
	4)region 
	5)Asset type
	6)keywords
	7)content owner
	8)data clAssification
	9)file type
	10)created date all
	11)document saved date all
	12)modified date all
	13)folder path
	feedback icon
	 ***************************************************************************************************************/
	public void verifyRelevantDataInSearchBox() throws Exception {

		WebElement SearchBoxGknowledge=driver.findElement(By.xpath("//input[@title='Search Gknowledge']"));
		SearchBoxGknowledge.sendKeys("Gknowledge");
		SearchBoxGknowledge.sendKeys(Keys.ENTER);
		
		String Content=driver.findElement(By.xpath("//a[@title='Searching Content']")).getAttribute("title");
		Assert.assertEquals(Content, "Searching Content", "Searching Content");
		
		String Experts=driver.findElement(By.xpath("//a[@title='Search Experts']")).getAttribute("title");
		Assert.assertEquals(Experts, "Search Experts", "Search Experts");
		
		String AudioVisuals=driver.findElement(By.xpath("//a[contains(text(),'Audio Visuals')]")).getText();
		Assert.assertEquals(AudioVisuals, "Audio Visuals", "AudioVisuals");
		
		String Images=driver.findElement(By.xpath("//a[contains(text(),'Images')]")).getText();
		Assert.assertEquals(Images, "Images", "Images");
		
		String BuyingCenter=driver.findElement(By.xpath("//div[contains(text(),'Buying Center')]")).getText();
		Assert.assertEquals(BuyingCenter, "Buying Center", "BuyingCenter");

		String Industry=driver.findElement(By.xpath("//div[contains(text(),'Industry')]")).getText();
		Assert.assertEquals(Industry, "Industry", "Industry");
		
		String ServiceLine=driver.findElement(By.xpath("//div[contains(text(),'Service Line')]")).getText();
		Assert.assertEquals(ServiceLine, "Service Line", "ServiceLine");
		
		String Natureofwork=driver.findElement(By.xpath("//div[contains(text(),'Nature of work')]")).getText();
		Assert.assertEquals(Natureofwork, "Nature of work", "Natureofwork");
		
		String Region=driver.findElement(By.xpath("//div[contains(text(),'Region')]")).getText();
		Assert.assertEquals(Region, "Region", "Region");
		
		String AssetType=driver.findElement(By.xpath("//div[contains(text(),'Asset Type')]")).getText();
		Assert.assertEquals(AssetType, "Asset Type", "AssetType");
		
		String Keywords=driver.findElement(By.xpath("//div[contains(text(),'Keywords')]")).getText();
		Assert.assertEquals(Keywords, "Keywords", "Keywords");
		
		String ContentOwner=driver.findElement(By.xpath("//div[contains(text(),'Content Owner')]")).getText();
		Assert.assertEquals(ContentOwner, "Content Owner", "ContentOwner");
		
		String DataClassification=driver.findElement(By.xpath("//div[contains(text(),'Data Classification')]")).getText();
		Assert.assertEquals(DataClassification, "Data Classification", "DataClassification");
		
		String FileType=driver.findElement(By.xpath("//div[contains(text(),'File Type')]")).getText();
		Assert.assertEquals(FileType, "File Type", "FileType");
		
		SoftAssert sa = new SoftAssert();
		
		String CreatedDate=driver.findElement(By.xpath("//div[contains(text(),'Created Date')]")).getText();
		sa.assertEquals(CreatedDate, "Created Date All", "CreatedDate");
		
		String DocumentSavedDate=driver.findElement(By.xpath("//div[contains(text(),'Document Saved Date')]")).getText();
		sa.assertEquals(DocumentSavedDate, "Document Saved Date All", "DocumentSavedDate");
		
		String Modifieddate=driver.findElement(By.xpath("//div[contains(text(),'Modified date')]")).getText();
		sa.assertEquals(Modifieddate, "Modified date All", "Modifieddate");
		
		String FolderPath=driver.findElement(By.xpath("//div[contains(text(),'Folder Path')]")).getText();
		sa.assertEquals(FolderPath, "Folder Path", "FolderPath");
		
		String Theme=driver.findElement(By.xpath("//div[contains(text(),'Theme')]")).getText();
		sa.assertEquals(Theme, "Theme", "Theme");
		
		boolean Feedback=driver.findElement(By.xpath("//span[@class='btnTxt dispNan']")).isDisplayed();
		sa.assertTrue(Feedback, "Feedback");
		sa.assertAll();
		

	}
	
	@Test(priority = 9)
	/***************************************************************************************************************
	Test Case : TC-10
	To verify if User is able To view content if click on find content after puting keyword in search field

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be redirected To search page and default page is showing contents
	 ***************************************************************************************************************/
	public void verifyRelevantContentIsShown() throws Exception {
		
		WebElement SearchBoxGknowledge=driver.findElement(By.xpath("//input[@title='Search Gknowledge']"));
		SearchBoxGknowledge.sendKeys("Gknowledge");
		SearchBoxGknowledge.sendKeys(Keys.ENTER);
		
		boolean resultCount=driver.findElement(By.xpath("//div[@id='ResultCount']")).isDisplayed();
		Assert.assertTrue(resultCount, "resultCount");
		
		
	}
	
	@Test(priority = 10)
	/***************************************************************************************************************
	Test Case : TC-11
	To verify if User is able To view internal experts if click on find internal expert after putting the keywords in search field

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be redirected To search page and default page should show experts related to tht particular content
	 ***************************************************************************************************************/
	public void verifyRelevantInternalExpert() throws Exception {
		
		WebElement SearchBoxGknowledge=driver.findElement(By.xpath("//input[@title='Search Gknowledge']"));
		SearchBoxGknowledge.sendKeys("Gknowledge");
		SearchBoxGknowledge.sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//a[@title='Search Experts']")).click();
		
		boolean resultCount=driver.findElement(By.xpath("//div[@id='ResultCount']")).isDisplayed();
		Assert.assertTrue(resultCount, "resultCount");
		
		
	}
	
	@Test(priority = 11)
	/***************************************************************************************************************
	Test Case : TC-12
	To verify if User is able To search by communities

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	3) User should be redirected To search page and content should be displayed in that page
	 ***************************************************************************************************************/
	public void verifyRelevantSearchByCommunities() throws Exception {
		
		WebElement SearchBoxGknowledge=driver.findElement(By.xpath("//input[@title='Search Gknowledge']"));
		SearchBoxGknowledge.sendKeys("Gknowledge");
		SearchBoxGknowledge.sendKeys(Keys.ENTER);
		
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='inlineClass']")));
		dropdown.selectByVisibleText("Accounts Payable");
		
		boolean resultCount=driver.findElement(By.xpath("//div[@class='ms-srch-resultscount']")).isDisplayed();
		Assert.assertTrue(resultCount, "resultCount");
		
		
	}


	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + result.getMethod().getMethodName() + ".png"));

		driver.quit();

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}

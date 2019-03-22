package com.qa.pages;

import static org.testng.Assert.fail;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.testbase.TestBase;

public class MyProfileOWT extends TestBase{
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String sheetName = "contacts";

	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		LoginMyPage();

	}
	
	@Test(priority = 1)
	/***************************************************************************************************************
	Test Case : TC-01
	To Verify that My User Profile page is visible to Employee

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	***************************************************************************************************************/
	public void verifyMyDocumentIsPresent() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'My Documents')]")), "My Document");
		//driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();
	}
	
	@Test(priority = 2)
	/***************************************************************************************************************
	Test Case : TC-01
	To Verify that My User Profile page is visible to Employee

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	***************************************************************************************************************/
	public void verifyHomepageIcon() throws Exception {
		boolean HoemPageIcon=driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
		Assert.assertTrue(HoemPageIcon, "HoemPageIcon");
		driver.findElement(By.xpath("//img[@class='img-responsive']")).click();
	
		String NewURL = driver.getCurrentUrl();
		String ActualresultURL = prop.getProperty("ProdUrl");
		
		Assert.assertEquals(NewURL, ActualresultURL, "URL Not Matched");
		
	}

	@Test(priority = 3)
	/***************************************************************************************************************
	Test Case : TC-03
	To Verify that My User Profile page is visible to Employee

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Profile Icon
	***************************************************************************************************************/
	public void verifyClickOnMyProfile() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'My Profile')]")), "My Profile");
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
	}
	
	@Test(priority = 4)
	/***************************************************************************************************************
	Test Case : TC-04
	To verify that Genpact Employee is able to See his/her Basic Details information 

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Profile Icon
	4) Click to Organisation details tab
	***************************************************************************************************************/
	public void verifyClickOnOrgDeatilTab() throws Exception {
		
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		String OrgDetail=driver.findElement(By.xpath("//a[contains(text(),'Organization Details')]")).getText();
		Assert.assertEquals(OrgDetail, "Organization Details", "Organization Details");
		driver.findElement(By.xpath("//a[contains(text(),'Organization Details')]")).click();
	}
	
	@Test(priority = 5)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify that Genpact Employee is able to See his/her Personal Details information  

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Profile Icon
	4) Verify to Personal details
	Employee should be able to see below details:
	1.Personal Email ID  
	2.Emergency Contact Number  
	3.PF Account Number  
	4.Universal Account Number  
	5.LE  
	***************************************************************************************************************/
	public void verifypersonalDetails() throws Exception {
		
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		Thread.sleep(7000);
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(driver.findElement(By.xpath("//input[@id='inputEmail']")).isDisplayed(), "inputEmail");
		sa.assertTrue(driver.findElement(By.xpath("//input[@id='inputEmergencyContactNumber']")).isDisplayed(), "inputEmergencyContactNumber");
		sa.assertTrue(driver.findElement(By.xpath("//label[@id='inputPFAccountNumber']")).isDisplayed(), "inputPFAccountNumber");
		sa.assertTrue(driver.findElement(By.xpath("//input[@id='inputUniversalAccountNumber']")).isDisplayed(), "inputUniversalAccountNumber");
		sa.assertTrue(driver.findElement(By.xpath("//input[@id='inputLE']")).isDisplayed(), "inputLE");		
		sa.assertAll();
		
		
	}
	
	@Test(priority = 6)
	/***************************************************************************************************************
	Test Case : TC-08
	To verify if as Team Manager (Genpact Employee) is able to view his/ her team details.

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My Profile
	3) Click My Team
	4) Click on Employee name
	***************************************************************************************************************/
	public void verifyManagerTeamMemberDetails() throws Exception {
		
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'My Team')]")).click();
		Boolean ManagerTeamOHR=driver.findElement(By.xpath(prop.getProperty("teamMemberOHR"))).isDisplayed();
		Assert.assertTrue(ManagerTeamOHR, "teamMemberOHR");
		
		driver.findElement(By.xpath(prop.getProperty("teamMemberOHR"))).click();
		
		boolean OHRID=driver.findElement(By.xpath("//label[@id='lblOHR']")).isDisplayed();
		Assert.assertTrue(OHRID, "OHRID");
		
		
	}
	
	@Test(priority = 7)
	/***************************************************************************************************************
	Test Case : TC-10
	To verify Genpact employee is able to see his/her work assignment details

	Test Steps : 
	1)Click to genpact Homepage
	2) Open MY PAGE
	3)Click on My profile
	4)Click on work History
	***************************************************************************************************************/
	public void verifyWorkAssignmentDetails() throws Exception {
		
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Work History')]")).click();
		Boolean WorkAssignmentDetails=driver.findElement(By.xpath("//div[@id='Assignmenttr']//div[@class='col-lg-6 panelbox']")).isDisplayed();
		Assert.assertTrue(WorkAssignmentDetails, "WorkAssignmentDetails");
		
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

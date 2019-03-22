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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.testbase.TestBase;

import Analyzer.RetryAnalyzer;

public class MyApprovals extends TestBase{

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String sheetName = "contacts";

	@BeforeMethod
	/***************************************************************************************************************
	Test Case : TC-01
	To verify if user is able to login to My Page or not

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	 ***************************************************************************************************************/
	public void setUp() throws Exception {
		initialization();
		LoginMyPage();

	}
	
	@Test
	public void testmethod() {
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).click();
		Boolean myRewards= driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).isDisplayed();
		//Assert.assertEquals(myRewards, true);
		Assert.assertTrue(myRewards, "My Reward is not displayed"); 
	}

	@Test(priority = 1)
	/***************************************************************************************************************
	Test Case : TC-01
	To verify if user is able to login to My Page or not

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	 ***************************************************************************************************************/
	public void verifyLogin() throws Exception {
		Assert.assertEquals(driver.getTitle(), "GSocial");
	}

	@Test(priority = 2)
	/***************************************************************************************************************
	Test Case : TC-02
	 To Verify the visibility of "My approval" Widget on My PAGE

	Test Steps : 
	1)Click to genpact Homepage
	2) Open MY PAGE
	3) Navigate to My Approval
	 ***************************************************************************************************************/
	public void verifytclickOnMyApprovals() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'My Approvals')]")), "My Approvals");
		driver.findElement(By.xpath("//span[contains(text(),'My Approvals')]")).click();
	}

	@Test(priority = 3 /*retryAnalyzer = Analyzer.RetryAnalyzer.class*/)
	/***************************************************************************************************************
	Test Case : TC-03
	To verify that if  request is visible on My page under My "Approvals" 

	Test Steps : 
	1)Click to genpact Homepage
	2) Open MY PAGE
	3) Navigate to My Approval
	 ***************************************************************************************************************/
	public void verifyViewRequest() throws Exception {

		//click on My Approval
		driver.findElement(By.xpath("//span[contains(text(),'My Approvals')]")).click();
		
		boolean HelpMateEmpty= driver.findElement(By.xpath("//span[@class='helpmateEmpty']")).isDisplayed();
		if(HelpMateEmpty = true) {
			Assert.assertFalse(true , "HelpmateEmpty");
		}
		
		String requestName=driver.findElement(By.xpath("//abbr[@class='trainingtitle']")).getText();
		Assert.assertEquals(requestName, "USB Activation-");
	}

	@Test(priority = 4)
	/***************************************************************************************************************
	Test Case : TC-05
	To verify that Request details that are visible to User

	Test Steps : 
	1)Click to genpact Homepage
	2) Open MY PAGE
	3) Navigate to My Approvals and  
	4) List of all Tickets will be avaliable that are pending for approval will be visible with following information:

	-Ticket Description
	-Ticket Number 
	-Ticket Date
	-Ticket Time
	 ***************************************************************************************************************/
	public void verifyRequestDetailVisibletoUser() throws Exception {

		//click on My Approval
		driver.findElement(By.xpath("//span[contains(text(),'My Approvals')]")).click();
		
		boolean HelpMateEmpty= driver.findElement(By.xpath("//span[@class='helpmateEmpty']")).isDisplayed();
		if(HelpMateEmpty = true) {
			Assert.assertFalse(true , "HelpmateEmpty");
		}

		SoftAssert sa = new SoftAssert();

		String requestName=driver.findElement(By.xpath("//abbr[@class='trainingtitle']")).getText();
		sa.assertEquals(requestName, "USB Activation-", "Ticket Description");

		String requestNumber=driver.findElement(By.xpath("//span[@class='duedate']")).getText();
		sa.assertEquals(requestNumber, "REQ000001589887", "Ticket Number");

		String requestDateTime=driver.findElement(By.xpath("//span[@class='traininghr']")).getText();
		sa.assertEquals(requestDateTime, "18 Jan 2019 01:12:53 PM", "Ticket Date Time");

		sa.assertAll();
	}


	@Test(priority = 5)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify that User is able to view the contents of  Ticket

	Test Steps : 
	1)Click to genpact Homepage
	2) Open MY PAGE
	3) Navigate to My Approvals 
	4) List of Tickets will be avaliable that are pending for approval
	5) Click on any Ticket.
	 ***************************************************************************************************************/
	public void verifyAbletoClickOnRequest() throws Exception {
		//click on My Approval
		driver.findElement(By.xpath("//span[contains(text(),'My Approvals')]")).click();
		
		boolean HelpMateEmpty= driver.findElement(By.xpath("//span[@class='helpmateEmpty']")).isDisplayed();
		if(HelpMateEmpty = true) {
			Assert.assertFalse(true , "HelpmateEmpty");
		}

		//click on request
		driver.findElement(By.xpath("//abbr[@class='trainingtitle']")).click();

		SoftAssert sa = new SoftAssert();

		String RequestNumber=driver.findElement(By.xpath("//td[contains(text(),'Request Number')]")).getText();
		sa.assertEquals(RequestNumber, "Request Number ", "RequestNumber");

		String RequestorName=driver.findElement(By.xpath("//td[contains(text(),'Requester Name')]")).getText();
		sa.assertEquals(RequestorName, "Requester Name", "RequestorName");

		String RequestorOHRiD=driver.findElement(By.xpath("//td[contains(text(),'Requester OHRID')]")).getText();
		sa.assertEquals(RequestorOHRiD, "Requester OHRID", "RequestorOHRiD");

		String band=driver.findElement(By.xpath("//tr[@data-dt-row='2']//td[contains(text(),'Band')]")).getText();
		sa.assertEquals(band, "Band", "band");

		String CostCode=driver.findElement(By.xpath("//td[contains(text(),'Cost Code')]")).getText();
		sa.assertEquals(CostCode, "Cost Code", "CostCode");

		String COE=driver.findElement(By.xpath("//td[contains(text(),'COE')]")).getText();
		sa.assertEquals(COE, "COE ", "COE");

		String SDO=driver.findElement(By.xpath("//td[contains(text(),'SDO')]")).getText();
		sa.assertEquals(SDO, "SDO ", "SDO");

		String LE=driver.findElement(By.xpath("//td[contains(text(),'LE')]")).getText();
		sa.assertEquals(LE, "LE ", "LE");

		String BaseLocation=driver.findElement(By.xpath("//td[contains(text(),'Base Location')]")).getText();
		sa.assertEquals(BaseLocation, "Base Location ", "BaseLocation");

		String SupervisiorName=driver.findElement(By.xpath("//td[contains(text(),'Supervisor Name')]")).getText();
		sa.assertEquals(SupervisiorName, "Supervisor Name", "SupervisiorName");

		String Details=driver.findElement(By.xpath("//td[contains(text(),'Details')]")).getText();
		sa.assertEquals(Details, "Details ", "Details");

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

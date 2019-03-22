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

import com.qa.testbase.TestBase;

public class MyDocument extends TestBase{
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
	Test Case : TC-03
	To verify that user is able to see my Document tab under GSOCIAL

	Test Steps : 
	1)Click to genpact Homepage
	 ***************************************************************************************************************/
	public void verifyMyDocumentIsPresent() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'My Documents')]")), "My Document");
		driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();
	}

	@Test(priority = 2)
	/***************************************************************************************************************
	Test Case : TC-04
	To verify that user is able to see his documents under My Document tab under GSOCIAL

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY Document
	 ***************************************************************************************************************/
	public void verifyClickOnMyDocument() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'My Documents')]")), "My Document");
		driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();
	}

	@Test(priority = 3)
	/***************************************************************************************************************
	Test Case : TC-05
	To verify that user is able to download Employment Proof from  My Document 

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Documents
	4) Doubble click on Employment Proof
	5) Click on Save
	 ***************************************************************************************************************/
	public void verifyDownloadEmploymentProof() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();

		Boolean EmploymentProof=driver.findElement(By.xpath("//p[contains(text(),'Employment Proof')]")).isDisplayed();
		Assert.assertTrue(EmploymentProof, "EmploymentProof");
		driver.findElement(By.xpath("//p[contains(text(),'Employment Proof')]")).click();

	}

	@Test(priority = 4)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify that user is able to download Employment Proof from  My Document 

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Documents
	4) Doubble click on Employment Proof
	5) Click on Save
	 ***************************************************************************************************************/
	public void verifyDownloadAddressProof() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();

		Boolean AddessProof=driver.findElement(By.xpath("//p[contains(text(),'Address Proof')]")).isDisplayed();
		Assert.assertTrue(AddessProof, "AddessProof");
		
		String oldURL=driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//p[contains(text(),'Address Proof')]")).click();
		
		String NewURL=driver.getCurrentUrl();

	}
	
	@Test(priority = 5)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify that user is able to download Employment Proof from  My Document 

	Test Steps : 
	1)Click to genpact Homepage
	2) Open My page
	3) Click on My Documents
	4) Doubble click on Appointment Letter
	5) Click on Save
	 ***************************************************************************************************************/
	public void verifyDownloadAppointmentletter() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Documents')]")).click();

		Boolean Appointmentletter=driver.findElement(By.xpath("//p[contains(text(),'Appointment Letter')]")).isDisplayed();
		Assert.assertTrue(Appointmentletter, "Appointmentletter");
		driver.findElement(By.xpath("//p[contains(text(),'Appointment Letter')]")).click();
		
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

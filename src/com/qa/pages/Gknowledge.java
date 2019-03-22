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

import com.qa.Utility.Utility;
import com.qa.testbase.TestBase;

public class Gknowledge extends TestBase{
	
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
	To verify that by clicking on the link provided User is getting redirected To G_ Knowledge search result page

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on GKnowledge
	***************************************************************************************************************/
	public void verifyClickOnGknowledge() throws Exception {
		Assert.assertTrue(isElementPresent(By.xpath("//span[contains(text(),'Gknowledge')]")), "Gknowledge");
		driver.findElement(By.xpath("//span[contains(text(),'Gknowledge')]")).click();
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
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

public class MyProfileMyRewards extends TestBase{

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
	To verify user is able to click on "My Profile"

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY Profile
	 ***************************************************************************************************************/
	public void verifyClickOnMyProfile() throws Exception {
		
		boolean MyProfile=driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).isDisplayed();
		Assert.assertTrue(MyProfile, "MyProfile");
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		
	}
	
	@Test(priority = 2)
	/***************************************************************************************************************
	Test Case : TC-01
	To verify user is navigated to "Employee Details" page after clicking on "My Profile"

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY Profile
	 ***************************************************************************************************************/
	public void verifyNavigatetoEmployeeDeatilsPage() throws Exception {
		
		boolean MyProfile=driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).isDisplayed();
		Assert.assertTrue(MyProfile, "MyProfile");
		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		
		boolean EmployeeDetails=driver.findElement(By.xpath("//div[@class='profiledetailsdiv']")).isDisplayed();
		Assert.assertTrue(EmployeeDetails, "EmployeeDetails");
		
	}

	@Test(priority = 3)
	/***************************************************************************************************************
	Test Case : TC-03
	To verify the visibility of "My Rewards" section under Employee Details

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	 ***************************************************************************************************************/
	public void verifyMyRewardsIsDisplayed() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		Boolean MyRewards=driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).isDisplayed();
		Assert.assertTrue(MyRewards, "My Rewards");
	}

	@Test(priority = 4)
	/***************************************************************************************************************
	Test Case : TC-04
	To verify if user is able to go to "My Rewards" section by clicking on it

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	3) Click on My Rewards
	 ***************************************************************************************************************/
	public void verifyClickonMyRewards() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();
		Boolean MyRewards=driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).isDisplayed();
		Assert.assertTrue(MyRewards, "My Rewards");
		driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).click();
	}

	@Test(priority = 5)
	/***************************************************************************************************************
	Test Case : TC-05
	 To verify the visibility of "Total Cheers" points balance under "My Rewards"

	Test Steps : 
	1) Click to genpact Homepage
	2) Open MY PAGE
	3) Click on "View Complete Profile"
	4) Click on "My Rewards"
	5) Go to "Total Cheers" points
	 **************************************************************************************************************/
	public void verifyVisibilityOfTotalCheers() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();		
		driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).click();
		Boolean totalCheers=driver.findElement(By.xpath("//label[contains(text(),'28245')]")).isDisplayed();
		Assert.assertTrue(totalCheers, "totalCheers");
	}

	@Test(priority = 6)
	/***************************************************************************************************************
	Test Case : TC-06
	To verify if user is able to redeem points on clicking on "Click here to Redeem Cheers"

	Test Steps : 
	1) Click to genpact Homepage
	2) Open MY PAGE
	3) Click on "View Complete Profile"
	4) Click on "My Rewards"
	5) Click on "Redeem Cheers" 
	 ***************************************************************************************************************/
	public void verifyClickOnRedeemCheers() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();		
		driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).click();
		Boolean ClickhereRedeemCheers=driver.findElement(By.xpath("//a[contains(text(),'Click here to redeem Cheers')]")).isDisplayed();
		Assert.assertTrue(ClickhereRedeemCheers, "ClickhereRedeemCheers");
		driver.findElement(By.xpath("//a[contains(text(),'Click here to redeem Cheers')]")).click();
	}

	@Test(priority = 7)
	/***************************************************************************************************************
	Test Case : TC-08
	To verify the visibility of "My Cheers History" for last two years under "My Rewards"

	Test Steps : 
	1) Click to genpact Homepage
	2) Open MY PAGE
	3) Click on "View Complete Profile"
	4) Click on "My Rewards"
	5) Go to "My Cheers History" section
	 ***************************************************************************************************************/
	public void verifyMyCheersHistory() throws Exception {

		driver.findElement(By.xpath("//span[contains(text(),'My Profile')]")).click();		
		driver.findElement(By.xpath("//a[contains(text(),'My Rewards')]")).click();
		Boolean MyCheersHistory=driver.findElement(By.xpath("//div[@id='applypointhistory']")).isDisplayed();
		Assert.assertTrue(MyCheersHistory, "MyCheersHistory");
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

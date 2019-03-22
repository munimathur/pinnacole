package com.qa.pages2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testbase.TestBase;

public class communities extends TestBase
{
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String sheetName = "contacts";

	@BeforeMethod
	/***************************************************************************************************************
	Test Case : TC-01
	To verify if user is able to view "Communities" icon

	Test Steps : 
	1)Click to genpact Homepage- GSOCIAL
	2)Click on MY PAGE
	 ***************************************************************************************************************/
	public void setUp() throws Exception {
		initialization();
		LoginMyPage();

	}

	@Test(priority=1)
	public void verifyCommunity() throws Exception
	{
		Thread.sleep(5000);
		WebElement element=driver.findElement(By.xpath("//span[contains(text(),'Communities')]"));
				if(element.isDisplayed())
				{
				System.out.println("Image displayed");
				}else{
				System.out.println("Image not displayed");
				}
		
	}
}

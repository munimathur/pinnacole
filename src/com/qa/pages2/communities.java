package com.qa.pages2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
	

	@Test(description="To Verify the Communities icon is Showing ",priority=1)
	public void verifyCommunity() throws Exception
	{
		boolean element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]")).isDisplayed();
		Assert.assertTrue(element, "Tools");	
		
	}
	
	@Test(description="To Verify the Popular Communities icon is clicking ",priority=2)
	public void clickCommunity()
	{
		WebElement element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
		element.click();
	}
	
		
	@Test(description="To Verify the Popular Communities icon is clicking ",priority=3)
	public void searchCommunity()
	{
		WebElement element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
		element.click();
		
		WebElement search=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
		search.sendKeys("Digital");
		search.submit();
	}
	
	
	@Test(description="To Verify the Popular Communities icon is cliking",priority=4)
	public void verifyPopularCommunities()
	{
		WebElement element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
		element.click();
		
		boolean element1=driver.findElement(By.xpath("//a[contains(text(),'Popular Communities')]")).isDisplayed();
		Assert.assertTrue(element1, "Popular Communities");	
	}
	
	@Test(description="To Verify the All Communities icon is clicking",priority=5)
	public void verifyAllCommunities()
	{
		WebElement element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
		element.click();		
		
		boolean element1=driver.findElement(By.xpath("//li[@id='leftallcommunities']//a[1]")).isDisplayed();
		Assert.assertTrue(element1, "All Communities");	
	}
	
}

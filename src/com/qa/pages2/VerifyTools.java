package com.qa.pages2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.testbase.TestBase;

public class VerifyTools extends TestBase{


private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();
String sheetName = "contacts";

@BeforeMethod
/***************************************************************************************************************
Test Case : TC-01
To verify if user is able to view "Tools" icon

Test Steps : 
1)Click to genpact Homepage- GSOCIAL
2)Click on MY PAGE
 ***************************************************************************************************************/
public void setUp() throws Exception {
	initialization();
	LoginMyPage();

}

@Test(description="To Verify the Tools icon is displayed",priority=1)
public void verify() throws Exception
{
	Thread.sleep(2000);
	boolean element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]")).isDisplayed();
	Assert.assertTrue(element, "Tools");	
	
}


@Test(description="To Verify the Popular Tools icon is clicking", priority=2)	
	
	public void clickTool() throws Exception
	{
	           		
				WebElement element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]"));
				element.click();	
				Thread.sleep(2000);	
				WebElement element1=driver.findElement(By.linkText("Popular Tools"));
				element1.click();
				
}

@Test(description="To Verify the functionality of search field",priority=3)	

public void verifySearch() throws Exception
{
	
			WebElement search1=driver.findElement(By.xpath("//input[@id='mainsearch']"));
			search1.sendKeys("alert");
			WebElement value=driver.findElement(By.xpath("//img[@src='https://genpactonline.sharepoint.com/_catalogs/masterpage/GSocial/images/searchtop.png?ctag=190326']"));
			value.sendKeys(Keys.ENTER);
}

@Test(description="To Verify the Popular Tools Dashboard",priority=4)
public void verifyPopularToolsDashboard()

{
	Boolean element=driver.findElement(By.xpath("//div[@class='poplinks'][contains(text(),'Alert')]")).isDisplayed();
	Assert.assertTrue(element, "Alert");	
}


@Test(description="To Verify the All Tools is displaying ",priority=5)
public void VerifyAllTools()
{

	Boolean element =driver.findElement(By.xpath("//a[@href='#Toolszone']")).isDisplayed();
	Assert.assertTrue(element, "All Tools");	
}

}
	

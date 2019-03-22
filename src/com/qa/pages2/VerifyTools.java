package com.qa.pages2;

import org.openqa.selenium.By;
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

@Test(priority=1)
public void verify() throws Exception
{
	Thread.sleep(5000);
	boolean element=driver.findElement(By.xpath("//li[@id='headtools']//span[contains(text(),'Tools')]")).isDisplayed();
	Assert.assertTrue(element, "Tools");	
	
}

@Test(priority=2)	
	
	public void clickTool() throws Exception
	{
		Thread.sleep(2000);
						
				String actualTitle = driver.getTitle();
				System.out.println(actualTitle);
				Thread.sleep(2000);
				//WebElement element1=driver.findElement(By.xpath("//a[contains(text(),'Popular Tools')]"));
				//element1.click();
				
}

/*@Test(priority=3)	

public void verifySearch() throws Exception
{
	Thread.sleep(2000);
	
			WebElement search1=driver.findElement(By.xpath("//input[@id='mainsearch']"));
			search1.sendKeys("alert");
			
}*/

}
package com.qa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;


	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void initialization(){
		String browserName = prop.getProperty("browser");

		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\exe\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\exe\\geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\exe\\IEDriverServer.exe");	
			driver = new InternetExplorerDriver(); 
		}


		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);



	}

	public static void LoginMyPage(){

		driver.get(prop.getProperty("url"));

		
		//Use Another account for IE
		//Created By Muni
		driver.findElement(By.xpath("//div[@id='otherTileText']")).click();
		
		
		//enter email phone or skype into the address to login
		driver.findElement(By.name("loginfmt")).sendKeys(prop.getProperty("email"));

		//click on next
		driver.findElement(By.id("idSIButton9")).click();

		//explicite wait for element to be clickable (Username)
		WebDriverWait d=new WebDriverWait(driver,20);
		d.until(ExpectedConditions.elementToBeClickable(By.name("username")));

		//enter OHR
		driver.findElement(By.name("username")).sendKeys(prop.getProperty("username"));

		//enter password using Encrypted
		String Password = prop.getProperty("password");
		byte[] encryptedPwd = Password.getBytes();
		//System.out.println(Password);
		byte[] decoded = Base64.decodeBase64(encryptedPwd);
		//System.out.println(new String(decoded));
		driver.findElement(By.name("password")).sendKeys(new String(decoded));

		//click on submit
		driver.findElement(By.id("okta-signin-submit")).click();

		//stay sign in click
		driver.findElement(By.id("idSIButton9")).click();
 

	}

	public static void LoginGknowledge(){

		driver.get(prop.getProperty("url_gKnowledge"));

		driver.findElement(By.name("loginfmt")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.id("idSIButton9")).click();

		WebDriverWait d=new WebDriverWait(driver,20);
		d.until(ExpectedConditions.elementToBeClickable(By.name("username")));
		driver.findElement(By.name("username")).sendKeys(prop.getProperty("username"));
		String Password = prop.getProperty("password");
		byte[] encryptedPwd = Password.getBytes();
		byte[] decoded = Base64.decodeBase64(encryptedPwd);
		driver.findElement(By.name("password")).sendKeys(new String(decoded));
		driver.findElement(By.id("okta-signin-submit")).click();

		driver.findElement(By.id("idSIButton9")).click();

	}

	
	@AfterMethod
	public static void closebrowser() throws Exception
	{
		Thread.sleep(2000);
		driver.close();	
	}
	
}

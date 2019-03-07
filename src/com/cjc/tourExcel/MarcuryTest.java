package com.cjc.tourExcel;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class MarcuryTest 
{
	public WebDriver driver;
	
	  @Test(dataProvider = "test1",description="Enter Valid username and password for User1")
	  public void loginwithValidDs(String username, String password) 
	  {
		  System.out.println("Login for mercury tour application");
		  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(username);
		  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		  driver.findElement(By.xpath("//input[@name='login']")).click();
		  System.out.println("Login Successfully");
		  driver.findElement(By.linkText("SIGN-OFF")).click();
	  }
	  @BeforeMethod
	  public void getAllCookies() 
	  {
		  System.out.println("Add cookies name and domain");
		  Set<Cookie>coo=driver.manage().getCookies();
		  for(Cookie cookies : coo)
		  {
			  System.out.println("Cookies name: "+cookies.getName());
			  System.out.println("Cookies domain: "+cookies.getDomain());
		  }
	  }

	  @AfterMethod
	  public void captureScreenshot () throws IOException 
	  {
		  System.out.println("In captureScreenshot method under @AfterMethod");
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("E:\\Core Java\\ExcelHandling\\Screenshot\\tour.jpg"));
		  System.out.println("screenshot is captured is successfully");
	  }


	  @Test
	@DataProvider
	  public Object[][] test1 () throws IOException 
	  {
		  ExcelData data=new ExcelData();
		  data.exceldata("E:\\Core Java\\ExcelHandling\\TourExample.xlsx");
		  int row=data.getrowcount("Sheet1");
		  int column=data.getcolumncount("Sheet1");
		  
		  Object[][]userds=new Object[row][column];
		  for(int i=0;i<row;i++)
		  {
			  for(int j=0;j<column;j++)
			  {
				  userds[i][j]=data.getdata("Sheet1", i, j);
			  }
		  }
		  
		  return userds;
	    
	  }
	  @BeforeClass
	  public void maximizeBrowser() 
	  {
		  driver.manage().window().maximize();
		  System.out.println("Window get maximized");
	  }

	  @AfterClass
	  public void deleteAllCokkies() 
	  {
		  System.out.println("In deleteAllCokkies method under @AfterClass");
		  driver.manage().deleteAllCookies();
		  System.out.println("All cokkies are delete successfully");
	  }

	  @BeforeTest
	  public void enterUrl() 
	  {
		  driver.get("http://newtours.demoaut.com/");
		  System.out.println("Application URL get open successfully");
	  }

	  @AfterTest
	  public void dbConnection() 
	  {
		  System.out.println("In dbConnection method under @AfterTest");
	  }

	  @BeforeSuite
	  public void openBrowser() 
	  {
		  System.setProperty("webdriver.chrome.driver", "F:\\Selenium Setup\\Setup\\chromedriver_win32\\chromedriver.exe");
		  driver=new ChromeDriver();
		  System.out.println("Browser get open successfully");
	  }

	  @AfterSuite
	  public void closeBrowser() 
	  {
		  driver.close();
	  }
}

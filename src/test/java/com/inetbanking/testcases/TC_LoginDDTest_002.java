package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDTest_002 extends BaseClass
{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name entered");
		lp.setPassword(pwd);
		logger.info("Password entered");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if(isAlertPresent() == true)
		{
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			
			Assert.assertTrue(true);
			logger.warn("Login passed");
			lp.clickLogOut();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // close logout
			driver.switchTo().defaultContent();
			
		}
		
	}
	
	public boolean isAlertPresent() // user defined method created to check alert is present or not 
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name ="LoginData")
	String  [][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/TestData.xlsx";
		int rownum = XLUtils.getRowCount(path,"Sheet1");
		int colcount = XLUtils.getCellCountt(path,"Sheet1", rownum);
		
		String logindata[][] = new String [rownum][colcount];
		for (int i =1; i <= rownum; i++)
		{
			for (int j =0; j< colcount; j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1",i,j);
			}
		}
		return logindata;
	}
}

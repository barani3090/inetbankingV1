package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		
		logger.info("Site is opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("user name entered");
		lp.setPassword(password);
		logger.info("Password entered");
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Test case passed");
		}
		else
		{
			captureScreen( driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Test case failed");
		}
		
	}
	
		
	}
	





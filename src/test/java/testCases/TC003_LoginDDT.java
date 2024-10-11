package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends baseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "Datadriven") //getting dataprovider from a different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException 
	{
		logger.info("***** Starting TC003 LoginDDT *****");
		
		
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage= macc.isMyAccountExists();
		
		System.out.println("targetpage = "+targetPage);
		
		//Data is valid - login success - test pass - logout
		//Data is valid - login failed - test fail
		//Data is invalid - login success - test fail - logout
		//Data is invalid - login failed - test pass
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if (targetPage==true)
			{
				Assert.assertTrue(true);
				macc.clickLogout();
			} else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if (targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);			
			} else
			{
				Assert.assertTrue(true);
			}
		}
		} catch (Exception e)
		{
			Assert.fail();
		}
		
		Thread.sleep(1000);
		logger.info("***** Finished TC003 LoginDDT *****");
	}
}

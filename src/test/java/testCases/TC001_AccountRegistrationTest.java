package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.baseClass;

public class TC001_AccountRegistrationTest extends baseClass{


	@Test (groups={"Regression","Sanity", "Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try
		{
		HomePage hp=new HomePage(driver);	
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details");
		regpage.setFirstName(randomString(5).toUpperCase());
		regpage.setLastName(randomString(5).toUpperCase());
		regpage.setEmail(randomString(7)+"@gmail.com");
		regpage.setTelephone(randomNumber(10));
		
		String password=randomAlphaNumeric(4);
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		System.out.println("Password="+password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message...");
		String confmsg = regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		} else
		{
			logger.error("Test Failed..");
			logger.debug("Debug Logs..");
			Assert.fail();
			Assert.assertTrue(false);
			
		}
			
		} catch (Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
	
	
	
}

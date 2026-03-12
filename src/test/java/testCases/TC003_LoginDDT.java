package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid -login success - test pass - logout
 * Data is valid - login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout 
 * Data is invalid - login failed - test pass 
 * 
 */
public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups="datadriven") //getting data provider from diff class
	public void verify_login(String email, String pwd, String exp) {
		logger.info("*** Starting TC003_LoginDDT ***");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clicklogin();
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true, "Login Failed");
		
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true);
				myacc.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
	
		if(exp.equalsIgnoreCase("InValid")) 
		{
			if(targetPage==true)
			{
				Assert.assertTrue(false);
				myacc.clickLogout();
			}
			else
			{
				Assert.assertTrue(true);
			}
	
		}
	}catch (Exception e)
	{
		Assert.fail();
	}
		
	logger.info("*** Finishing TC003_LoginDDT ***");

	}
	
}

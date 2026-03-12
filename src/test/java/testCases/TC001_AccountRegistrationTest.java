package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"regression","Master"})
	void verify_account_registration() {
		
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		
		try {
			
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.ClickRegister();
		logger.info("Clicked on MyRegister link");
		
		AccountRegistrationPage regpg = new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		regpg.setFirstName(randomeString().toUpperCase());
		regpg.setLastName(randomeString().toUpperCase());
		regpg.setEmail(randomeString()+"@gmail.com");
		regpg.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumeric();
		regpg.setPassword(password);
		regpg.setConfirmPassword(password);
		
		regpg.setPrivacyPolicy();
		regpg.setcontinue();
		
		logger.info("Validating expected msg");
		String confmsg = regpg.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("*** Finshed TC001_AccountRegistrationTest ***");

	}
	
	
}

package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.*;
import testBase.BaseClass;

import java.time.Duration;

public class TC006_EndToEndTest extends BaseClass {

    @Test(groups={"sanity","Master"})
    public void verify_EndToEndFlow() throws InterruptedException {
    	
    	//SoftAssert
    	SoftAssert myassert = new SoftAssert();

        logger.info("Starting TC006_EndToEndTest");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     // Navigate to registration page
        //driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

        // Registration
        
        HomePage hp = new HomePage(driver);
        hp.ClickMyAccount();
        hp.ClickRegister();
        
        
        AccountRegistrationPage reg = new AccountRegistrationPage(driver);
        reg.setFirstName(randomeString().toUpperCase());
        reg.setLastName(randomeString().toUpperCase());
       
        String email = (randomeString() + "@gmail.com");
        reg.setEmail(email);
        
        reg.setTelephone("9876543210");
        reg.setPassword("test123");
        reg.setConfirmPassword("test123");
        reg.setPrivacyPolicy();
        reg.setcontinue();

        logger.info("Registration completed");
        
        MyAccountPage mc = new MyAccountPage(driver);
        mc.clickLogout();

        handleAlertIfPresent();

        logger.info("Logout completed");

        Thread.sleep(2000);   // temporary wait

        // Navigate again to login
        hp.ClickMyAccount();
        hp.ClickLogin();
        
        // Wait until login email field is visible
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));

        LoginPage lp = new LoginPage(driver);

        lp.setEmail(email);
        lp.setPassword("test123");
        lp.clicklogin();

        logger.info("Login successful");
        
        System.out.println("Going to my account page? "+mc.isMyAccountPageExists());
        myassert.assertEquals(mc.isMyAccountPageExists(), true);

        // Search product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));

        hp.enterProductName(p.getProperty("searchProductName"));
        hp.clickSearch();
        SearchPage sp = new SearchPage(driver);
        
        if(sp.isProductExist(p.getProperty("searchProductName")))
        {
        	sp.selectProduct(p.getProperty("searchProductName"));
        	sp.setQuantity("2");
        	sp.addTocard();
        }

        boolean status = sp.checkConfMsg();
        System.out.println("Added product to cart? " + status);

        myassert.assertTrue(status);
        logger.info("Product searched");

        // Add to cart
        ShoppingCartPage sc = new ShoppingCartPage(driver);
        //sc.clickItemsToNavigateToCart();
        sc.clickViewCart();
        
        String totprice=sc.getTotalPrice();
        System.out.println("total price in shopping cart: "+totprice);
        myassert.assertTrue(totprice.contains("$")); //validation
        
        sc.clickCheckout();

        logger.info("Product added to cart");
     // Wait for checkout page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));

        // Checkout
        CheckoutPage cp = new CheckoutPage(driver);
        
        cp.setFirstName(randomeString().toUpperCase());
        cp.setLastName(randomeString().toUpperCase());
        cp.setaddress1("address1");
        cp.setaddress2("address2");
        cp.setcity("Chennai");
        cp.setpin("600069");
        cp.country("India");
        cp.state("Tamil Nadu");
        cp.clickOnContinueAfterBillingAddress();
        cp.clickOnContinueAfterDeliveryAddress();
        cp.setDeliveryMethodComment("Please deliver fast...");
        cp.clickOnContinueAfterDeliveryMethod();
        cp.selectTermsAndConditions();
        cp.clickOnContinueAfterPaymentMethod();
        
        logger.info("Checkout completed");

        // Validation
        String totalPrice_inOrderPage = cp.getTotalPriceBeforeConfOrder();
        System.out.println("Total price in confirmed order page: "+totalPrice_inOrderPage);
        myassert.assertEquals(totalPrice_inOrderPage, "$205.00"); //validation
        logger.info("Price validated successfully");
        myassert.assertAll();

        logger.info("Finished TC006_EndToEndTest");
        
    }
      
}
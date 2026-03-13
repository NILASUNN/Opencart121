package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC005_AddToCartTest extends BaseClass {

    @Test(groups={"regression","Master"})
    public void verify_addtocart() throws InterruptedException{

        logger.info("Starting TC005_AddToCartTest");

        try 
        {
        	
        HomePage hp = new HomePage(driver);
        hp.enterProductName("iPhone");
        hp.clickSearch();

        SearchPage sp = new SearchPage(driver);
        if(sp.isProductExist("iPhone"))
        {
        	sp.selectProduct("iPhone");
        	sp.setQuantity("2");
        	sp.addTocard();
        	
        }
        Assert.assertEquals(sp.checkConfMsg(), true);
       
    }catch (Exception e)
        {
    	Assert.fail();
        }
        logger.info("Finished TC005_AddToCartTest");
    }
}
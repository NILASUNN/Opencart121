package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

    @Test(groups={"regression","Master"})
    public void verify_productSearch() {

        logger.info("Starting TC004_SearchProductTest");

        try {
        HomePage hm = new HomePage(driver);
        hm.enterProductName("Mac");
        hm.clickSearch();
        
        SearchPage sp = new SearchPage(driver);
        sp.isProductExist("MacBook");
        Assert.assertEquals(sp.isProductExist("MacBook"), true);
    }catch(Exception e) {
    	Assert.fail();
    }
        logger.info("Finished TC004_SearchProductTest");
    }
    
}
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	WebElement linkLogin;
	
	@FindBy(xpath = "//input[@name='search']")
    WebElement txtSearch;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    WebElement btnSearch;
    
	public void ClickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void ClickRegister()
	{
		lnkRegister.click();
	}
	
	public void ClickLogin()
	{
		linkLogin.click();
	}

	public void enterProductName(String productName) {
        txtSearch.clear();
        txtSearch.sendKeys(productName);
    }


	public void clickSearch() {
        btnSearch.click();
    }

	
}

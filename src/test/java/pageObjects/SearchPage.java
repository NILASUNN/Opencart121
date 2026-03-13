package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath="//input[@name='search']")
    WebElement txtSearch;

    @FindBy(xpath="//button[@class='btn btn-default btn-lg']")
    WebElement btnSearch;
    
    @FindBy(id="input-quantity")
    WebElement txtQuantity;

    @FindBy(id="button-cart")
    WebElement btnAddToCart;

    @FindBy(xpath="//div[contains(@class,'alert-success')]")
    WebElement confMsg;

    // Actions
    public void enterProductName(String product)
    {
        txtSearch.clear();
        txtSearch.sendKeys(product);
    }

    public void clickSearch()
    {
        btnSearch.click();
    }

    public boolean isProductExist(String productName)
    {
        try {
            WebElement product = driver.findElement(
                    By.xpath("//a[normalize-space()='"+productName+"']")
            );
            return product.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }

    public void selectProduct(String productName)
    {
        driver.findElement(
                By.xpath("//a[normalize-space()='"+productName+"']")
        ).click();
    }

    public void setQuantity(String quantity)
    {
        txtQuantity.clear();
        txtQuantity.sendKeys(quantity);
    }

    public void addTocard()
    {
        btnAddToCart.click();
    }

    public Boolean checkConfMsg()
    {
        try {
            return confMsg.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
}
package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    WebDriver driver;

    public ShoppingCartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(xpath="//a[@title='Shopping Cart']")
    WebElement linkShoppingCart;

    @FindBy(linkText="Checkout")
    WebElement btnCheckout;
    
    @FindBy(xpath="//strong[text()='Total:']/parent::td/following-sibling::td")
    WebElement txtTotalPrice;

    @FindBy(xpath="//div[@class='product-thumb']//a")
    List<WebElement> products; // all products on homepage or listing

    @FindBy(xpath="//button[@data-original-title='Add to Cart']")
    List<WebElement> btnAddToCart; // Add buttons for products
    

    //Actions
    public void openCart()
    {
        linkShoppingCart.click();
    }


    // Click a product and add to cart
    public void clickItemsToNavigateToCart() {
    	if(!btnAddToCart.isEmpty()) {
            btnAddToCart.get(0).click();  // click first Add to Cart button
    	}
    }

    // Click the cart icon or link
    public void clickViewCart()
    {
    	linkShoppingCart.click();
    }

    public String getTotalPrice()
    {
        return txtTotalPrice.getText();
    }

    public void clickCheckout()
    {
    	btnCheckout.click();
    }
	
}
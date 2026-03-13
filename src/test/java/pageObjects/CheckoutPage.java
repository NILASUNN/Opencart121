package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Billing Address Locators
    @FindBy(id="input-payment-firstname")
    WebElement txtFirstName;

    @FindBy(id="input-payment-lastname")
    WebElement txtLastName;

    @FindBy(id="input-payment-address-1")
    WebElement txtAddress1;

    @FindBy(id="input-payment-address-2")
    WebElement txtAddress2;

    @FindBy(id="input-payment-city")
    WebElement txtCity;

    @FindBy(id="input-payment-postcode")
    WebElement txtPin;

    @FindBy(id="input-payment-country")
    WebElement dropdownCountry;

    @FindBy(id="input-payment-zone")
    WebElement dropdownState;


    // Delivery Method Locators
    @FindBy(name="comment")
    WebElement txtDeliveryComment;

    @FindBy(name="agree")
    WebElement chkTerms;
    
    @FindBy(id="button-payment-address")
    WebElement btnBillingContinue;

    @FindBy(id="button-shipping-address")
    WebElement btnDeliveryAddressContinue;

    @FindBy(id="button-shipping-method")
    WebElement btnDeliveryMethodContinue;

    @FindBy(id="button-payment-method")
    WebElement btnPaymentMethodContinue;

    
    @FindBy(xpath="//table[@class='table table-bordered']//tr/td[contains(text(),'Total')]/following-sibling::td")
    WebElement lblTotalPrice;

    // Action Methods
    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setaddress1(String address) {
        txtAddress1.sendKeys(address);
    }

    public void setaddress2(String address) {
        txtAddress2.sendKeys(address);
    }

    public void setcity(String city) {
        txtCity.sendKeys(city);
    }

    public void setpin(String pin) {
        txtPin.sendKeys(pin);
    }

    public void country(String country) {
        org.openqa.selenium.support.ui.Select selectCountry = new org.openqa.selenium.support.ui.Select(dropdownCountry);
        selectCountry.selectByVisibleText(country);
    }

    public void state(String state) {
        org.openqa.selenium.support.ui.Select selectState = new org.openqa.selenium.support.ui.Select(dropdownState);
        selectState.selectByVisibleText(state);
    }

    public void clickOnContinueAfterBillingAddress() {
        btnBillingContinue.click();
    }

    public void clickOnContinueAfterDeliveryAddress() {
        btnDeliveryAddressContinue.click();
    }

    public void clickOnContinueAfterDeliveryMethod() {
        btnDeliveryMethodContinue.click();
    }

    public void clickOnContinueAfterPaymentMethod() {
        btnPaymentMethodContinue.click();
    }
    
    public void setDeliveryMethodComment(String comment) {
        txtDeliveryComment.sendKeys(comment);
    }

    public void selectTermsAndConditions() {
        if(!chkTerms.isSelected())
            chkTerms.click();
    }


    public String getTotalPriceBeforeConfOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement total = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//strong[text()='Total:']/parent::td/following-sibling::td")
                ));

        return total.getText();
    }
}
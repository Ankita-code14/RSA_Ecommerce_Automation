package ecommerceproject.FrameworkPackage.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerceproject.FrameworkPackage.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private @FindBy(css ="[placeholder='Select Country']")
	WebElement countryDropdown;
	
	private @FindBy(xpath ="(//button[contains(@class, 'ta-item')])[2]")
	WebElement countryOption;
	
	private By dropdownResult = By.cssSelector(".ta-results");
	private By btnPlaceOrder = By.cssSelector(".action__submit");
	
	public void selectContury(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryDropdown, countryName).build().perform();
		getVisibilityOfElementLocatedBy(dropdownResult);
		countryOption.click();
	}
	
	public OrderConfirmationPage placedOrder() {
		getVisibilityOfElementLocatedBy(btnPlaceOrder);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", driver.findElement(btnPlaceOrder));
		return new OrderConfirmationPage(driver);
	}
}

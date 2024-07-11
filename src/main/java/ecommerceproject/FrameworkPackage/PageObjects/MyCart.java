package ecommerceproject.FrameworkPackage.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ecommerceproject.FrameworkPackage.AbstractComponents.AbstractComponents;

public class MyCart extends AbstractComponents{

	WebDriver driver;
	
	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	private @FindBy(css="[class='cartSection'] h3")
	List<WebElement> cartProductList;
	
	private @FindBy(css = "[class*='subtotal'] button")
	WebElement btnCheckout;
	

	public Boolean verifyProductOnCartPage(String prodName) {
		return cartProductList.stream()
				.anyMatch(prod -> prod.getText().equalsIgnoreCase(prodName));
	}
	
	public CheckoutPage goToCheckout() {
		btnCheckout.click();
		return new CheckoutPage(driver);
	}
}

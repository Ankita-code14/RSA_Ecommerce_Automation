package ecommerceproject.FrameworkPackage.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private @FindBy(css = ".hero-primary")
	WebElement thankyouMessage;
	
	public String getOrderConfirmationMessage() {
		return thankyouMessage.getText();
	}
	
	
}

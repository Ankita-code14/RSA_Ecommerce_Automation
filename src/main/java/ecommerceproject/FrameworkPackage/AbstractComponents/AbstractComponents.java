package ecommerceproject.FrameworkPackage.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerceproject.FrameworkPackage.PageObjects.MyCart;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement linkCart;

	public void getVisibilityOfElementLocatedBy(By findBy) {
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		expWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void getVisibilityOfElement(WebElement webElement) {
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		expWait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void getInvisibilityofElement(WebElement webElement) {
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		expWait.until(ExpectedConditions.invisibilityOf(webElement));
	}
	
	
	public MyCart goToMyCart() {
		linkCart.click();
		return new MyCart(driver);
	}
	
	
	
	
	
}

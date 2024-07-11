package ecommerceproject.FrameworkPackage.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecommerceproject.FrameworkPackage.AbstractComponents.AbstractComponents;

public class ProductCatlog extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatlog(WebDriver driver) {
		//driver initialization in constructor 
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory design pattern
	
	private @FindBy(css = ".card-body")
	List<WebElement> productList;
	
	private @FindBy(css = ".ng-animating")
	WebElement loader;

	
	private By productBy = By.cssSelector(".card-body");
	private By productText = By.cssSelector("b");
	private By btnAddToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		getVisibilityOfElementLocatedBy(productBy);
		return productList;
	}
	
	public WebElement getProductByName(String prodName) {
		WebElement product = getProductList().stream()
				.filter(prod -> prod.findElement(productText).getText().equals(prodName)).findFirst()
				.orElse(null);
		return product;
	}
	
	public void addProductToCart(String prodName) {
		WebElement product = getProductByName(prodName);
		product.findElement(btnAddToCart).click();
		
		getVisibilityOfElementLocatedBy(toastMessage);
		getInvisibilityofElement(loader);
	}

}

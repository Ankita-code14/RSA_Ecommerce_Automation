package ecommerceproject.FrameworkPackage.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceproject.FrameworkPackage.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		//driver initialization in constructor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory design pattern
	
	private @FindBy(id = "userEmail")
	WebElement userEmail;
	
	private @FindBy(id = "userPassword")
	WebElement userPassword;
	
	private @FindBy(id = "login")
	WebElement btnLogin;
	
	private @FindBy(xpath = "//div[contains(text(),'*Enter Valid Email')]")
	WebElement errormsgEmail;
	
	private @FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorToastMsgForEmailPassword;
	
	private @FindBy(xpath = "//div[@aria-label='Login Successfully']")
	WebElement successToastMsgValidLogin;
	
	
	public ProductCatlog appLogin(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		btnLogin.click();
		return new ProductCatlog(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessageEmail() {
		getVisibilityOfElement(errormsgEmail);
		return errormsgEmail.getText();
	}
	
	public String getErrorToastMsgForEmailPassword() {
		getVisibilityOfElement(errorToastMsgForEmailPassword);
		return errorToastMsgForEmailPassword.getText();
	}
	
	public String getSuccessToastMsgValidLogin() {
		getVisibilityOfElement(successToastMsgValidLogin);
		return successToastMsgValidLogin.getText();
		
	}
}

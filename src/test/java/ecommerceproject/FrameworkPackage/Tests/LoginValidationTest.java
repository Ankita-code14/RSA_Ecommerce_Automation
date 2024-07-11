package ecommerceproject.FrameworkPackage.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ecommerceproject.FrameworkPackage.PageObjects.CheckoutPage;
import ecommerceproject.FrameworkPackage.PageObjects.MyCart;
import ecommerceproject.FrameworkPackage.PageObjects.OrderConfirmationPage;
import ecommerceproject.FrameworkPackage.PageObjects.ProductCatlog;
import ecommerceproject.FrameworkPackage.TestComponents.BaseTest;
import ecommerceproject.FrameworkPackage.TestComponents.Retry;

public class LoginValidationTest extends BaseTest{

	@Test(retryAnalyzer = Retry.class)
	public void loginValidationWithWrongEmail() throws IOException {

		login.appLogin("12323", "Ankita$14");
		Assert.assertEquals("*Enter Validy Email", login.getErrorMessageEmail());
	}
	
	
	@Test
	public void loginValidationWithWrongPassword() throws IOException {

		login.appLogin("ankita@test.com", "Ankita$14123");
		Assert.assertEquals(login.getErrorToastMsgForEmailPassword(), "Incorrect email or password.");
	}

	@Test
	public void loginValidationWithWrongEmailAndPassword() throws IOException {

		login.appLogin("ankita@teest.com", "Ankita$1423");
		Assert.assertEquals(login.getErrorToastMsgForEmailPassword(), "Incorrect email or password.");
	}
	
	@Test
	public void loginValidationWithCorrectEmailAndPassword() throws IOException {

		login.appLogin("ankita@test.com", "Ankita$14");
		Assert.assertEquals(login.getSuccessToastMsgValidLogin(), "Login Successfully");
	}

}

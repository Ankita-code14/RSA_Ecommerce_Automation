package ecommerceproject.FrameworkPackage.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ecommerceproject.FrameworkPackage.PageObjects.CheckoutPage;
import ecommerceproject.FrameworkPackage.PageObjects.MyCart;
import ecommerceproject.FrameworkPackage.PageObjects.OrderConfirmationPage;
import ecommerceproject.FrameworkPackage.PageObjects.ProductCatlog;
import ecommerceproject.FrameworkPackage.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	@Test(groups = {"purchase"}, dataProvider="getData")
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatlog prodCatlog = login.appLogin(input.get("email"), input.get("password"));

		prodCatlog.addProductToCart(input.get("productName"));
		MyCart myCart = prodCatlog.goToMyCart();

		Boolean cartProdNameMatch = myCart.verifyProductOnCartPage(input.get("productName"));
		Assert.assertTrue(cartProdNameMatch);
		CheckoutPage checkoutPage = myCart.goToCheckout();

		checkoutPage.selectContury("India");
		OrderConfirmationPage ordconPage = checkoutPage.placedOrder();

		String confirmMsg = ordconPage.getOrderConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods ="submitOrder")
	
	public void OrderHistoryTest() {
		//Test
	}
	
	
	@DataProvider
	
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\ecommerceproject\\FrameworkPackage\\TestData\\purchaseData.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
/*	public Object[][] getData() {
		//Data provider using Array object
		return new Object[][] {{"ankita@test.com", "Ankita$14", "ADIDAS ORIGINAL"}, {"sandeep@test.com", "Sandy*09", "IPHONE 13 PRO"}};
		
		
		//Data provider using HashMap
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "ankita@test.com");
		map.put("password", "Ankita$14");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "sandeep@test.com");
		map1.put("password", "Sandy*09");
		map1.put("productName", "IPHONE 13 PRO");
	}
*/
}

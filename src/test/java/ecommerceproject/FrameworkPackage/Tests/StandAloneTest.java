package ecommerceproject.FrameworkPackage.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecommerceproject.FrameworkPackage.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ankita@test.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ankita$14");
		driver.findElement(By.id("login")).click();

		expWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> productList = driver.findElements(By.cssSelector(".card-body"));

		WebElement product = productList.stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		expWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		expWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProductList = driver.findElements(By.cssSelector("[class='cartSection'] h3"));

		Boolean cartProdNameMatch = cartProductList.stream()
				.anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(cartProdNameMatch);

		driver.findElement(By.cssSelector("[class*='subtotal'] button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		expWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		
		expWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", submit);
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
	}

}

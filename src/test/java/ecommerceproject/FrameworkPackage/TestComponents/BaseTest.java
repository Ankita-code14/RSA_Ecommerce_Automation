package ecommerceproject.FrameworkPackage.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerceproject.FrameworkPackage.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage login;
	
	public WebDriver initializeDriver() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\test\\java\\ecommerceproject\\FrameworkPackage\\Resources\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browserName = System.getProperty("browserName") != null ? System.getProperty("browserName") : prop.getProperty("browserName");
				
		//prop.getProperty("browserName");
		
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")){
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge")){
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to String
		
		String jsonContent = FileUtils.readFileToString(
				new File(filePath), StandardCharsets.UTF_8);
	
		//convert Stringcontent to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(sourcefile, destinationfile);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage lunchApplication() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.goTo();
		return login;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		
		driver.quit();
	}
	
}

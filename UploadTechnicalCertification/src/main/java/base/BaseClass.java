package base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public static WebDriver driver;
	public Properties prop;

	public BaseClass() {
		this.prop = readProp();
	}

	public static void browserInitOnce() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Acer\\Downloads\\chromedriver_win32\\new\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void initBrowser() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public Properties readProp() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./src/main/resources/configuration/configuration.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void tearDown() {
		driver.quit();
	}

}

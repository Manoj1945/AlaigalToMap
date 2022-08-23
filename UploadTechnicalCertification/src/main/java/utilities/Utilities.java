package utilities;

import java.io.File;
import java.time.Duration;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	private WebDriverWait wait;
	private WebDriver driver;
	
	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForElementClickable(WebElement webElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public WebElement presenceOfElementLocated(By webElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(webElement));
	}

	public boolean waitForTextMatch(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.textMatches(locator, Pattern.compile("\\d+")));
	}

	public void selectByText(WebElement element, String text) {
		
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public void selectByIndex(WebElement element) {
		Select select = new Select(element);
		select.selectByIndex(1);
	}

	public void uploadFile(WebElement element, String directory) {
		element.sendKeys(new File(directory).getAbsolutePath());
	}
		
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}
}

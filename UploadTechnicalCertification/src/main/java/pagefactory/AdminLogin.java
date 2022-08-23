package pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class AdminLogin extends BaseClass {

	Utilities util;

	public AdminLogin() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login-email")
	private WebElement adminLoginId;

	@FindBy(id = "login-password")
	private WebElement adminLoginPassword;

	@FindBy(xpath = "//*[text()='Log in']")
	private WebElement adminLoginButton;

	@FindBy(xpath = "//*[@href='http://maplogik.com/index.php/admin/logout']")
	private WebElement adminLogoutButton;

	public void loginIdTextboxFill() {
		adminLoginId.sendKeys(prop.getProperty("adminEmailId"));
	}

	public void loginPasswordTextboxFill() {
		adminLoginPassword.sendKeys(prop.getProperty("adminPassword"));
	}

	public AdminHome clickLoginButton() {
		adminLoginButton.click();
		return new AdminHome();
	}

	public void clickAdminLogout() {
		adminLogoutButton.click();
	}
}

package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class StudentLogin extends BaseClass {

	Utilities util;

	public StudentLogin() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	private By testOtpLocator = By.id("test_otp");

	@FindBy(id = "login-student-id")
	private WebElement studentLoginId;

	@FindBy(id = "login-mobile")
	private WebElement mobileNumber;

	@FindBy(xpath = "//*[text()='Log in']")
	private WebElement clickLogin;

	@FindBy(id = "test_otp")
	private WebElement TypeOtp;

	@FindBy(id = "login-otp")
	private WebElement OtpTextBox;

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement clickSubmit;

	public void fillStuIdTextbox(String stdId) {
		studentLoginId.sendKeys(stdId);
	}

	public void fillMobTextbox(String mobNum) {
		mobileNumber.sendKeys(mobNum);
	}

	public void clickLoginButton() {
		clickLogin.click();
	}

	public void EnterOtpTextBox() {
		util.waitForTextMatch(testOtpLocator);
		String otp = TypeOtp.getText();
		OtpTextBox.sendKeys(otp);
	}

	public StudentDashboard clickOtpSubmitButton() {
		clickSubmit.click();
		return new StudentDashboard();
	}

}

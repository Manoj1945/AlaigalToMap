package pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class StudentDashboard extends BaseClass {

	Utilities util;
	public StudentDashboard() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Co Curricular Achievements']")
	private WebElement coCurricularAchivDropdown;
	
	@FindBy(xpath = "//*[text()='Technical Record Creation']") 
	private WebElement technicalCertificationStudentButton; 
	
	@FindBy(xpath = "//*[@href='http://maplogik.com/index.php/student/logout']")
	private WebElement logOut;

	public void clickcoCurricularAchivDropdown() {
		util.waitForElementClickable(coCurricularAchivDropdown).click();
	}
	
	public StudentTechnicalRecord clickTechnicalRecordPageStudentButton() {
		util.waitForElementClickable(technicalCertificationStudentButton).click();
		return new StudentTechnicalRecord();
	}
	
	public void clickLogout() {
		logOut.click();
	}
}

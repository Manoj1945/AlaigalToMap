package pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class AdminHome extends BaseClass {

	Utilities util;

	public AdminHome() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text()='Co Curricular Achievements']")
	private WebElement AdminCoCurricularDropdown; 

	@FindBy(xpath = "//*[text()='Technical Record Creation']")
	private WebElement AdminTechnicalrecordButton; 
	
	public void clickCocurricularButtonAdmin() {
		util.waitForElementClickable(AdminCoCurricularDropdown).click(); 
	}
	
	public AdminTechnicalRecordApproval clickAdminTechnicalrecordButton() { 
		util.waitForElementClickable(AdminTechnicalrecordButton).click(); 
		return new AdminTechnicalRecordApproval(); 
	}
}

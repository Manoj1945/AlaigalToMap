package pagefactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class AdminTechnicalRecordApproval extends BaseClass {

	Utilities util;

	public AdminTechnicalRecordApproval() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='basic-datatable'] //div[@class='col-sm-3'][2]")
	private WebElement approvedTechnicalrecordButton;

	@FindBy(xpath = "//*[@id='basic-datatable'] //div[@class='col-sm-3'][4]")
	private WebElement declinedTechnicalrecordButton;

	@FindBy(xpath = "//*[@id='basic-datatable'] //div[@class='col-sm-3'][3]")
	private WebElement pendingTechnicalrecordButton;

	@FindBy(xpath = "//*[@type='search']")
	private WebElement searchTextBox;

	@FindBy(xpath = "//*[@name='datatable_length']")
	private WebElement showEntries;

	@FindBy(xpath = "//*[@id='datatable']/tbody/tr/td[3]") 
	private List<WebElement> pendingTechnicalrecordsTitles;

	@FindBy(xpath = "(//*[@type='submit'])[2]")
	private WebElement approveTechnicalrecordButton;

	@FindBy(xpath = "(//*[@type='submit'])[1]")
	private WebElement declineTechnicalrecordButton;

	public void selectEntryShowLimit() {
		util.selectByText(showEntries, prop.getProperty("showEntryLimit"));
	}

	public void searchForStudent() {
		searchTextBox.clear();
		searchTextBox.sendKeys(prop.getProperty("studentId"));
	}

	private void findingMatchingTechnicalrecord(String certificateTitle) {
		int index = 1;
		List<WebElement> allPendingTitles = pendingTechnicalrecordsTitles;
		for (WebElement we : allPendingTitles) {
			String technicalrecordDetailedView = "//*[@id='datatable']/tbody/tr[" + index + "]/td[6]/a[1]";
			if (we.getText().equalsIgnoreCase(certificateTitle)) {
				driver.findElement(By.xpath(technicalrecordDetailedView)).click();
				break;
			} else {
				index++;
			}
		}
	}

	public void approveTechnicalRecord() throws Exception {
		String certificateTitle = prop.getProperty("approveCertificate");
		try {
			findingMatchingTechnicalrecord(certificateTitle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			util.waitForElementClickable(approveTechnicalrecordButton).click();
		} catch (Exception e) {
			throw new Exception("The " + certificateTitle + " is not uploaded or not present in the list");
		}
	}

	public void declineTechnicalRecord() throws Exception {
		String certificateTitle = prop.getProperty("declineCertificate");
		try {
			findingMatchingTechnicalrecord(certificateTitle);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			util.waitForElementClickable(declineTechnicalrecordButton).click();
		} catch (Exception e) {
			throw new Exception("The " + certificateTitle + " is not uploaded or not present in the list");
		}
	}

	public void clickPendingPageButton() {
		util.waitForElementClickable(pendingTechnicalrecordButton).click();
	}

	public void clickApprovePageButton() {
		util.waitForElementClickable(approvedTechnicalrecordButton).click();
	}

	public void clickDeclinePageButton() {
		util.waitForElementClickable(declinedTechnicalrecordButton).click();
	}

	public Map<String, String> techicalrecordTitleAndStudentId(String certifTitle) {
		int index = 1;
		Map<String, String> techicalrecordTitleAndStudentId = new HashMap<>();
		List<WebElement> allPendingTitles = pendingTechnicalrecordsTitles; // common for all pages
		try {
			for (WebElement we : allPendingTitles) {
				if (we.getText().equalsIgnoreCase(certifTitle)) {
					String stdIdFetchXpath = "//*[@id='datatable']/tbody/tr[" + index + "]/td[1]";
					String stdIdFetched = util.findElement(By.xpath(stdIdFetchXpath)).getText();
					techicalrecordTitleAndStudentId.put("certificateTitle", we.getText());
					techicalrecordTitleAndStudentId.put("stdId", stdIdFetched);
					break;
				} else {
					index++;
				}
			}
		} catch (Exception e) {
		}
		return techicalrecordTitleAndStudentId;
	}
}

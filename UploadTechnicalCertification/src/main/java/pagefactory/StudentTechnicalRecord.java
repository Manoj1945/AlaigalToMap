package pagefactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utilities.Utilities;

public class StudentTechnicalRecord extends BaseClass {

	Utilities util;

	public StudentTechnicalRecord() {
		util = new Utilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "title")
	private WebElement certificateTitleTextbox;
	
	@FindBy(name = "which_field")
	private WebElement certificateFieldSelectDropdown;
	
	@FindBy(id = "place")
	private WebElement placeTextBox;
	
	@FindBy(name = "award")
	private WebElement awardDropdown;
	
	@FindBy(name = "scope")
	private WebElement scopeOfCertificationDropdown;

	@FindBy(name = "year")
	private WebElement yearOfcertificationButton;

	@FindBy(id = "certificate")
	private WebElement certifiUpload;

	@FindBy(id = "submitbtn")
	private WebElement submitButton;

	public Map<String, String> readExcelFileOnlyByRow(int columnNum, String excelLocation) {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(excelLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheet("Sheet1");
		DataFormatter format = new DataFormatter();
		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();
		Map<String, String> certifiDetail = new HashMap<String, String>();
		for (int i = firstRow; i <= lastRow; i++) {
			XSSFRow row = sheet.getRow(i);
			String key = format.formatCellValue(row.getCell(0));
			String value = format.formatCellValue(row.getCell(columnNum));
			certifiDetail.put(key, value);
		}
		return certifiDetail;
	}

	Map<String, String> certificationDetatils;

	public void certirficateTitileTextboxFill(int rowNum, String certfiTitle) {
		String excelLocation = prop.getProperty("certificationDetailExcel");
		certificationDetatils = readExcelFileOnlyByRow(rowNum + 1, excelLocation);
		certificateTitleTextbox.sendKeys(certfiTitle);
	}

	public void placeTextBoxFill() {
		placeTextBox.sendKeys(certificationDetatils.get("Place"));
	}
	
	public void selectFieldRecord() {
		util.selectByText(certificateFieldSelectDropdown, certificationDetatils.get("In which field the record was created"));
	}
	
	public void selectScopeOfRecord() {
		util.selectByText(scopeOfCertificationDropdown, certificationDetatils.get("Scope of the record"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void selectAwardDropdown() {
		util.selectByIndex(awardDropdown);
	}
	
	public void selectYearOfCertification() throws AWTException {
		yearOfcertificationButton.click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_3);
		robot.keyRelease(KeyEvent.VK_3);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_9);
		robot.keyRelease(KeyEvent.VK_9);
	}

	public void uploadCertificateFile() {
		util.uploadFile(certifiUpload, certificationDetatils.get("Upload Certificate"));
	}

	public void clickSubmitButton() {
		util.waitForElementClickable(submitButton).click();
	}
}

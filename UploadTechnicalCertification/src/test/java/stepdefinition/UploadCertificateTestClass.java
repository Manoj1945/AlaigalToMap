package stepdefinition;

import java.awt.AWTException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.StudentTechnicalRecord;
import pagefactory.StudentDashboard;
import pagefactory.StudentLogin;

public class UploadCertificateTestClass {

	StudentDashboard studentDashboard;
	StudentTechnicalRecord studentTechnicalPage;
	static int coulmnNo;

	@Given("Student should login with the given id and mobile number")
	public void student_should_login_with_the_given_id_and_mobile_number() {
		StudentLogin stdlogin = new StudentLogin();
		stdlogin.fillStuIdTextbox(stdlogin.prop.getProperty("studentId"));
		stdlogin.fillMobTextbox(stdlogin.prop.getProperty("studentPassword"));
		stdlogin.clickLoginButton();
		stdlogin.EnterOtpTextBox();
		studentDashboard = stdlogin.clickOtpSubmitButton();
	}

	@When("click on co-curricular achievement and click on technical record button")
	public void click_on_co_curricular_achievement_and_click_on_technical_record_button() {
		studentDashboard.clickcoCurricularAchivDropdown();
		studentTechnicalPage = studentDashboard.clickTechnicalRecordPageStudentButton();
	}

	@Then("fill the details {string} and upload certificate")
	public void fill_the_details_and_upload_certificate(String certifTitle) throws AWTException {
		studentTechnicalPage.certirficateTitileTextboxFill(coulmnNo, certifTitle);
		studentTechnicalPage.selectFieldRecord();
		studentTechnicalPage.placeTextBoxFill();
		studentTechnicalPage.selectYearOfCertification();
		studentTechnicalPage.selectScopeOfRecord();
		studentTechnicalPage.selectAwardDropdown();
		studentTechnicalPage.uploadCertificateFile();
		studentTechnicalPage.clickSubmitButton();
		coulmnNo++;
	}

	@Then("click the logout button on student page")
	public void click_the_logout_button_on_student_page() {
		studentDashboard.clickLogout();
	}
}

package stepdefinition;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.AdminTechnicalRecordApproval;
import pagefactory.AdminHome;
import pagefactory.AdminLogin;

public class ApproveAndVerifyCertificate {

	AdminLogin adminloginPage;
	AdminHome adminHomePage;
	AdminTechnicalRecordApproval adminCoCurricularCertificationApprovalPage;
	String expectedCerifiTitle;

	@Given("login as admin with the given emailId and password")
	public void login_as_admin_with_the_given_email_id_and_password() {
		adminloginPage = new AdminLogin();
		adminloginPage.loginIdTextboxFill();
		adminloginPage.loginPasswordTextboxFill();
		adminHomePage = adminloginPage.clickLoginButton();
	}

	@When("on admin page click on co-curricular achievement and click on technical record button")
	public void on_admin_page_click_on_co_curricular_achievement_and_click_on_technical_record_button() {
		adminHomePage.clickCocurricularButtonAdmin();
		adminCoCurricularCertificationApprovalPage = adminHomePage.clickAdminTechnicalrecordButton();
	}

	@Then("click {string} technical records button and search the student with the given Id")
	public void click_technical_records_button_and_search_the_student_with_the_given_id(String key) {
		expectedCerifiTitle = adminCoCurricularCertificationApprovalPage.prop.getProperty(key);
		switch (key) {
		case "pendingCertificate":
			adminCoCurricularCertificationApprovalPage.clickPendingPageButton();
			break;
		case "approveCertificate":
			adminCoCurricularCertificationApprovalPage.clickApprovePageButton();
			break;
		case "declineCertificate":
			adminCoCurricularCertificationApprovalPage.clickDeclinePageButton();
			break;
		}
		adminCoCurricularCertificationApprovalPage.selectEntryShowLimit();
		adminCoCurricularCertificationApprovalPage.searchForStudent();
	}

	@When("find the particular certificate and click approve button")
	public void find_the_particular_certificate_and_click_approve_button() throws Exception {
		adminCoCurricularCertificationApprovalPage.approveTechnicalRecord();
	}

	@Then("click the logout button on admin page")
	public void click_the_logout_button_on_admin_page() {
		adminloginPage.clickAdminLogout();
		;
	}

	@When("find the particular certificate and click decline button")
	public void find_the_particular_certificate_and_click_decline_button() throws Exception {
		adminCoCurricularCertificationApprovalPage.declineTechnicalRecord();
	}

	@Then("verify whether the particular certificate is present")
	public void verify_whether_the_particular_certificate_is_present() {
		Map<String, String> certificateTitleAndStdId = adminCoCurricularCertificationApprovalPage
				.techicalrecordTitleAndStudentId(expectedCerifiTitle);
		String stdIDActual = certificateTitleAndStdId.get("stdId");
		String certificateTitleActual = certificateTitleAndStdId.get("certificateTitle");
		String expectedStdID = adminCoCurricularCertificationApprovalPage.prop.getProperty("studentId");
		Assert.assertEquals(stdIDActual, expectedStdID);
		Assert.assertEquals(certificateTitleActual, expectedCerifiTitle);
	}
}

package hooks;

import base.BaseClass;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;


public class HookClass extends BaseClass {

	@BeforeAll
	public static void onceOpen() {
		browserInitOnce();
	}
	
	@Before(order = 0 ,value = "@studentLogin")
	public void studentLoginPage() {
		initBrowser();
		driver.get(prop.getProperty("studentLoginUrl"));
	}
	
	@Before(order = 1, value = "@adminLogin")
	public void adminLoginPage() {
		initBrowser();
		driver.get(prop.getProperty("adminLoginUrl"));
	}
	
	@AfterAll
	public static void closeBrowse() {
		tearDown();
	}
	
}

package stepDefinitions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

import org.junit.Assert;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class NopCommerceSteps extends BaseClass{
	
	@Before
	public void setup() throws IOException
	{
		logger= LogManager.getLogger(this.getClass());
		System.setProperty("log4j.configurationFile", "src/test/resources/log4j2.xml");
		
		//reading properties file
		pconfig = new Properties();
		FileInputStream configPropFile = new FileInputStream("./src//test//resources//config.properties");
		pconfig.load(configPropFile);
		
		String br =pconfig.getProperty("browser");
		if(br.equalsIgnoreCase("chrome"))
		{
		driver=new ChromeDriver(); //launch the chrome browser
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
		driver=new FirefoxDriver(); //launch the firefox browser
		}
		
		else if(br.equalsIgnoreCase("edge"))
		{
		driver=new InternetExplorerDriver(); //launch the InternetExplorer browser
		}
		else
		{
			System.out.println("Invalid browser");
		}
		
		logger.info("*****************Launching Browser*****************");
	}
	
	
	@Given("User launch chrome browser")
	public void launch_chrome_browser()
	{
		lp=new LoginPage(driver); //Object of LoginPage & using this we can access all methods of LoginPage class
      
	}
	
	@When("User opens URL {string}")
	public void user_opens_URL(String url) 
	{
		logger.info("*****************Launching URL*****************");
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	@And("User enters Email as {string} and Password as {string}")
	public void enterCredentials(String email,String pswrd)
	{
		logger.info("*****************Providing login details*****************");
		lp.setEmail(email);
		lp.setPassword(pswrd);
	}
	
	@And("User Clicks on Login")
	public void clickLogin() throws InterruptedException
	{
		logger.info("*****************Started login*****************");
		lp.clickLogin();
		Thread.sleep(3000);
	}
	
	@Then("Page Title should be {string}")
	public void verify_pagetitle(String title) throws InterruptedException
	{
		Thread.sleep(3000);
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
			logger.info("*****************Login was unsuccessful*****************");
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("*****************Login was successful*****************");
			Assert.assertEquals(title, driver.getTitle());	
		}
		
	}
	
	@When("User click on Log out link")
	public void clickLogout() throws InterruptedException
	{
		logger.info("*****************Click on logout link*****************");
		Thread.sleep(3000);
		lp.clickLogout();
		
	}
	
	@And("close browser")
	public void close_browser()
	{
		logger.info("*****************Closing Browser*****************");
		driver.quit();
	}
	
	//customer feature step definition	
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard()
	{
    cp = new AddCustomerPage(driver);	
	Assert.assertEquals("Dashboard / nopCommerce administration",cp.getPageTitle());			
	}
	
	
	@When("User clicks on Customers Menu")
	public void user_clicks_on_customers_menu() throws InterruptedException
	{
	  logger.info("*****************User clicks on Customers Menu*****************");
      cp.clickOnCustomersMenu();
      Thread.sleep(3000);
	}
	
	@And("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException
	{
		logger.info("*****************User clicks on Customers Menu item*****************");
		cp.clickOnCustomersMenuItem();
		Thread.sleep(3000);
	}
	@And("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException
	{
		logger.info("*****************User clicks on Add new button*****************");
		cp.clickOnAddnewButton();
		Thread.sleep(2000);
	}
	
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", cp.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	  
		logger.info("*****************Adding new customer information*****************");
	String email = randomString()+"@gmail.com";
	cp.setEmail(email);
	cp.setPwd("test123");
	cp.setFname("ABC");
	cp.setLname("Kumar");
	cp.setGender("Male");
	cp.setCompany("QA");
	cp.clickOntaxExempt();
	//cp.setCustomerRole("Registered");
	Thread.sleep(3000);
	cp.setManagerOfVendor("Vendor 1");
	cp.setAdminContent("Selenium+Cucumber");
	}
	@And("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("*****************Saving customer data*****************");
	   cp.clickSave();
	   Thread.sleep(3000);
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg)  {
		 
		if(driver.getPageSource().contains(msg))
		{
			Assert.assertTrue(true);
		}
		else
		{
			driver.close();
			Assert.assertTrue(false);
		}
	}
	
	//customer search feature using email id
	@When("Enter Customer email")
	public void enter_customer_email() {
		logger.info("*****************Searching existing customer using email id*****************");
	    sc = new SearchCustomer(driver);
	    sc.setEmail("steve_gates@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	   sc.clickSearch();
	   Thread.sleep(5000);
	}
	@Then("user should get email in the search table")
	public void user_should_get_email_in_the_search_table() {
	  boolean status = sc.searchByEmail("steve_gates@nopCommerce.com");
	  Assert.assertEquals(true,status);
	
	}
	
	
	//customer search feature using first name &last name
	@When("Enter Customer FirstName")
	public void enter_customer_firstname() {
		logger.info("*****************Searching customer by name*****************");
	   sc = new SearchCustomer(driver);
	   sc.setFirstName("Victoria");
	}
	@When("Enter Customer LastName")
	public void enter_customer_lastname() {
	   sc.setLastName("Terces");
	}
	
	@Then("user should get name in the search table")
	public void user_should_get_name_in_the_search_table() {
		boolean status = sc.searchCustomerByName("Victoria Terces");
		  Assert.assertEquals(true,status);
	}
	
}

package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage cp;
	public SearchCustomer sc;
	public static Logger logger; //create logger class object
	public Properties pconfig;
	
	//Created for generating random string for unique email id
	public static String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
}

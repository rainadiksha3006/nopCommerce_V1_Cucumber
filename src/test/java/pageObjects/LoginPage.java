package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='Email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='Password']") WebElement txtPassword;
	@FindBy(xpath="//button[normalize-space()='Log in']") WebElement btnLogin;
	@FindBy(xpath="//*[contains(text(),'Logout')]") WebElement btnLogout;
	
	public void setEmail(String email)
	{
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	public void clickLogout()
	{
		btnLogout.click();
	}
}

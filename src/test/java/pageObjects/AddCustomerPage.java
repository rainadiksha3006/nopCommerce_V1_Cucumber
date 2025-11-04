package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
public WebDriver driver;
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy (xpath="//a[@href='#']//p[contains(text(),'Customers')]") WebElement lnkCustomersMenu;
	@FindBy (xpath="//a[@href='/Admin/Customer/List']") WebElement lnkCustomersMenuItem;
	@FindBy (xpath="//a[normalize-space()='Add new']") WebElement btnAddnew;
	@FindBy(xpath="//input[@id='Email']") WebElement emailAddr;
	@FindBy(xpath="//input[@id='Password']") WebElement txtpwd;
	@FindBy(xpath="//input[@id='FirstName']") WebElement firstName;
	@FindBy(xpath="//input[@id='LastName']") WebElement lastName;
	
	@FindBy(xpath="//input[@id='Gender_Female']") WebElement rdbtnFemale;
	@FindBy(xpath="//input[@id='Gender_Male']") WebElement rdbtnMale;
	
	@FindBy(xpath="//input[@id='Company']") WebElement company;
	@FindBy(xpath="//input[@id='IsTaxExempt']") WebElement chkisTaxExempt;
	@FindBy(xpath="//div[@class='input-group-append']//input[@role='searchbox']") WebElement txtnewsletter;
	
	By txtcustomerRoles = By.xpath("//span[@aria-expanded='true']//input[@role='searchbox']");
	
	By listItemAdmin = By.xpath("//li[contains(text(),'Administrators')]");
	By listItemModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By listItemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By listItemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By listItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By vendor = By.xpath("//select[@id='VendorId']");
	By chkChangePwd = By.xpath("//input[@id='MustChangePassword']");
	By chkActive = By.xpath("//input[@id='Active']");
	
	By txtAdminComment = By.xpath("//textarea[@id='AdminComment']");	
	By btnSave = By.xpath("//button[@name='save']");
	
	//Action methods
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void clickOnCustomersMenu()
	{
		lnkCustomersMenu.click();
	}
	
	
	public void clickOnCustomersMenuItem()
	{
		lnkCustomersMenuItem.click();
	}
	
	public void clickOnAddnewButton()
	{
		btnAddnew.click();
	}
	public void setEmail(String email)
	{
		emailAddr.sendKeys(email);
	}
	public void setPwd(String pwd)
	{
		txtpwd.sendKeys(pwd);
	}
	
	public void setFname(String fname)
	{
		firstName.sendKeys(fname);
	}
	public void setLname(String lname)
	{
		lastName.sendKeys(lname);
	}
	
	public void setCustomerRole(String role) throws InterruptedException
	{
		
		driver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		if(role.equals("Administrators"))
		{
			listitem=driver.findElement(listItemAdmin);
		}
		else if(role.equals("Guests"))
		{
			listitem=driver.findElement(listItemGuests);

		}
		else if(role.equals("Registered"))
		{
			listitem=driver.findElement(listItemRegistered);

		}
		else if(role.equals("Vendors"))
		{
			listitem=driver.findElement(listItemVendors);
		}
		else
		{
			listitem=driver.findElement(listItemModerators);
		}
		listitem.click();
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drpdown = new Select(driver.findElement(vendor));
		drpdown.selectByVisibleText(value);
	}
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			rdbtnMale.click();
		}
		else if(gender.equals("Female"))
		{
			rdbtnFemale.click();
		}
		else
		{
			rdbtnMale.click();
		}
	}
	
	public void setCompany(String cmpy)
	{
		company.sendKeys(cmpy);
	}
	public void clickOntaxExempt()
	{
		chkisTaxExempt.click();
	}
	public void setAdminContent(String content) {
		driver.findElement(txtAdminComment).sendKeys(content);;
	}
	public void clickSave() {
		driver.findElement(btnSave).click();;
	}
}

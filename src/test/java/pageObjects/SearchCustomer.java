package pageObjects;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomer {

public WebDriver driver;
WaitHelper wh;
	public SearchCustomer(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wh = new  WaitHelper(driver);
	}
	
	@FindBy(xpath="//input[@id='SearchEmail']") WebElement txtemail;
	@FindBy(xpath="//input[@id='SearchFirstName']") WebElement firstname;
	@FindBy(xpath="//input[@id='SearchLastName']") WebElement lastname;
	@FindBy(xpath="//button[@id='search-customers']") WebElement searchbtn;

	@FindBy(xpath="//*[@id='customers-grid_wrapper']") WebElement table;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr") List<WebElement> tableRows;
	
	@FindBy(xpath="//table[@id='customers-grid']//col") List<WebElement> tableColumns;

	
	
	public void setEmail(String email)
	{
		wh.waitForElement(txtemail, Duration.ofMillis(5000));
		txtemail.clear();
		txtemail.sendKeys(email);
	}

	public void setFirstName(String fname)
	{
		wh.waitForElement(firstname, Duration.ofMillis(5000));
		firstname.clear();
		firstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		wh.waitForElement(lastname, Duration.ofMillis(5000));
		lastname.clear();
		lastname.sendKeys(lname);
	}
	
	public void clickSearch()
	{
		
		//wh.waitForElement(searchbtn, Duration.ofMillis(5000));
		searchbtn.click();
	}
	public int getRowCount()
	{
		return tableRows.size();
	}
	public int getNoOfColumns()
	{
		return tableColumns.size();
	}
	
	public boolean searchByEmail(String email)
	{
		boolean flag = false;
		for(int i=1;i<=getRowCount();i++)
		{
			String emailid = driver.findElement(By.xpath("//div/table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals(email))
			{
				flag=true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String name)
	{
		boolean flag = false;
		for(int i=1;i<=getRowCount();i++)
		{
			String fullname = driver.findElement(By.xpath("//div/table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[] = fullname.split(" "); //split name from the table
			String sentnames[] = name.split(" "); //split name passed in the method
			if(names[0].equals(sentnames[0]) && names[1].equals(sentnames[1]))
			{
				flag=true;
			}
		}
		return flag;
	}
	
}

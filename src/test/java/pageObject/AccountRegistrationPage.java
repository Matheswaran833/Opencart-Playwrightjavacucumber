package pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountRegistrationPage extends BasePage{
	
	
	//Locator
	private Locator txtfirstName;
	private Locator txtlastName;
	private Locator txtemail;
	private Locator txttelephone;
	private Locator txtpassword;
	private Locator txtconfirmpassword;
	private Locator chkprivacypolicy;
	private Locator btncontinue;
	private Locator confirmmessage;
	
	
	//Constructor
	public AccountRegistrationPage(Page page)
	{
		super(page);
		txtfirstName=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name"));
		txtlastName=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name"));
		txtemail=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-Mail"));
		txttelephone=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Telephone"));
		txtpassword=page.locator("input[name='password']");
		txtconfirmpassword=page.locator("input[name='confirm']");
		chkprivacypolicy=page.locator("input[name='agree']");
		btncontinue=page.locator("input[value='Continue']");
		confirmmessage=page.locator("#content h1");
		
	}
	
	//methods/Actions
	public void setfirstName(String firstname) {
		txtfirstName.fill(firstname);
	}


	public void setlastName(String lastName) {
		txtlastName.fill(lastName);;
	}


	public void setemail(String email) {
		txtemail.fill(email);;
	}


	public void settelephone(String telephone) {
		txttelephone.fill(telephone);;
	}


	public void setpassword(String pwd) {
		txtpassword.fill(pwd);;
	}


	public void setconfirmpassword(String pwd) {
		txtconfirmpassword.fill(pwd);;
	}


	public void clickprivacypolicy() {
		chkprivacypolicy.click();;
	}


	public void clickBtncontinue() {
		btncontinue.click();;
	}
	
	public String getConfirmationmsg()
	{
		try 
		{
			return confirmmessage.innerText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}

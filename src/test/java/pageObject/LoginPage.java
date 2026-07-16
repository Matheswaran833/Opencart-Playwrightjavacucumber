package pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage{
	
	//Locator
	private Locator txtEmail;
	private Locator txtpassword;
	private Locator btnLogin;
	
	
	//constructor
	public LoginPage(Page page)
	{
		super(page);
		txtEmail=page.getByRole(AriaRole.TEXTBOX, new GetByRoleOptions().setName("E-Mail Address"));
		txtpassword=page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
		btnLogin=page.locator("input[value='Login']");
		
	}
	
	//Action methods
	public void setEmail(String email) {
		txtEmail.fill(email);;
	}


	public void setpassword(String pwd) {
		txtpassword.fill(pwd);
	}


	public void clickLogin() {
		btnLogin.click();
	}


}

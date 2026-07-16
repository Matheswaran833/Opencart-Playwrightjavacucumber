package pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountLogoutPage extends BasePage{
	
	//Locator 
	private Locator msgLogout;
	private Locator btnContinue;
	
	//constructor
	public AccountLogoutPage(Page page)
	{
		super(page);
		msgLogout=page.locator("h1:has-text('Account Logout')");
		btnContinue=page.getByText("Continue");
	}
	
	//Action methods
	public boolean isAccountLogoutExists()
	{
		try 
		{
			return msgLogout.isDisabled();
		} catch (Exception e) 
		{
			return false;
		}
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}

}

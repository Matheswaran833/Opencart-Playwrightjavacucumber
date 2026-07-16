package pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MyAccountPage extends BasePage{
	
	//Locator
	private Locator msgHeading;
	private Locator lnklogout;
	
	
	//constructor
	public MyAccountPage(Page page)
	{
		super(page);
		msgHeading=page.locator("h2:has-text('My Account')");
		lnklogout=page.locator("div.list-group a").nth(12);		
	}
	
	//Action method
	public boolean isMyAccountPageExists()
	{
		try 
		{
			return msgHeading.isVisible();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnklogout.click();
	}

}

package pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage{
	
	//Locator
	private Locator lnkMyAccount;
	private Locator lnkRegister;
	private Locator lnkLogin;
	private Locator txtSearchbox;
	private Locator btnSearch;
	
	
	//constructor 
	public HomePage(Page page)
	{
		super(page);
		lnkMyAccount=page.locator("a[title='My Account']");
		lnkRegister=page.locator("a:has-text('Register')");
		lnkLogin=page.locator("a:has-text('Login')");
		txtSearchbox=page.locator("[name='search']");
		btnSearch=page.locator("button[class='btn btn-default btn-lg']");
	}
	
	//Action methods
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}


	public void clickRegister() {
		lnkRegister.click();
	}


	public void clickLogin() {
		lnkLogin.click();;
	}


	public void enterProductName(String pname) {
		txtSearchbox.fill(pname);;
	}


	public void  clickSearch() {
		btnSearch.click();
	}


}

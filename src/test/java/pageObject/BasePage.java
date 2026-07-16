package pageObject;

import com.microsoft.playwright.Page;

public class BasePage {

	Page page;
	
	public BasePage(Page page)
	{
		this.page=page;
	}
}

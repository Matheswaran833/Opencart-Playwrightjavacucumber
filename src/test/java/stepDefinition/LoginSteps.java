package stepDefinition;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.microsoft.playwright.Page;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utiliies.DataReader;

public class LoginSteps {

	private final Page page;
	private final HomePage hPage;
	private final LoginPage loginPage ;
	private final MyAccountPage maPage;
	
	List<HashMap<String, String>> datamap; //Data driven
	
	public LoginSteps()
	{
		page=BaseClass.getPage();
		hPage=new HomePage(page);
		loginPage=new LoginPage(page);
		maPage=new MyAccountPage(page);
	}
	
	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() 
	{
	    BaseClass.getLogger().info("Goto myaccount-->click on login...");
	    hPage.clickMyAccount();
	    hPage.clickLogin();
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) 
	{
		BaseClass.getLogger().info("Entering email and password.. ");
		loginPage.setEmail(email);
		loginPage.setpassword(pwd);
		
	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() 
	{
		loginPage.clickLogin();
		BaseClass.getLogger().info("clicked on login button...");
	}

	@Then("the user should be redirected to the MyAccount Page")
	public void the_user_should_be_redirected_to_the_my_account_page() 
	{
		boolean targetpage=maPage.isMyAccountPageExists();
		Assert.assertEquals(targetpage,true);
	    
	}
    
	 //*******   Data Driven test **************
    @Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
    public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows)
    {
        datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

       
        loginPage.setEmail(email);
		loginPage.setpassword(pwd);

		loginPage.clickLogin();
        
        try
        {
            boolean targetpage=maPage.isMyAccountPageExists();
            System.out.println("target page: "+ targetpage);
            if(exp_res.equals("Valid"))
            {
                if(targetpage==true)
                {
                   
                    maPage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equals("Invalid"))
            {
                if(targetpage==true)
                {
                	maPage.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
      }
 
}



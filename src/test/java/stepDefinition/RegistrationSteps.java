package stepDefinition;



import java.util.Map;

import org.junit.Assert;

import com.microsoft.playwright.Page;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class RegistrationSteps {
	
	private final Page page;
    private final  HomePage hPage;
    private  final AccountRegistrationPage regPage;
    
    public RegistrationSteps() {

        page = BaseClass.getPage();

        hPage = new HomePage(page);
        regPage = new AccountRegistrationPage(page);
    }

		@Given("the user navigates to Register Account page")
		public void the_user_navigates_to_register_account_page() 
		{
		    
			
			hPage.clickMyAccount();
			hPage.clickRegister();
		}
		
		@When("the user enters the details into below fields")
		public void the_user_enters_the_details_into_below_fields(DataTable dataTable) 
		{
		    Map<String,String> dataMap= dataTable.asMap(String.class,String.class);
		    
		    regPage.setfirstName(dataMap.get("firstName"));
		    regPage.setlastName(dataMap.get("lastName"));
		    regPage.setemail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		    regPage.settelephone(dataMap.get("telephone"));
		    regPage.setpassword(dataMap.get("password"));
		    regPage.setconfirmpassword(dataMap.get("password"));
		    
		}
		
		@When("the user selects Privacy Policy")
		public void the_user_selects_privacy_policy() 
		{
		    regPage.clickprivacypolicy();
		}
		
		@When("the user clicks on Continue button")
		public void the_user_clicks_on_continue_button() 
		{
		    regPage.clickBtncontinue();
		}
		
		@Then("the user account should get created successfully")
		public void the_user_account_should_get_created_successfully() 
		{
		    String confirmmsg=regPage.getConfirmationmsg();
		    Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
		}

	
}

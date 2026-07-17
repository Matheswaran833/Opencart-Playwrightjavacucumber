package factory;



import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseClass {
	
     static  Playwright playwright;
     static Browser browser;
     static BrowserContext context;
     static Page page;
     static Properties p;
     static Logger logger;
     
     public static Browser initBrowser() throws IOException
     {
    	 playwright=Playwright.create();
    	
    	 switch (getProperties().getProperty("browser").toLowerCase()) 
    	 {
    	 case "chrome":
             browser = playwright.chromium()
                     .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
             break;
         case "msedge":
             browser = playwright.chromium()
                     .launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
             break;
         case "chromium":
             browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
             break;
         case "firefox":
             browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
             break;
         case "webkit":
             browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
             break;

         default:
             System.out.println("invalid browser name");
             browser=null;
             
		}
     
		 return browser;
     }
     
     public static void closeBrowser()
     {
    	 if(browser!=null)
    	 {
    		 browser.close();
    	 }
    	 
    	 if(playwright!=null)
    	 {
    	 playwright.close();
    	 }
     }
     
     public static void setContext(BrowserContext browserContext)
     {
    	    context = browserContext;
     }

     public static BrowserContext getContext() 
     {
    	    return context;
     }

     public static void setPage(Page p) 
     {
    	    page = p;
     }

     public static Page getPage() 
     {
    	    return page;
     }
     
     
     public static Properties getProperties() throws IOException
     {
    	 FileReader file=new FileReader("./src/test/resources/config.properties");
    	 p=new Properties();
    	 p.load(file);
    	 return p;
    	 
     }
     
     public static Logger getLogger()
     {
    	 logger=LogManager.getLogger();
    	 return logger;
    	 
     }
     
     public static String randomeString()
 	{
 		String generatedString=RandomStringUtils.randomAlphabetic(5);
 		return generatedString;
 	}
 	
 	
 	public static String randomeNumber()
 	{
 		String generatedString=RandomStringUtils.randomNumeric(10);
 		return generatedString;
 	}
 	
 		
 	public static String randomAlphaNumeric()
 	{
 	String str=RandomStringUtils.randomAlphabetic(5);
 	 String num=RandomStringUtils.randomNumeric(10);
 	return str+num;
 	}
     

}

package Hooks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Video;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	BrowserContext context;
	Page page;
	Properties p;
	
	
	@Before
	public void setup() throws IOException
	{
		context=BaseClass.initBrowser().newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("test-output/videos"))
				                                                                  .setRecordVideoSize(1280,720));
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true)
				                                          .setSnapshots(true)
				                                          .setSources(true));
		BaseClass.setContext(context);
		page=context.newPage();
		BaseClass.setPage(page);
		p=BaseClass.getProperties();
		page.navigate(p.getProperty("appURL"));
		
	}
	
	@After
	public void teardown(Scenario scenario) throws IOException
	{
		
		    Video video = page.video();

		    String scenarioName = scenario.getName()
		            .replaceAll("[^a-zA-Z0-9]", "_");

		    Path tracePath = Paths.get("test-output/traces/" + scenarioName + ".zip");

		    try {

		        if (scenario.isFailed()) {

		            // Save trace only for failed scenario
		            context.tracing().stop(
		                    new Tracing.StopOptions().setPath(tracePath));

		        } else {

		            context.tracing().stop();
		        }

		    } finally {

		        // finalize video file
		        context.close();

		        if (video != null) {

		            Path oldPath = video.path();
		            Path newPath = Paths.get("test-output/videos/" + scenarioName + ".webm");

		            // keep video only for failed scenarios
		            if (scenario.isFailed()) {

		                Files.move(oldPath, newPath,
		                        StandardCopyOption.REPLACE_EXISTING);

		                // ===== CUCUMBER REPORT =====
		                scenario.attach(
		                        newPath.toString().getBytes(),
		                        "text/plain",
		                        "Video Path");

		                scenario.attach(
		                        tracePath.toString().getBytes(),
		                        "text/plain",
		                        "Trace Path");

		                // ===== EXTENT REPORT =====
		                String videoLink =
		                        "<a href='../videos/" + newPath.getFileName() +
		                        "' target='_blank'>🎥 View Video</a>";

		                String traceLink =
		                        "<a href='../traces/" + tracePath.getFileName() +
		                        "' target='_blank'>📄 View Trace</a>";

		                ExtentCucumberAdapter.addTestStepLog(videoLink);
		                ExtentCucumberAdapter.addTestStepLog(traceLink);

		            } else {

		                Files.deleteIfExists(oldPath);
		            }
		        }

		        BaseClass.closeBrowser();
		    }
		
		
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			byte[]screenshot=page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
			scenario.attach(screenshot,"image/png",scenario.getName());
		}
	}
}

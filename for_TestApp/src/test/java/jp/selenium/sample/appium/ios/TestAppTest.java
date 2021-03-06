package jp.selenium.sample.appium.ios;

import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;

/**
 * Sample codes for appium
 *
 */
public class TestAppTest
{
    RemoteWebDriver wd;

    private String hostname = "0.0.0.0";
    private int port = 4723;
    
    /**
     * app path like "/Users/foobar/Library/Developer/Xcode/DerivedData/TestApp-aqswedfrtgyhuj/Build/Products/Debug-iphonesimulator/TestApp.app"
     */
    // private static String appPath = "TestApp.app";
    private static String appPath = "TestApp.app";
    
    @Test
    public void testSample() throws Exception
    {
        wd.findElement(By.name("TextField1")).sendKeys("12345");
        wd.findElement(By.name("TextField2")).sendKeys("67890");
        wd.findElement(By.name("Done")).click();
        
        wd.switchTo().defaultContent();

        wd.findElement(By.name("ComputeSumButton")).click();
        wd.findElement(By.name("show alert")).click();
        wd.switchTo().alert().accept();
        
        wd.switchTo().defaultContent();

        wd.executeScript(
                "mobile: swipe",
                new HashMap<String, Double>()
                {
                    {
                        put("touchCount", 1.0);
                        put("startX", 154.0);
                        put("startY", 297.0);
                        put("endX", 200.0);
                        put("endY", 302.0);
                        put("duration", 0.8339844);
                    }
                }
            );

        wd.executeScript(
                "mobile: swipe",
                new HashMap<String, Double>()
                {
                    {
                        put("touchCount", 1.0);
                        put("startX", 200.0);
                        put("startY", 302.0);
                        put("endX", 100.0);
                        put("endY", 300.0);
                        put("duration", 0.8339844);
                    }
                }
            );
    }
    
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String hubURLStringFormat = "http://%s:%d/wd/hub";

        if (new File(appPath).exists() == false)
        {
            throw new FileNotFoundException();
        }
        
        capabilities.setCapability("app", appPath);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
        capabilities.setCapability(CapabilityType.VERSION, "6.1");
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
        
        String hubURLString = String.format(
                hubURLStringFormat,
                hostname,
                port
            );

        wd = new RemoteWebDriver(
                new URL(hubURLString),
                capabilities
            );

        wd.manage().timeouts().implicitlyWait(
                60,
                TimeUnit.SECONDS
            );
    }
    
    @After
    public void tearDown() throws Exception
    {
        wd.quit();
    }

}

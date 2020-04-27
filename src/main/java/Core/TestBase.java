package Core;

import Browser.IBrowserSetup;
import Reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase {

    private IBrowserSetup browserSetup = new IBrowserSetup();
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    public WebDriver driver;
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriver getDriver() {
        return driver;
    }

   public void getMeABrowsa(String browser) throws Exception {
       setDriver(browserSetup.setUpBrowser(browser));
       System.out.println("getDriver inside TEST_BASE is..:"+getDriver());
   }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite started!");
        extent = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public synchronized void beforeClass() {
        System.out.println("Before Class started!");
        ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {
        System.out.println("Before Method is..:"+method);
        ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result, Method method) {
        System.out.println("After Method is..:"+method);
        System.out.println("getDriver is.....:"+getDriver());
        if (result.getStatus() == ITestResult.FAILURE){
            takeScreenshot(method.getName(), getDriver());
        test.get().fail(result.getThrowable());
       }
        else if(result.getStatus() == ITestResult.SKIP) {
            test.get().skip(result.getThrowable());
        }
        else{
            test.get().pass("Test passed");
        }
        extent.flush();
    }

    protected void closeBrowserIfItExists(WebDriver driver) {
        if(driver!=null){
        driver.close();
        }
    }

    private void takeScreenshot(String testName, WebDriver driver){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        System.out.println("*** Took Screenshot! ***");
    }
}


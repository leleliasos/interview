package login;

import Browser.IBrowserElements;
import Browser.login.LoginComponent;
import Core.TestBase;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestReachingGoogle extends TestBase {

    private IBrowserElements browserElements = new IBrowserElements();
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private WebDriver localDriver;
    private LoginComponent loginComponent;

    @Test (priority = 1)
    @Parameters({"browser"})
    public void init(String browser) throws Exception {
        getMeABrowsa(browser);
        System.out.println("I can access getWebDriver from TestClass! And it is..:"+getDriver());
        localDriver=getDriver();
    }

    @Test (priority = 2)
    public void visitGoogle(){
        browserElements.gotoPage("http://www.google.co.uk", localDriver);
        System.out.println("I can access localDriver from TestClass! And it is..:"+localDriver);
    }

    @Test (priority = 3)
    public void forceFrameWorkToTakeScreensShot(){
        boolean hAEKgamaeiToGavro=false;
        Assert.assertTrue(hAEKgamaeiToGavro);
    }

    @Test (priority = 4, dependsOnMethods = "forceFrameWorkToTakeScreensShot")
    public void willThisMethodExecute(){
     System.out.println("Without dependsOnMethods! It executed!");
    }

    @Test (priority = 5)
    public void findGoogleSearchBoxAndTypeSomething() throws InterruptedException {
        loginComponent=new LoginComponent(localDriver);
        loginComponent.getGooglesBoxAndTypeSomething();
        System.out.println("localDriver inside Google BOX is..:"+localDriver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserIfItExists(localDriver);
    }
}

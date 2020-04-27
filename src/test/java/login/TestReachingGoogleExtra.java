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

public class TestReachingGoogleExtra extends TestBase {

    private IBrowserElements browserElements = new IBrowserElements();
    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
    private WebDriver localDriver;
    private LoginComponent loginComponent;

    @Test (priority = 1)
    @Parameters({"browser"})
    public void init(String browser) throws Exception {
        getMeABrowsa(browser);

        localDriver=getDriver();
    }

    @Test (priority = 2)
    public void visitGoogle() {
        browserElements.gotoPage("http://www.google.co.uk", localDriver);

    }




    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowserIfItExists(localDriver);
    }
}

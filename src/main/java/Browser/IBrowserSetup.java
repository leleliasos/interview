package Browser;


import Core.SeleniumGridConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IBrowserSetup implements  BrowserSetup{

    private static final ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();

    SeleniumGridConfiguration seleniumGridConfiguration = new SeleniumGridConfiguration();
    static boolean isGridAlive = false;

    public WebDriver setUpBrowser(String browser) throws Exception {
        WebDriver driver = null;
        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver","./selenium/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browser.equals("grid")){

            if(!isGridAlive) {
                seleniumGridConfiguration.HubConfig();
                System.out.println("Hub Configured Successfully");
                seleniumGridConfiguration.NodeConfig();
                System.out.println("Nodes Configured Successfully");
                isGridAlive=true;
            }

            driver = seleniumGridConfiguration.getRemoteBrowser();
        }
        return driver;
    }
}

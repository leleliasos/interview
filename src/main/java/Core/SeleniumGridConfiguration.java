package Core;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.SeleniumServer;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridConfiguration {

    private static final String HUB_REGISTER_ADDRESS="http://localhost:4444/grid/register";
    private final static int hubPort=4444;
    private static final int firstNodePortNumber=5555;
    private static final int maxNodeInstances=5;

    /*public static void main(String[] args) throws Exception {

        SeleniumGridConfiguration objHubNodeConfig = new SeleniumGridConfiguration();
        objHubNodeConfig.HubConfig();
        System.out.println("Hub Configured Successfully");

        objHubNodeConfig.NodeConfig();
        System.out.println("Node Configured Successfully");
        Thread.sleep(5000);
        objHubNodeConfig.invokeBrowser(); //attempt to startBrowser
    }*/

    public void HubConfig() throws Exception    {
        GridHubConfiguration gridHubConfig = new GridHubConfiguration();
        gridHubConfig.role = "hub";
        gridHubConfig.host = "localhost";
        gridHubConfig.port = hubPort;
        gridHubConfig.debug = true;

        Hub myHub = new Hub(gridHubConfig);
        myHub.start();
    }

    public void NodeConfig() throws Exception {
        int portNumber =firstNodePortNumber;
        for(int i=0;i<20;i++){
            GridNodeConfiguration gridNodeConfig = new GridNodeConfiguration();
            gridNodeConfig.hub = HUB_REGISTER_ADDRESS;
            gridNodeConfig.port = portNumber;
            gridNodeConfig.role = "node";

            RegistrationRequest req = RegistrationRequest.build(gridNodeConfig);
            SelfRegisteringRemote remote = new SelfRegisteringRemote(req);
            remote.setRemoteServer(new SeleniumServer(gridNodeConfig));
            remote.startRemoteServer();
            remote.startRegistrationProcess();
            portNumber++;
        }
    }

    public void invokeBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/selenium/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.setBinary("./src/main/resources/selenium/chromedriver.exe");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");


        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        //capability.setPlatform(Platform.WIN10); //better not set if not known
        capability.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        System.out.println("Driver Launched Successfully");
        driver.get("https://google.com");
        driver.close();
    }


    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() {
        @Override
        protected RemoteWebDriver initialValue() {
            //By default lets set the browser flavor always as Chrome.
            return new ChromeDriver();
        }
    };

    public WebDriver getRemoteBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/selenium/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.setBinary("./src/main/resources/selenium/chromedriver.exe");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");


        DesiredCapabilities capability = DesiredCapabilities.chrome();        capability.setBrowserName("chrome");
        //capability.setPlatform(Platform.WIN10); //better not set if not known
        capability.setCapability(ChromeOptions.CAPABILITY, options);

        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
        return driver.get();

    }
    // TODO KILL HUB IN AFTER SUITE --> http://{hubhost}:{hubport}/lifecycle-manager/LifecycleServlet?action=shutdown
}